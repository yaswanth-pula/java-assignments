package implementing.ceaser.cipher;

class WordPlay{
    public boolean isVowel(char character){
        String lowerCaseVowels = "aeiou";
        String upperCaseVowels = "AEIOU";
        return lowerCaseVowels.indexOf(character) != -1
                || upperCaseVowels.indexOf(character) != -1;
    }
    public String replaceVowels(String phrase,char characterToReplace){
        int lengthOfPhrase = phrase.length();

        StringBuilder replacedStringBuilder = new StringBuilder("");
        for(int index = 0; index<lengthOfPhrase; index++){
                char phraseCharacter = phrase.charAt(index);
                if( isVowel(phraseCharacter))
                        replacedStringBuilder.insert(index,characterToReplace);
                else
                        replacedStringBuilder.insert(index,phraseCharacter);
        }
        return replacedStringBuilder.toString();
    }
    public String emphasize(String phrase,char characterToReplace){
        int lengthOfPhrase = phrase.length();
        char oddIndexChar = '*';
        char evenIndexChar = '+';
        StringBuilder replacedStringBuilder = new StringBuilder("");

        for(int index = 0; index<lengthOfPhrase; index++){
            char phraseCharacter = phrase.charAt(index);
            if(phraseCharacter == characterToReplace) {
                if(isOdd(index))
                    replacedStringBuilder.insert(index, oddIndexChar);
                else
                    replacedStringBuilder.insert(index, evenIndexChar);
            }
            else
                replacedStringBuilder.insert(index,phraseCharacter);
        }
        return replacedStringBuilder.toString();
    }

    private boolean isOdd(int number){
        return number % 2 == 0;
    }

}

public class WordPlayRunner {
    public static void main(String[] args) {
        WordPlay wordPlay = new WordPlay();
        System.out.println(wordPlay.isVowel('F'));
        System.out.println(wordPlay.isVowel('i'));
        System.out.println(wordPlay.replaceVowels("Hello World",'*'));
        System.out.println(wordPlay.emphasize("dna ctgaaactga", 'a')
                                    .equals("dn* ctg+*+ctg+"));
        System.out.println(wordPlay.emphasize("Mary Bella Abracadabra",'a'));
        System.out.println("M+ry Bell+ +br*c*d*br+");

    }
}
