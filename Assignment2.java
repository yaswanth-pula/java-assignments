/**
 *
 * Write a java function that checks if the input string
 * contains all the letters of the alphabet a-z (case-insensitive).
 *
 *  Time Complexity : O(n) -> n length of string
 *  Space Complexity : O(1) -> boolean array
 * */

class AlphabetChecker{
    public boolean check(String inputString){
        boolean[] charExist = new boolean[26];

        for(char currentCharacter : inputString.toCharArray()){
                int charIndex = currentCharacter - 'a';
                if(! charExist[charIndex])
                        charExist[charIndex] = true;
        }

        for(int index = 0 ; index < 26 ;index++)
                if( ! charExist[index])
                        return false;
        return true;
    }
}

public class Assignment2 {
    public static void main(String[] args) {
        AlphabetChecker checker = new AlphabetChecker();
        System.out.println(checker.check("abcdefghijklmnopqrstuvwxyz"));
        System.out.println(checker.check("notallletterstring"));
    }
}