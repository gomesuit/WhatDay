package myclass;

import myclass.WikipediaApi;

import java.net.URI;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.DocumentException;

public class SearchWikiWord {
    private static final String XPATH = "/mediawiki/page/revision/text";
    private static String keyword;
    private static String word;

    public SearchWikiWord(String str){
        keyword = str;
    }

    public String getWord() throws Exception{
        runWikiApi();
        convertWord();
        return word;
    }

    private static void runWikiApi() throws Exception{
        String xml;
        WikipediaApi api;

        try {
            api = new WikipediaApi(getEncodeKeyword());
            xml = api.getXml();
            Document document = DocumentHelper.parseText(xml);
            word = document.valueOf(XPATH);
        } catch (DocumentException e) {
            e.printStackTrace();
            word = "";
        }
    }

    private static void convertWord(){
        word = word.replaceAll("\\{\\{仮リンク\\|(.+?)\\|.*\\}\\}", "$1");
        word = word.replaceAll("\\[(.+?)\\|(.+?)\\]", "$1");
        word = word.replaceAll("\\[", "");
        word = word.replaceAll("\\]", "");
        word = word.replaceAll("\\*", "◆");
        word = word.replaceAll("^[^◆].*", "");
        word = word.replaceAll("^\n", "");
    }

    private static String getEncodeKeyword() throws Exception{
        return new URI(keyword).toASCIIString();
    }
}