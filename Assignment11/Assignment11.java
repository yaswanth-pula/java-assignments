package Assignment11;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class CharacterCount{
    private static final String dataFolderPath = "./src/Assignment11/";

    public void performCharacterCount(String fileName){
        String inputData = readFromFile(fileName);
        Map<Character,Integer> charCountMap = getCharCountMap(inputData);
        StringBuilder outputData = new StringBuilder();
        for(Character character: charCountMap.keySet()) {
            int count =  charCountMap.get(character);
            outputData.append(character).append(" : ").append(count).append("\n");
        }
        writeToFile(outputData.toString());
    }

    private String readFromFile(String fileName){
        File file = new File(dataFolderPath+fileName);
        StringBuilder stringBuilder = new StringBuilder();

        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                stringBuilder.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
    private Map<Character,Integer> getCharCountMap(String data){
        Map<Character,Integer> charCountMap = new HashMap<>();
        for(int index=0; index < data.length(); index++){
            char currentChar = data.charAt(index);
            if(Character.isLetter(currentChar)) {
                if (charCountMap.containsKey(currentChar)) {
                    int currentCharCount = charCountMap.get(currentChar);
                    charCountMap.put(currentChar, currentCharCount + 1);
                } else {
                    charCountMap.put(currentChar, 1);
                }
            }
        }
        return charCountMap;
    }
    private void writeToFile(String fileData){
        try {
            FileWriter fileWriter = new FileWriter(dataFolderPath+"output.txt");
            fileWriter.append(fileData);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



public class Assignment11 {
    public static void main(String[] args) {
        CharacterCount characterCount = new CharacterCount();
        characterCount.performCharacterCount("input.txt");
    }
}
