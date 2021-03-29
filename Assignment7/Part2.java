package Assignment7;

/**
 * Part 2 : Create a Cycle class, with subclasses Unicycle, Bicycle and Tricycle.
 * Add a balance( ) method to Unicycle and Bicycle, but not to Tricycle.
 * Create instances of all three types and upcast them to an array of Cycle.
 * Try to call balance( ) on each element of the array and observe the results.
 * Downcast and call balance( ) and observe what happens.
 *
 */
class Cycle {
    public void printType() {
        System.out.println("Just Cycle");
    }

    public void balance() {
        System.out.println("Balance Simple  cycle");
    }
}

class UniCycle extends Cycle {
    public void printType() {
        System.out.println("Uni Cycle");
    }

    public void balance() {
        System.out.println("Unicycle Balance");
    }
}

class Bicycle extends Cycle {
    public void printType() {
        System.out.println("Bi Cycle");
    }

    public void balance() {
        System.out.println("Bicycle Balance");
    }

}

class Tricycle extends Cycle {
    public void printType() {
        System.out.println("Tri Cycle");
    }
}


public class Part2 {
    public static void main(String[] args) {
        Cycle[] cycles = {new Cycle(), new UniCycle(), new Bicycle(), new Tricycle()};
        for (Cycle cycle : cycles) {
            cycle.balance();
//            cannot cast one class with another
            ((UniCycle) cycle).balance();
            cycle.printType();
        }
    }
}
