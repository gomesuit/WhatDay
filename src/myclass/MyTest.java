package myclass;

public class MyTest
{
    public static void main( String[] args ) throws Exception{
    	String word;
        
    	word = new WikipediaApi("Template:今日は何の日").getWord();
        System.out.println(word);
        
    	word = new WikipediaApi("Portal:災害/今日は何の日").getWord();
        System.out.println(word);
        
    	word = new WikipediaApi("Portal:スポーツ/今日は何の日").getWord();
        System.out.println(word);
        
    	word = new WikipediaApi("Portal:文学/今日は何の日").getWord();
        System.out.println(word);
    }
}
