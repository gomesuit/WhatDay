package myclass;

import java.io.IOException;
import java.net.URI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikipediaApi
{
    private static final String BASE_URL = "https://ja.wikipedia.org/wiki/";
    private static String keyword;

    public WikipediaApi(String str){
        keyword = str;
    }

    public String getWord() throws Exception {
    	String xml = "";
        String url = getUrl();

		Document document = getDocumentFromFrom(url);
		Elements elements = document.select("#mw-content-text ul");
		
        for (Element element : elements) {
            Elements lis = element.select("li");
            for (Element li : lis) {
            	xml += elementToWord(li);
            }
        }
        
        return xml;
    }
    
    private String elementToWord(Element element){
    	String str = "";

    	str += "◆";
    	str += element.text();
    	str += "\n";
    	
		return str;
    }


    private static Document getDocumentFromFrom(String requestUrl) throws IOException{
    	Document document = Jsoup.connect(requestUrl).get();
    	return document;
    }

    private static String getUrl() throws Exception {
        return new URI(BASE_URL + keyword).toASCIIString();
    }
}
