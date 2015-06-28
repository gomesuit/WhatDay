package myclass;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Stack;

public class MyTweet
{
    private static String TODAY_KEYWORD = "Template:今日は何の日";
    private static String HEADER_WORD = "";
    private static Twitter twitter;
    private static String word;
    private static String header;

    public static void main( String[] args ) throws Exception{
        if(args.length > 0){
            TODAY_KEYWORD = args[0];
        }
        if(args.length > 1){
            HEADER_WORD = ":" + args[1];
        }

        twitter = new TwitterFactory().getInstance();
        word = new WikipediaApi(TODAY_KEYWORD).getWord();
        header = getToday();

        sentenceTweet();
    }

    private static void sentenceTweet() throws TwitterException{
        String buff;
        String[] strs = word.split("\n");
        Integer tweCnt = 0;
        Stack<String> stack = new Stack<String>();
        Status status;

        buff = strs[0];
        for (int i = 1 ; i < strs.length ; i++){
            if(buff.length() + strs[i].length() > 140 - 20){
                tweCnt++;
                buff = convertHeader(tweCnt) + buff;
                stack.push(buff);
                buff = strs[i];
            }else{
                buff = buff + "\n" + strs[i];
            }
        }

        tweCnt++;
        buff = convertHeader(tweCnt) + buff;
        stack.push(buff);

        while(!stack.empty()){
            //System.out.println((String)stack.pop());
            status = twitter.updateStatus((String)stack.pop());
            System.out.println("Successfully updated the status to [\n" + status.getText() + "\n].");
        }
    }

    private static String getToday(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("【M月d日】");
        sdf.setTimeZone(TimeZone.getTimeZone("JST"));
        return sdf.format(c.getTime()) + HEADER_WORD;
    }

    private static String convertHeader(Integer cnt){
        return header + "(その" + cnt + ")" + "\n\n";
    }
}
