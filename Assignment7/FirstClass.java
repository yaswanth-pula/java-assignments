package Assignment7;

/**
 * Create a class with an inner class that has a non-default constructor (one that takes arguments).
 * Create a second class with an inner class that inherits from the first inner class.
 *
 */

public class FirstClass{
    int firstClassValue = 0;

    public class SecondClass{
        int secondVariable1;
        int secondVariable2;
        SecondClass(int secondVariable1,int secondVariable2){
            this.secondVariable1 = secondVariable1;
            this.secondVariable2 = secondVariable2;
        }
    }
}

class SuperClass{
//    class InheritedClass extends SecondClass {
//        public void method(){
//            System.out.println("Inherited Class Method");
//        }
//    }
}

