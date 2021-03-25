package object.oriented.cipher;

import edu.duke.FileResource;

public class TestCaesarCipherTwo {

    public void simpleTests(){
        FileResource fileResource = new FileResource();
        String message = fileResource.asString();
        int key1 = 17;
        int key2 = 3;
        CaesarCipherTwo cipherTwo = new CaesarCipherTwo(key1,key2);
        String encryptedMessage = cipherTwo.encryptWithTwoKeys(message);
        String decryptedMessage = cipherTwo.decrypt(encryptedMessage);
        System.out.println("keys are  " + key1 +","+key2+
                "\nEncrypted Message: " + encryptedMessage+
                "\nDecrypted Message: " +decryptedMessage );
        breakCaesarCipherTwo(encryptedMessage);
    }
    public void breakCaesarCipherTwo(String encryptedMessage){
        String encryptedFirstHalf = halfOfString(encryptedMessage,0);
        String encryptedSecondHalf = halfOfString(encryptedMessage,1);

        int firstDecryptKey = getDecryptionKey(encryptedFirstHalf);
        int secondDecryptKey = getDecryptionKey(encryptedSecondHalf);

        System.out.println("First Decryption Key: "+(firstDecryptKey));
        System.out.println("Second Decryption Key: "+(secondDecryptKey));

        CaesarCipherTwo caesarCipherTwo = new CaesarCipherTwo((firstDecryptKey),(secondDecryptKey));
        String decryptedMessage = caesarCipherTwo.encryptWithTwoKeys(encryptedMessage);
        System.out.println("Decrypted Message using Breaker:"+decryptedMessage);

    }

    private int getDecryptionKey(String encryptedMessage){
        int[] frequencyOfLetters = countLetters(encryptedMessage);
        int maxIndexOfLetter = maxIndexOfLetter(frequencyOfLetters);

        int currentDecryptionKey = maxIndexOfLetter - 4;
        if(currentDecryptionKey < 0)
            currentDecryptionKey +=26;

        return 26 - currentDecryptionKey;
    }
    private int maxIndexOfLetter(int[] frequencyOfLetters){
        int maxIndexOfLetter = 0;
        for(int index = 0 ; index<frequencyOfLetters.length; index++){
            if(frequencyOfLetters[maxIndexOfLetter] < frequencyOfLetters[index]){
                maxIndexOfLetter = index;
            }
        }
        return maxIndexOfLetter;
    }
    private int[] countLetters(String message){
        int[] lettersFrequency = new int[26];
        message = message.toLowerCase();
        for(int index = 0; index < message.length(); index++){
            char letter = message.charAt(index);
            if(Character.isLetter(letter)) {
                lettersFrequency[letter - 'a']++;
            }
        }
        return lettersFrequency;
    }
    private String halfOfString(String message,int start){
        StringBuilder slicedStringBuilder = new StringBuilder();
        for(int index = start; index < message.length(); index+=2){
            slicedStringBuilder.append(message.charAt(index));
        }

        return slicedStringBuilder.toString();
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo testCaesarCipherTwo = new TestCaesarCipherTwo();
        testCaesarCipherTwo.simpleTests();
    }
}
