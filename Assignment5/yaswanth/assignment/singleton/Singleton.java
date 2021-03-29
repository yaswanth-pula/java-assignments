package yaswanth.assignment5.singleton;

public class Singleton {
    String unInitializedString;

    public static Singleton initialize(String string){
//        unInitializedString = string;
//        cannot initialize the non static member in static method
//        since the static method can be accessed without an instance,
//        there is no need of object creation.
        return new Singleton();
    }

    public void printString(){
        System.out.println("Singleton uninitialized String Variable: "+unInitializedString);
    }
}
