package myclass;

import myclass.SearchWikiWord;

public class MyTest
{
    public static void main( String[] args ) throws Exception{
        String word = new SearchWikiWord(args[0]).getWord();

        System.out.println(word);
    }
}
