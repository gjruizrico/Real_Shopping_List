/*Author: Guillermo Ruiz-Rico
Class: CSC232 at Boston University*/

public class GRR_FifthAssignment_Main /**This class only contains invocations to methods and the creation of our object - "shopping list". Refer to the GRR_FifthAssignment_Class_and_Methods for more information */
{
    public static void main (String [] args) throws OutOfRangeException, DuplicateException, Exception
    {
        GRR_FifthAssignment_Class_and_Methods shoppinglist = new GRR_FifthAssignment_Class_and_Methods();
        shoppinglist.ReadTextFile();
        shoppinglist.InputData();
        shoppinglist.setPriority();
        shoppinglist.getRealItems();
        shoppinglist.printRealItemsAndChoose();
        shoppinglist.recordChoices();
      //shoppinglist.setData(); //Commenting it out since it's not needed
      //shoppinglist.customEqualsMethod(); //Commenting it out since it's not needed
        shoppinglist.setandgetNameandBudget();
        shoppinglist.setQuantity();
        shoppinglist.getData();
        shoppinglist.goShopping();
        shoppinglist.showPending();
        shoppinglist.showPurchased();
        shoppinglist.WriteTextFile();
    }
}