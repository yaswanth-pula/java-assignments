package breaking.ceaser.cipher;

import edu.duke.FileResource;

class WordLengths{
    public void countWordLengths(FileResource fileResource, int[] counts){
        for(String currentWord: fileResource.words()){
            int wordLength = currentWord.length();
            int decreaseLength = decreaseLengthFromWord(currentWord);
            int modifiedLength = wordLength - decreaseLength;
            int maxWordLength = counts.length;

            if(modifiedLength >= maxWordLength)
                modifiedLength = maxWordLength;

            counts[modifiedLength]++;
        }
    }
    public void testCountWordLengths(){
        FileResource fileResource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fileResource,counts);
        for(int index = 1;index<31;index++){
            System.out.println("Number of Words of Length "+index+" are : "+counts[index]);
        }

    }

    private int decreaseLengthFromWord(String word){
        if(word.length() == 0)
                return 0;
        char firstCharInWord = word.charAt(0);
        char lastCharInWord = word.charAt(word.length() - 1);
        char decreaseCounter = 0;
        if(!Character.isLetter(firstCharInWord))
                decreaseCounter++;
        if(!Character.isLetter(lastCharInWord))
                decreaseCounter++;

        return decreaseCounter;
    }

}
public class WordLengthsRunner {
    public static void main(String[] args) {
        WordLengths wordLengths = new WordLengths();
        wordLengths.testCountWordLengths();
    }
}
