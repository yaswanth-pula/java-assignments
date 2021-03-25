package object.oriented.cipher;

public class CaesarCipher {
    private static final String baseUpperCaseAlphabetSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String baseLowerCaseAlphabetSet = "abcdefghijklmnopqrstuvwxyz";
    private String cipheredUpperSet;
    private String cipheredLowerSet;
    private int encryptionKey;
    public CaesarCipher(int encryptionKey) {
            this.encryptionKey  = encryptionKey;
            cipheredUpperSet = baseUpperCaseAlphabetSet.substring(encryptionKey)
                + baseUpperCaseAlphabetSet.substring(0, encryptionKey);

            cipheredLowerSet = baseLowerCaseAlphabetSet.substring(encryptionKey)
                + baseLowerCaseAlphabetSet.substring(0, encryptionKey);
    }
    public String encrypt(String message){
        StringBuilder encryptedMessageBuilder = new StringBuilder(message);

        int messageLength = message.length();
        for (int index = 0; index < messageLength; index++) {
            char currentChar = message.charAt(index);

            if (isUpperCaseCharacter(currentChar)) {
                int indexOfCurrentCharInBase = baseUpperCaseAlphabetSet.indexOf(currentChar);
                if (indexOfCurrentCharInBase != -1) {
                    char encryptChar = cipheredUpperSet.charAt(indexOfCurrentCharInBase);
                    encryptedMessageBuilder.setCharAt(index, encryptChar);
                }
            } else {
                int indexOfCurrentCharInBase = baseLowerCaseAlphabetSet.indexOf(currentChar);
                if (indexOfCurrentCharInBase != -1) {
                    char encryptChar = cipheredLowerSet.charAt(indexOfCurrentCharInBase);
                    encryptedMessageBuilder.setCharAt(index, encryptChar);
                }
            }

        }
        return encryptedMessageBuilder.toString();
    }
    public String decrypt(String encryptedMessage){
        CaesarCipher cipher = new CaesarCipher(26 - encryptionKey);
        return cipher.encrypt(encryptedMessage);
    }
    private boolean isUpperCaseCharacter(char character) {
        return Character.isUpperCase(character);
    }
}
