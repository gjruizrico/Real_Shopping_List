/*Author: Guillermo Ruiz-Rico
Class: CSC232 at Boston University*/

public class DuplicateException extends Exception
{
    public DuplicateException ()
    {
        super ("A duplicate item name was found. See below for specifics.\n");
    }


    public DuplicateException (String message)
    {
        super (message);
    }
}
