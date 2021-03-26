import java.io.File;
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
        HashMap<String,HashSet<String>> languagesWithDictionaryMap  = new HashMap<>();

        DirectoryResource directoryResource = new DirectoryResource();
        for(File dictionaryFile : directoryResource.selectedFiles()){
            FileResource dictionaryFileResource = new FileResource(dictionaryFile);
            HashSet<String> dictionaryWordSet = readDictionary(dictionaryFileResource);

            String currentLanguageName = dictionaryFile.getName();
            System.out.println("Reading Completed: "+currentLanguageName);
            languagesWithDictionaryMap.put(currentLanguageName,dictionaryWordSet);
        }

        breakForAllLanguages(encryptedMessage,languagesWithDictionaryMap);
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
        char mostCommonCharacterOfLanguage = mostCommonCharInDictionary(dictionary);

        for(int currentKeyLength = 1; currentKeyLength < 100 ; currentKeyLength++){
            int[] currentKey = tryKeyLength(encryptedMessage,currentKeyLength,mostCommonCharacterOfLanguage);
            vigenereCipher = new VigenereCipher(currentKey);
            String currentDecryptedMessage = vigenereCipher.decrypt(encryptedMessage);

            int currentValidWords = countValidWords(currentDecryptedMessage,dictionary);

            if(currentValidWords > maxValidWords){
                bestDecryptedSoFar = currentDecryptedMessage;
                maxValidWords = currentValidWords;

            }
        }
        return bestDecryptedSoFar;
    }

    public char mostCommonCharInDictionary(HashSet<String> dictionary){
        int[] charFreqCount = new int[26];
        for(String currentWord : dictionary){
                currentWord = currentWord.toLowerCase();
                for(char character : currentWord.toCharArray()) {
                    if(character >= 97 && character <= 122)
                        charFreqCount[character - 'a']++;
                }
            }

        int maxCountIndex = 0;
        for(int index = 0 ; index < 26; index++)
                if(charFreqCount[index] > charFreqCount[maxCountIndex]) {
                    maxCountIndex = index;
                }

        return (char) ('a'+maxCountIndex);
    }

    public void breakForAllLanguages(String encryptedMessage,
                                     HashMap<String,HashSet<String>> languagesWithDictionaryMap){
        int maxValidWords = 0;
        String bestDecryptedMessage="Not Found";
        String bestLanguage = "NO Language";

        for(String currentLanguage: languagesWithDictionaryMap.keySet()){
            HashSet<String> currentLanguageDictionary = languagesWithDictionaryMap.get(currentLanguage);
            String currentDecryptedMessage = breakForLanguage(encryptedMessage,currentLanguageDictionary);

            int validWordsCountForCurrentLanguage = countValidWords(currentDecryptedMessage,currentLanguageDictionary);

            System.out.println("Breaking Completed For Language: "+currentLanguage);

            if(validWordsCountForCurrentLanguage > maxValidWords){
                maxValidWords = validWordsCountForCurrentLanguage;
                bestDecryptedMessage = currentDecryptedMessage;
                bestLanguage = currentLanguage;
            }
        }

        System.out.println("------------------------------------------------");
        System.out.println("Best Language Decryption is : "+bestLanguage);
        System.out.println("Best Decryted Message is \n"+bestDecryptedMessage);
        System.out.println("------------------------------------------------");
    }

}
