/*Author: Guillermo Ruiz-Rico
Class: CSC232 at Boston University*/

import java.util.Scanner;


public abstract class GRR_FifthAssignment_Abstract_Class /** This is the base abstract class, used to set high level parameters applicable to the shopping list*/
{
    private Integer numberofitems;
    private String shopperName;
    private String stringbankAccountdollars;
    private double bankAccount;
    Scanner keyboard = new Scanner(System.in);

    public GRR_FifthAssignment_Abstract_Class() /** Below the by-default constructors */
    {
        numberofitems = 4;
        shopperName = "No Name Given Yet";
        bankAccount = 0;
    }

    public int getNumberOfItems() {
        return numberofitems;
    }

    public void setBankAccount() throws Exception
    {
        try
        {
            stringbankAccountdollars = keyboard.next();
            bankAccount = Double.parseDouble(stringbankAccountdollars.replaceAll("[^0-9.]", "")); // Please note all negative values will be converted to positives.
        }
        catch (Exception e)
        {
            System.out.println("\nError entering the numeric values. Please start over.");
            System.exit(0);
        }
    }

    public double getBankAccount() {
        return bankAccount;
    }

    public void setShopperName() throws Exception
    {
        try
        {
            shopperName = keyboard.next();
            shopperName += keyboard.nextLine();
            shopperName = (shopperName.replaceAll("[^A-z]", ""));
        }
        catch (Exception e)
        {
            System.out.println("\nError entering the your name. Please start over.");
            System.exit(0);
        }

    }

    public String getShopperName() {
        return shopperName;
    }

}