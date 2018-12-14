/*Author: Guillermo Ruiz-Rico
Class: CSC232 at Boston University*/

import java.awt.*;

public interface GRR_FifthAssignment_Print_Interface
{
    /** Prints existing shopping list upon reading it from the txt file.*/
    public void ReadTextFile();

    /** Prints instructions by asking for all item names and looks for duplicates. Exits the program if one is found.*/
    public void InputData() throws DuplicateException;

    /**  Asks the user to set the priority numbers via print out. If a string, special character, or a number out of range is entered, the program exits. Duplicates are allowed*/
    public void setPriority()throws OutOfRangeException;

    /** Prints instruction to the user to change one item's name and priority number.*/
    public void setData() throws Exception;

    /** Prints either a False or True for each comparison pair.*/
    public void customEqualsMethod();

    /** Prints and asks for user input to set the shopper's name and budget (bankAccount) */
    public void setandgetNameandBudget() throws Exception;

    /** Asks via print statement for the user for the quantity of an item that needs to be purchased */
    public void setQuantity() throws Exception;

    /** Connects to all 3 store websites and retrieves real product name and prices for all */
    public void getRealItems() throws AWTException, Exception;

    /** Presents to the user all choices and asks for the choice for each product*/
    public void printRealItemsAndChoose() throws Exception;

    /** Performs the actual recording of the selected products, price and store*/
    public void recordChoices() throws Exception;

    /** Prints an array with the priority number and its name retrieved from the InputData and setPriority methods. Subsequently, sorts it using the bubble method and prints them all*/
    public void getData();

    /** Prints purchased items and their quantity as well as the remaining balance in the bank account */
    public void goShopping();

    /** Prints what's pending for purchase */
    public void showPending();

    /** Shows what was purchased via print statements */
    public void showPurchased();

    /** Prints to the text file. It only prints to the console if any issues are encountered. */
    public void WriteTextFile();
}
