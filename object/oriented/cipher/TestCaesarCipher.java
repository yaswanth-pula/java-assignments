package object.oriented.cipher;

import edu.duke.FileResource;

public class TestCaesarCipher {
    public void simpleTests(){
        FileResource fileResource = new FileResource();
        String message = fileResource.asString();
        int key = 18;
        CaesarCipher cipher = new CaesarCipher(key);
        String encryptedMessage = cipher.encrypt(message);
        String decryptedMessage = cipher.decrypt(encryptedMessage);

        System.out.println("key is " + key +
                        "\nEncrypted Message: " + encryptedMessage+
                        "\nDecrypted Message: " +decryptedMessage );
        breakCaesarCipher(encryptedMessage);
    }
    private void breakCaesarCipher(String encryptedMessage){

        int decryptKey = getDecryptionKey(encryptedMessage);
        System.out.println("Decrypt Key :"+(decryptKey));
        CaesarCipher cipher = new CaesarCipher(decryptKey);
        String decryptedBreakerMessage = cipher.encrypt(encryptedMessage);
        System.out.println("Decrypted Using Breaker Method :"+decryptedBreakerMessage);
    }
    private int getDecryptionKey(String encryptedMessage){
        int[] frequencyOfLetters = countLetters(encryptedMessage);
        int maxIndexOfLetter = maxIndexOfLetter(frequencyOfLetters);
        System.out.println();
        int currentDecryptionKey = maxIndexOfLetter - 4;
        if(currentDecryptionKey < 0)
            currentDecryptionKey += 26;

        return  26 - currentDecryptionKey;
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

    public static void main(String[] args) {
        TestCaesarCipher testCaesarCipher = new TestCaesarCipher();
        testCaesarCipher.simpleTests();
    }
}
