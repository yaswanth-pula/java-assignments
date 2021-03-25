package gladlibsImprovement;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

class WordsInFiles{
    private HashMap<String, ArrayList<String>> wordFilesMap;

    WordsInFiles(){
        wordFilesMap = new HashMap<>();
    }

    private void addWordsFromFile(File file){
        FileResource fileResource = new FileResource(file);
        for(String word: fileResource.words()){
            if(wordFilesMap.containsKey(word)){
                ArrayList<String> fileNameList = wordFilesMap.get(word);
                String currentFileName = file.getName();
                fileNameList.add(currentFileName);
                wordFilesMap.put(word,fileNameList);
            }
            else{
                ArrayList<String> fileNamesList = new ArrayList<>();
                fileNamesList.add(file.getName());
                wordFilesMap.put(word,fileNamesList);
            }
        }
    }

    private void buildWordFileMap(){
        wordFilesMap.clear();

        DirectoryResource directoryResource = new DirectoryResource();
        for(File currentFile : directoryResource.selectedFiles()){
            addWordsFromFile(currentFile);
        }
    }

    private int maximumFilesOfWord(){
        int maxFileCount = 0;
        for(String word : wordFilesMap.keySet()){
            int currentWordFileCount = wordFilesMap.get(word).size();
            if(maxFileCount < currentWordFileCount)
                    maxFileCount = currentWordFileCount;
        }
        return maxFileCount;
    }

    private ArrayList<String> wordsInNumFiles(int numberOfFiles){
        ArrayList<String> wordsList = new ArrayList<>();
        for(String word : wordFilesMap.keySet()){
            int currentWordFileCount = wordFilesMap.get(word).size();
            if(numberOfFiles == currentWordFileCount)
                wordsList.add(word);
        }

        return wordsList;
    }

    private void printFilesOfWord(String word){
        ArrayList<String> fileNamesList = wordFilesMap.get(word);
        System.out.println("Word "+word+" exist in below all files");
        for(int index = 0; index < fileNamesList.size(); index++){
            System.out.println(fileNamesList.get(index));
        }
    }
    public void tester(){
        buildWordFileMap();
        System.out.println("Maximum Files of common word:"+maximumFilesOfWord());
        System.out.println(wordsInNumFiles(3));
        printFilesOfWord("and");
        System.out.println(wordFilesMap);
    }

}


public class WordsInFilesRunner {
    public static void main(String[] args) {
        WordsInFiles wordsInFiles = new WordsInFiles();
        wordsInFiles.tester();
    }
}
