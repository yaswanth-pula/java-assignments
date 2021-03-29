package yaswanth.assignment5.data;

public class Data {
    int unInitializedInt;
    char unInitializedChar;

    public void printGlobalUnInitialized(){
        System.out.println("Global Int Variable: "+unInitializedInt);
        System.out.println("Global Character Variable :"+unInitializedChar);
    }

    public void printLocalUnInitialized(){
        int localInt;
        char localChar;

//        System.out.println("Global Int Variable: "+localInt);
//        System.out.println("Global Character Variable: "+localChar);
//        cannot print the uninitialized local variables,
//        since they don't have any default values
    }

}
