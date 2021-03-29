package Assignment7;

/**
 * Part 1 : Create an inheritance hierarchy of Rodent: Mouse, Gerbil, Hamster,etc.
 * In the base class, provide methods that are common to all Rodents,
 * and override these in the derived classes to perform different behaviors
 * depending on the specific type of Rodent. Create an array of Rodent,
 * fill it with different specific types of Rodents, and
 * call your base-class methods to see what happens.
 * Make the methods of Rodent abstract whenever possible and
 * all classes should have default constructors that print a message about that class.
 */

class Rodent {
    Rodent() {
        System.out.println("Rodent class Constructor");
    }

    public void printMessage() {
        System.out.println("This is Rodent class");
    }
}

class Mouse extends Rodent {
    Mouse() {
        System.out.println("Mouse class Initialized");
    }

    public void printMessage() {
        System.out.println("This is Mouse Class");
    }
}

class Gerbil extends Rodent {
    Gerbil() {
        System.out.println("Gerbil class Initialized");
    }

    public void printMessage() {
        System.out.println("This is Mouse Class");
    }
}

class Hamster extends Rodent {
    Hamster() {
        System.out.println("Hamster class Initialized");
    }

    public void printMessage() {
        System.out.println("This is Hamster Class");
    }
}

public class Part1 {
    public static void main(String[] args) {
        Rodent[] rodents = {new Rodent(), new Mouse(), new Gerbil(), new Hamster()};
        for (Rodent rodent : rodents)
            rodent.printMessage();
    }
}
