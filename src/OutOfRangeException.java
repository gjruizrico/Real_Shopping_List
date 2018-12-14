/*Author: Guillermo Ruiz-Rico
Class: CSC232 at Boston University*/

public class OutOfRangeException extends Exception
{
    public OutOfRangeException ()
    {
        super ("You've entered an out of range numeric value. Please start over. Thank you.");
    }


    public OutOfRangeException (String message)
    {
        super (message);
    }
}
