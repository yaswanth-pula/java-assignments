import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment9 {

    public static boolean testSentencePattern(String sentence){
        String patternString = "[A-Z][^.]*\\.$";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(sentence);

        return matcher.matches();
    }
    public static void main(String[] args) {
        System.out.println(testSentencePattern("Perfect Sentence."));
        System.out.println(testSentencePattern("not with Capital."));
        System.out.println(testSentencePattern("Not with ending period,"));
    }
}
