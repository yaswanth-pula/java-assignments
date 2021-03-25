package randomstory;

import edu.duke.FileResource;
import java.util.ArrayList;

class MostFrequentWord{
    private ArrayList<String> uniqueWordsList;
    private ArrayList<Integer> wordFrequencyList;

    public MostFrequentWord(){
        uniqueWordsList = new ArrayList<>();
        wordFrequencyList = new ArrayList<>();
    }

    private void findUniqueWordsFromFile(){
        uniqueWordsList.clear();
        wordFrequencyList.clear();

        FileResource fileResource = new FileResource();
        for(String currentWordInFile :fileResource.words()){
            currentWordInFile = currentWordInFile.toLowerCase();

            int indexOfWordInList = uniqueWordsList.indexOf(currentWordInFile);

            if(indexOfWordInList == -1){
                uniqueWordsList.add(currentWordInFile);
                wordFrequencyList.add(1);
            }
            else{
                int frequency = wordFrequencyList.get(indexOfWordInList);
                wordFrequencyList.set(indexOfWordInList,frequency+1);
            }
        }
    }

    public void testFindUniqueWords(){
        findUniqueWordsFromFile();
        int uniqueWordsCount = uniqueWordsList.size();

        System.out.println("---------------------------------------------------------");
        System.out.println("Count\tWORDS");
        System.out.println("---------------------------------------------------------");
        for(int index = 0 ; index < uniqueWordsCount ; index++){
            System.out.println(wordFrequencyList.get(index)+"\t"+uniqueWordsList.get(index));
        }
        System.out.println("Number of Unique Words in the File Are: "+uniqueWordsCount);
    }

    private int findIndexOfMaxFrequent(){
        int maxIndex = 0;
        int maxIndexValue = 0;
        int listSize = wordFrequencyList.size();
        for(int index = 0 ; index < listSize; index++){
            int currentIndexValue = wordFrequencyList.get(index);
            if(maxIndexValue < currentIndexValue){
                maxIndexValue = currentIndexValue;
                maxIndex = index;
            }
        }
        return maxIndex;
    }
    public void testMaxIndex(){
        int maxIndex = findIndexOfMaxFrequent();
        System.out.println("The Most Frequent Word is "+uniqueWordsList.get(maxIndex)
                            +"\tOccurs "+wordFrequencyList.get(maxIndex));
    }

}

public class MostFrequentWordRunner {
    public static void main(String[] args) {
        MostFrequentWord mostFrequentWord = new MostFrequentWord();
        mostFrequentWord.testFindUniqueWords();
        mostFrequentWord.testMaxIndex();
    }
}
