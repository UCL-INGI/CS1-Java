package src.librairies;
/**
 * Write a description of class StudentFormatException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StudentFormatException extends Exception
{
    String invalid;

    /**
     * Constructor for objects of class StudentFormatException
     */
    public StudentFormatException(String s)
    {
       super();
       invalid=s;
    }
    public StudentFormatException()
    {
       super();
      
    }


}
