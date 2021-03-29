/**
 * Error Handling
 *
 * Create three new types of exceptions.
 * Write a class with a method that throws all three.
 * In main( ), call the method but only use a single catch clause that will catch all three types of exceptions.
 * Add a finally clause and verify that your finally clause is executed,
 * even if a NullPointerException is thrown.
 *
 * */
class FirstException extends Exception{
    public FirstException(String message){
        super(message);
    }
}
class SecondException extends NullPointerException{
    public SecondException(String message){
        super(message);
    }
}
class ThirdException extends Exception{
    public ThirdException(String message){
        super(message);
    }
}

public class Assignment8 {
    public static void throwMultipleException(String stringMessage)
            throws FirstException, SecondException, ThirdException{
        try{
            if(stringMessage.equals("First"))
                 throw new FirstException("First Exception");
            else if(stringMessage.equals("Second"))
                throw new SecondException("Second Exception");
            else
                throw new ThirdException("Third Exception");
        }
        catch (Exception exception){
            throw exception;
        }
        finally {
            System.out.println(" Check If Finally Clause Executed ");
        }
    }

    public static void main(String[] args) throws FirstException, SecondException, ThirdException {
        throwMultipleException("Second");
    }
}
