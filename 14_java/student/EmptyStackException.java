// EmptyStackException.java

/**
 * Class representing an exception
 * This is exception is used when trying to perform an operation
 * that is not allowed on an empty stack
 * 
 * @author Sebastien Combefis
 * @version 22 nov. 2008
 */
package student;

public class EmptyStackException extends Exception
{ 
    public EmptyStackException(String msg) {
        super(msg);
    }
}
