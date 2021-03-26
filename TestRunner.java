import edu.duke.FileResource;

import java.util.Arrays;

public class TestRunner {
    public static void main(String[] args) {
        VigenereBreaker vigenereBreaker = new VigenereBreaker();

        System.out.println(vigenereBreaker.sliceString("abcdefghijklm",0,5));
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm",3,5));

        FileResource fileResource = new FileResource();
        String message = fileResource.asString();
        int[] keys =  vigenereBreaker.tryKeyLength(message,4,'e');
        System.out.println(Arrays.toString(keys));

        vigenereBreaker.breakVigenere();

    }
}
