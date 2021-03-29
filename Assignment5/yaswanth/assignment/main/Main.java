package yaswanth.assignment5.main;

import yaswanth.assignment5.data.Data;
import yaswanth.assignment5.singleton.Singleton;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        data.printGlobalUnInitialized();
        data.printLocalUnInitialized();

        Singleton singleton = Singleton.initialize("Initial String");
        singleton.printString();
    }
}
