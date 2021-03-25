package randomstory;

import edu.duke.FileResource;

import java.util.ArrayList;

class CharactersInPlay{
    private ArrayList<String> charactersNameList;
    private ArrayList<Integer> characterCountList;

    public CharactersInPlay() {
        charactersNameList = new ArrayList<>();
        characterCountList = new ArrayList<>();
    }

    private void updateCharacter(String personName){
        int indexOfCharacter = charactersNameList.indexOf(personName);
        if(indexOfCharacter == -1){
            charactersNameList.add(personName);
            characterCountList.add(1);
        }else{
            int characterPreviousCount = characterCountList.get(indexOfCharacter);
            characterCountList.set(indexOfCharacter, characterPreviousCount+1);
        }

    }
    private void findAllCharacters(){
        charactersNameList.clear();
        characterCountList.clear();

        FileResource fileResource = new FileResource();
        for(String currentLine : fileResource.lines()){
            currentLine = currentLine.trim();
            int periodIndex = currentLine.indexOf(".");
            if(periodIndex!=-1){
                String name = currentLine.substring(0,periodIndex);
                updateCharacter(name);
            }
        }
    }
    private void charactersWithNumberOfParts(int minCount, int maxCount){
        for(int index  = 0; index < characterCountList.size(); index++){

            int countOfCharacter = characterCountList.get(index);

            if(countOfCharacter >= minCount && countOfCharacter <= maxCount)
                printCharacter(index, countOfCharacter);
        }
    }
    public void tester(){
        findAllCharacters();
        charactersWithNumberOfParts(1,4);
        charactersWithNumberOfParts(2,6);
    }

    private void printCharacter(int index, int countOfCharacter) {
        System.out.println("Character Name : "+charactersNameList.get(index)
                            +"\tCount : "+ countOfCharacter);
    }
}

public class CharactersInPlayRunner {
    public static void main(String[] args) {
        CharactersInPlay play = new CharactersInPlay();
        play.tester();
        play.tester();

    }
}
