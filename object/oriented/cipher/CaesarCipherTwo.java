package object.oriented.cipher;

public class CaesarCipherTwo {
    private int encryptionKey1, encryptionKey2;
    public CaesarCipherTwo(int encryptionKey1,int encryptionKey2){
        this.encryptionKey1 = encryptionKey1;
        this.encryptionKey2 = encryptionKey2;
    }
    public String encryptWithTwoKeys(String message){
        int messageLength = message.length();
        StringBuilder encryptedMessage = new StringBuilder();
        for (int index = 0; index < messageLength; index++) {
            String messageCharString = message.substring(index, index + 1);
            CaesarCipher cipher;
            if (index % 2 == 0){
                cipher = new CaesarCipher(encryptionKey1);
            }
            else {
                cipher = new CaesarCipher(encryptionKey2);
            }
            encryptedMessage.append(cipher.encrypt(messageCharString));
        }
        return encryptedMessage.toString();
    }
    public String decrypt(String encryptedMessage){
        CaesarCipherTwo caesarCipherTwo = new CaesarCipherTwo(26 - encryptionKey1,26 - encryptionKey2);
        return caesarCipherTwo.encryptWithTwoKeys(encryptedMessage);
    }
}
