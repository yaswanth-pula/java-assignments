package implementing.ceaser.cipher;

import edu.duke.FileResource;

class CaesarCipher{
    private static final String baseUpperCaseAlphabetSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String baseLowerCaseAlphabetSet = "abcdefghijklmnopqrstuvwxyz";

    public String encryptCipherUpperCase(String message,int encryptionKey){
        String cipheredAlphabetSet = baseUpperCaseAlphabetSet.substring(encryptionKey)
                                    + baseUpperCaseAlphabetSet.substring(0,encryptionKey);

        StringBuilder encryptedMessageBuilder = new StringBuilder(message);

        int messageLength = message.length();
        for(int index = 0; index<messageLength; index++){
            char currentChar = message.charAt(index);
            int indexOfCurrentCharInBase = baseUpperCaseAlphabetSet.indexOf(currentChar);
            if(indexOfCurrentCharInBase!=-1){
                char encryptChar = cipheredAlphabetSet.charAt(indexOfCurrentCharInBase);
                encryptedMessageBuilder.setCharAt(index,encryptChar);
            }
        }

        return encryptedMessageBuilder.toString();
    }

    public String encryptCipher(String message,int encryptionKey){
        String cipheredUpperSet = baseUpperCaseAlphabetSet.substring(encryptionKey)
                + baseUpperCaseAlphabetSet.substring(0,encryptionKey);

        String cipheredLowerSet = baseLowerCaseAlphabetSet.substring(encryptionKey)
                + baseLowerCaseAlphabetSet.substring(0,encryptionKey);

        StringBuilder encryptedMessageBuilder = new StringBuilder(message);

        int messageLength = message.length();
        for(int index = 0; index<messageLength; index++){
            char currentChar = message.charAt(index);

            if(isUpperCaseCharacter(currentChar)){
                int indexOfCurrentCharInBase = baseUpperCaseAlphabetSet.indexOf(currentChar);
                if(indexOfCurrentCharInBase!=-1){
                    char encryptChar = cipheredUpperSet.charAt(indexOfCurrentCharInBase);
                    encryptedMessageBuilder.setCharAt(index,encryptChar);
                }
            }
            else{
                int indexOfCurrentCharInBase = baseLowerCaseAlphabetSet.indexOf(currentChar);
                if(indexOfCurrentCharInBase!=-1){
                    char encryptChar = cipheredLowerSet.charAt(indexOfCurrentCharInBase);
                    encryptedMessageBuilder.setCharAt(index,encryptChar);
                }
            }

        }
        return encryptedMessageBuilder.toString();
    }

    public void testCaesar(){
        FileResource fileResource = new FileResource();
        String message = fileResource.asString();

        int key = 23;
        String encryptedMessage = encryptCipher(message,key);
        System.out.println("key is " + key + "\n" + encryptedMessage);
        System.out.println(encryptCipher("First Legion Attack East FLanK!",17));


    }

    public String encryptCipherWithTwoKeys(String message,int encryptionKey1,int encryptionKey2){
        int messageLength = message.length();
        StringBuilder encryptedMessage = new StringBuilder();
        for(int index = 0 ;index < messageLength; index++){
            String messageCharString = message.substring(index,index+1);
                if(index % 2 == 0)
                    encryptedMessage.append(encryptCipher(messageCharString, encryptionKey1));
                else
                    encryptedMessage.append(encryptCipher(messageCharString,encryptionKey2));
        }
        return encryptedMessage.toString();
    }

    private boolean isUpperCaseCharacter(char character){
        return Character.isUpperCase(character);
    }
}

public class CaesarCipherRunner {
    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher();
        //cipher.testCaesar();
        String encryptedMessage =  cipher.encryptCipherWithTwoKeys("First Legion",23,17);
        System.out.println(encryptedMessage);
        System.out.println(cipher.encryptCipherWithTwoKeys("At noon be in the conference room with your " +
                "hat on for a surprise party. YELL LOUD!",8,21));
    }
}
