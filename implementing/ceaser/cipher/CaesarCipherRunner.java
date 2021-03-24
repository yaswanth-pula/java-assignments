package implementing.ceaser.cipher;

public class CaesarCipherRunner {
    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher();
        //cipher.testCaesar();
        System.out.println(cipher.encryptCipher("Hee leee kiee peeee awsee?",4));
//        String encryptedMessage =  cipher.encryptCipherWithTwoKeys("First Legion",23,17);
//        System.out.println(encryptedMessage);
//        System.out.println(cipher.encryptCipherWithTwoKeys("At noon be in the conference room with your " +
//                "hat on for a surprise party. YELL LOUD!",8,21));
    }
}
