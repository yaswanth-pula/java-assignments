package breaking.ceaser.cipher;

import implementing.ceaser.cipher.CaesarCipher;

class CaesarBreaker{
    public String decrypt(String encryptedMessage){
        CaesarCipher cipher = new CaesarCipher();
        int decryptKey = getDecryptionKey(encryptedMessage);

        return cipher.encryptCipher(encryptedMessage,decryptKey);
    }

    public void testDecrypt(){
        System.out.println(decrypt("Lii piii omii tiiii eawii?"));
        System.out.println(decryptWithTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
    }

    public String decryptWithTwoKeys(String encryptedMessage){
        CaesarCipher cipher = new CaesarCipher();

        String encryptedFirstHalf = halfOfString(encryptedMessage,0);
        String encryptedSecondHalf = halfOfString(encryptedMessage,1);

        int firstDecryptKey = getDecryptionKey(encryptedFirstHalf);
        int secondDecryptKey = getDecryptionKey(encryptedSecondHalf);

        System.out.println("First Decryption Key: "+firstDecryptKey);
        System.out.println("Second Decryption Key: "+secondDecryptKey);

        return cipher.encryptCipherWithTwoKeys(encryptedMessage,firstDecryptKey,secondDecryptKey);
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
        StringBuilder slicedStringBuilder = new StringBuilder("");
        for(int index = start; index < message.length(); index+=2){
            slicedStringBuilder.append(message.charAt(index));
        }

        return slicedStringBuilder.toString();
    }

}

public class CaesarBreakerRunner {
    public static void main(String[] args) {
        CaesarBreaker breaker = new CaesarBreaker();
        breaker.testDecrypt();
    }
}
