package myclass;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.HttpClientBuilder;

public class WikipediaApi
{
    private static final String BASE_URL = "http://ja.wikipedia.org/wiki/";
    private static final String API_URL = "特別:データ書き出し";
    private static String keyword;
    private static String xml;

    public WikipediaApi(String str){
        keyword = str;
    }

    public String getXml() throws Exception {
        executeRequest();
        return xml;
    }

    private static void executeRequest() throws Exception {
        String url = getUrl() + keyword;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet getMethod = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(getMethod)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    xml = EntityUtils.toString(entity,StandardCharsets.UTF_8).replaceAll("<mediawiki.*>", "<mediawiki>");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            xml = "";
        }
    }

    private static String getUrl() throws Exception {
        return new URI(BASE_URL + API_URL + "/").toASCIIString();
    }
}
