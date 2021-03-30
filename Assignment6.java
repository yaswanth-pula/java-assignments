import java.util.Arrays;

/**
 * Part 1: A vampire number v is a number with an even number of digits n,
 * that can be factored into two numbers x and y each with n/2 digits and
 * not both with trailing zeroes, where v contains precisely all the digits from x and from y,
 * in any order. Write a java program to print first 100 vampire numbers.
 *
 * Part 2:  Create a class with two (overloaded) constructors.
 * Using this, call the second constructor inside the first one.
 *
 * Part 3: Create a class with a constructor that takes a String argument.
 * During construction, print the argument.
 * Create an array of object references to this class,
 * but don’t actually create objects to assign into the array.
 * When you run the program, notice whether the initialization messages
 * from the constructor calls are printed.
 *
 * Part 4:  Complete the previous exercise by creating objects
 * to attach to the array of references.
 *
 * */

// part 1
class VampireClass{

    public void printVampireNumbers(int numberOfVampires){
        int foundVampireCount = 0;
        for(long iterator = 10; foundVampireCount < numberOfVampires; iterator++ ){

            if(isOddNumberOfDigits(iterator)) {
                iterator = getSeriesLastNumber(iterator);
                continue;
            }

            if( isVampire(iterator) ){
                foundVampireCount++;
                System.out.println(foundVampireCount+" : "+iterator);
            }
        }
    }

    private boolean isVampire(long iterator){
        for(long fang1 = 2; fang1 < highestDivisor(iterator); fang1++){
            if(isDivisible(iterator,fang1)){
                long fang2 = iterator / fang1;
                if(isValidFangs(iterator, fang1, fang2) && fang1 <= fang2)
                    return true;
            }
        }
        return false;
    }
    private boolean isValidFangs(long original, long fang1, long fang2){
        String fang1AsString = Long.toString(fang1);
        String fang2AsString = Long.toString(fang2);

        if(hasTrailingZeros(fang1AsString) && hasTrailingZeros(fang2AsString)) return false;

        int originalLength = numDigits(original);
        if(numDigits(fang1) != originalLength / 2 || numDigits(fang2) != originalLength / 2) return false;

        byte[] originalBytes = Long.toString(original).getBytes();
        byte[] fangBytes = (fang1AsString + fang2AsString).getBytes();

        Arrays.sort(originalBytes);
        Arrays.sort(fangBytes);

        return Arrays.equals(originalBytes, fangBytes);
    }
    private  int numDigits(long num){
        return Long.toString(Math.abs(num)).length();
    }
    private boolean isOddNumberOfDigits(long iterator){
        return (numDigits(iterator) % 2) != 0;
    }
    private long getSeriesLastNumber(long iterator){
        return iterator * 10 - 1;
    }
    private long highestDivisor(long iterator){
        return (long) (Math.sqrt(iterator) + 1);
    }
    private boolean isDivisible(long iterator, long fang1){
        return iterator % fang1 == 0;
    }
    private boolean hasTrailingZeros(String fang){
        return fang.endsWith("0");
    }
}


// part 2
class Overloaded{
    Overloaded(){
        this("First Constructor");

    }
    Overloaded(String input){
        System.out.println("Second Constructor called from : "+input);
    }

}

// part 3
class ArrayObject{
    String inputArgument;

    ArrayObject(String input){
        this.inputArgument = input;
        System.out.println("Initialized Message with argument "+inputArgument);
    }
}

public class Assignment6 {
    public static void main(String[] args) {
//        Part 1
        VampireClass vampire = new VampireClass();
        vampire.printVampireNumbers(100);
//        Part 2
//        Overloaded overloaded = new Overloaded();
//
//        ArrayObject[]  objects = new ArrayObject[5];
////        part 3
////        prints null as objects
//        for(int index = 0; index < objects.length ;index ++)
//            System.out.println(objects[index]);
//
////        part 4
////        prints initialized constructor message
//        for(int index = 0; index < objects.length ;index ++)
//            objects[index] = new ArrayObject("Object "+index);

    }
}
