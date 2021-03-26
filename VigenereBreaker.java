import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedStringBuilder = new StringBuilder();

        for(int index = whichSlice; index < message.length() ;index+=totalSlices){
            slicedStringBuilder.append(message.charAt(index));
        }


        return slicedStringBuilder.toString();
    }

    public int[] tryKeyLength(String encryptedMessage, int keyLength, char mostCommonCharacter) {
        int[] key = new int[keyLength];
        CaesarCracker caesarCracker = new CaesarCracker();
        for(int slice = 0 ;slice < keyLength;slice++) {
            String slicedString = sliceString(encryptedMessage,slice,keyLength);
            key[slice] = caesarCracker.getKey(slicedString);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fileResourceForMessage = new FileResource();
        String encryptedMessage = fileResourceForMessage.asString();

        FileResource fileResourceForDictionary = new FileResource("src/dictionaries/English");
        HashSet<String> dictionaryWordSet = readDictionary(fileResourceForDictionary);

        String decryptedMessage = breakForLanguage(encryptedMessage,dictionaryWordSet);

        System.out.println("Best Decrypted Message is :\n"+decryptedMessage);
    }
    public HashSet<String> readDictionary(FileResource fileResource){
            HashSet<String> dictionaryWordSet = new HashSet<>();
            for(String word : fileResource.lines()){
                word = word.toLowerCase();
                dictionaryWordSet.add(word);
            }
            return dictionaryWordSet;
    }

    public int countValidWords(String decryptedMessage, HashSet<String> dictionary){
       String[] encryptedWords = decryptedMessage.toLowerCase().split("\\W");
       int validWordCounter = 0;
       for(String currentWord : encryptedWords){
            if(dictionary.contains(currentWord))
                    validWordCounter++;
        }
       return validWordCounter;
    }

    public String breakForLanguage(String encryptedMessage,HashSet<String> dictionary){
        int maxValidWords = 0;
        String bestDecryptedSoFar = "";
        VigenereCipher vigenereCipher;
        for(int currentKeyLength = 1; currentKeyLength < 39 ; currentKeyLength++){
            int[] currentKey = tryKeyLength(encryptedMessage,currentKeyLength,'e');
            vigenereCipher = new VigenereCipher(currentKey);
            String currentDecryptedMessage = vigenereCipher.decrypt(encryptedMessage);

            int currentValidWords = countValidWords(currentDecryptedMessage,dictionary);

            if(currentValidWords > maxValidWords){
                bestDecryptedSoFar = currentDecryptedMessage;
                maxValidWords = currentValidWords;
                System.out.println("Best Key Length :"+currentKeyLength);
            }
        }
//        System.out.println("Count of Best Valid Words are"+maxValidWords);
        return bestDecryptedSoFar;
    }


    public static void main(String[] args) {


    }
}
