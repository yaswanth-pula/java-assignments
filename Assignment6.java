import java.util.Arrays;
import java.util.HashSet;

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
    private  int numDigits(long num){
        return Long.toString(Math.abs(num)).length();
    }

    private boolean fangCheck(long original, long fang1, long fang2){
        if(Long.toString(fang1).endsWith("0") && Long.toString(fang2).endsWith("0")) return false;

        int originalLen = numDigits(original);
        if(numDigits(fang1) != originalLen / 2 || numDigits(fang2) != originalLen / 2) return false;

        byte[] originalBytes = Long.toString(original).getBytes();
        byte[] fangBytes = (Long.toString(fang1) + fang2).getBytes();

        Arrays.sort(originalBytes);
        Arrays.sort(fangBytes);

        return Arrays.equals(originalBytes, fangBytes);
    }

    public void printVampireNumbers(){
        HashSet<Long> vamps = new HashSet<Long>();

        for(long iterator = 10; vamps.size() < 100; iterator++ ){
            if((numDigits(iterator) % 2) != 0) {
                iterator = iterator * 10 - 1;
                continue;
            }

            for(long fang1 = 2; fang1 < Math.sqrt(iterator) + 1; fang1++){
                if(iterator % fang1 == 0){
                    long fang2 = iterator / fang1;
                    if(fangCheck(iterator, fang1, fang2) && fang1 <= fang2){
                        vamps.add(iterator);
                        System.out.println(vamps.size()+" : "+iterator);
                    }
                }
            }
        }
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
        vampire.printVampireNumbers();
//        Part 2
        Overloaded overloaded = new Overloaded();

        ArrayObject[]  objects = new ArrayObject[5];
//        part 3
//        prints null as objects
        for(int index = 0; index < objects.length ;index ++)
            System.out.println(objects[index]);

//        part 4
//        prints initialized constructor message
        for(int index = 0; index < objects.length ;index ++)
            objects[index] = new ArrayObject("Object "+index);
    }
}
