/*Author: Guillermo Ruiz-Rico
Class: CSC232 at Boston University*/

/* We get selenium into intellij following these steps https://saucelabs.com/resources/articles/getting-started-with-webdriver-in-java-using-intellij-on-windows
https://chromedriver.storage.googleapis.com/index.html?path=2.43/
https://stackoverflow.com/questions/20579007/get-href-value-webdriver
https://stackoverflow.com/questions/29078072/how-to-get-link-value-inside-href?rq=1
https://examples.javacodegeeks.com/core-java/xml/xpath/using-xpath-selenium-example
https://www.guru99.com/first-webdriver-script.html
https://examples.javacodegeeks.com/enterprise-java/selenium/selenium-tutorial-for-beginners/
https://testingbot.com/support/getting-started/java.html
https://stackoverflow.com/questions/20579007/get-href-value-webdriver
https://stackoverflow.com/questions/29078072/how-to-get-link-value-inside-href?rq=1
https://groups.google.com/forum/#!topic/selenium-users/ZUi9cenA13o
http://www.randomtriviagenerator.com/#/category/science - uses JS but good alternative
We use xPath helper https://stackoverflow.com/questions/34829329/how-to-open-a-link-in-new-tab-chrome-using-selenium-webdriver
https://stackoverflow.com/questions/17668329/selenium-webdriver-webelement-iterator-cannot-be-resolved-to-a-type
https://stackoverflow.com/questions/16154318/how-to-check-all-elements-in-a-ul-list-using-selenium-webdriver -- making alist.
https://www.geeksforgeeks.org/robot-class-java-awt/
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.EOFException;
import java.util.List;
import java.io.File;
import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import org.openqa.selenium.WebElement;



public class GRR_FifthAssignment_Class_and_Methods extends GRR_FifthAssignment_Abstract_Class implements GRR_FifthAssignment_Print_Interface
/** This is the class where the majority of methods are defined and the object array created since the Abstract Class does not allow creating objects.*/
{
        /**Access the private variables' values from the abstract class */
        private int numberofitems = getNumberOfItems();
        private String myfileName = "myShoppingList.txt";
        private String itemname;
        private String prioritystringnumber;
        private Integer prioritynumber;
        private String quantitystringnumber;
        private Integer quantitynumber;
        private String priorityandname;
        private double itemprice;
        private String purchasedflag;
        private String realItemChoiceString;
        private Integer realItemChoiceNumber;
        private String realItemChosenProduct;
        private String realItemChoiceStoreName;
        private String realItemChoicePriceString;
        private String sortedflag;
        private String notfoundflag;
        List<GRR_FifthAssignment_Class_and_Methods> itemobjectarray;
        private String[] Target_RealItemName = new String[3];
        private String[] Target_RealItemPrice = new String[3];
        private Integer[] Target_RealItemNumber = new Integer[3];
        private String[] Walmart_RealItemName = new String[3];
        private String[] Walmart_RealItemPrice = new String[3];
        private Integer[] Walmart_RealItemNumber = new Integer[3];
        private String[] Wegmans_RealItemName = new String[3];
        private String[] Wegmans_RealItemPrice = new String[3];
        private Integer[] Wegmans_RealItemNumber = new Integer[3];



        public GRR_FifthAssignment_Class_and_Methods() /** Below the by-default constructors */
        {
            itemname = "By-default value - please type in the name";
            prioritynumber = 0;
            priorityandname = "0 none";
            itemprice = -1.00;
            purchasedflag = "N";
            sortedflag = "N";
            quantitynumber = 0;
            realItemChoiceString = "1";
            realItemChoiceStoreName = "No Chosen Store  Yet";
            realItemChosenProduct = "No Chosen Product Yet";
            notfoundflag = "N";
        }



    public void ReadTextFile() /**Locate existing shopping list file and print it. */
    {
        Scanner inputStream = null;
        System.out.println("\nThis is a program used to generate and prioritize a shopping list. First, Let's take a look at the existing shopping list.");

        try
        {
            inputStream = new Scanner(new File(myfileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("\nError opening the below file\n: " + myfileName + "program will exit now. Thank you.");
            System.exit(0);
        }
        while (inputStream.hasNextLine())
        {
            String line = inputStream.nextLine();
            System.out.println(line);
        }
        System.out.println("\n\n\n");
        inputStream.close();
    }



    public void InputData() throws DuplicateException /** Gather item names and looks for duplicates. Exits the program if one is found */
    {
        System.out.println("\nNow let's create a new shopping list. Please type in an item's name. If a duplicate is entered, the program will exit.");
        itemobjectarray = new ArrayList<GRR_FifthAssignment_Class_and_Methods>(numberofitems);

        for (int i = 0; i < numberofitems; i++)
        {
            itemobjectarray.add(i, new GRR_FifthAssignment_Class_and_Methods());
            System.out.println("Please type in the name of the shopping list item #" + (i + 1) + " followed by Enter.");

            try
            {
                itemobjectarray.get(i).itemname = keyboard.next();
                itemobjectarray.get(i).itemname += keyboard.nextLine();

                int j = 0;
                while (j < i)
                {
                    if (itemobjectarray.get(i).itemname.equalsIgnoreCase(itemobjectarray.get(j).itemname))
                    {
                        throw new DuplicateException();
                    }
                    else
                    {
                        j++;
                    }
                }
                System.out.print("The \"" + itemobjectarray.get(i).itemname + "\" item name is new and therefore has no duplicates. Please continue - thank you.\n");
            }

            catch (DuplicateException e)
            {
                System.out.println(e.getMessage());
                System.out.print("This item name: \"" + itemobjectarray.get(i).itemname + "\" already exists. Please start over and enter unique items only. Thank you.");
                System.exit(0);
            }
        }
    }



    public void setPriority() throws OutOfRangeException /** Set the priority number via a for loop. String or special characters are automatically removed.
     Numbers out of range are checked for and if found the program exits. */
    {
        for (int i = 0; i < numberofitems; i++) {
            System.out.println("Please type in an integer representing the priority number (1 through 7) for \"" + itemobjectarray.get(i).itemname + "\" followed by Enter.");
            try
            {
                {
                    itemobjectarray.get(i).prioritystringnumber = keyboard.next();
                    itemobjectarray.get(i).prioritynumber = Integer.parseInt(itemobjectarray.get(i).prioritystringnumber.replaceAll("[^0-9]", "0"));

                    if (itemobjectarray.get(i).prioritynumber <= 0 || itemobjectarray.get(i).prioritynumber > (numberofitems))
                    {
                        throw new OutOfRangeException();
                    }
                    else
                    {
                        System.out.println("Priority number entry is within range and duplicates are now allowed.");
                    }
                    itemobjectarray.get(i).priorityandname = (itemobjectarray.get(i).prioritystringnumber + " | " + itemobjectarray.get(i).itemname);
                    System.out.println(itemobjectarray.get(i).priorityandname);
                }
            }
            catch (OutOfRangeException e)
            {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }



    public void setData() throws Exception /** Overwrite the initial priority and item name.*/
    {
        System.out.println ("Now that you've seen all entered items and numbers. This step gives you a chance to overwrite an item's name and priority number. \n To do so, first type its current existing item's name such as \"orange\" and press Enter." +
                "\nConfirmation will be submitted if found. Subsequently, type in the new priority number followed by space and that item's new name\n\n\n If you wish to leave all items unchanged, type in anything random and press Enter.");
        try
        {
            String finditem = keyboard.next();
            finditem += keyboard.nextLine();
            int j = 0;
            while (j < numberofitems) {
                if (finditem.equalsIgnoreCase(itemobjectarray.get(j).itemname)) {
                    System.out.print("The item name: \"" + itemobjectarray.get(j).itemname + "\" was found. Please type in the new priority number followed by Enter and the new item's name.");
                    itemobjectarray.get(j).prioritystringnumber = keyboard.next();
                    itemobjectarray.get(j).prioritynumber = Integer.parseInt(itemobjectarray.get(j).prioritystringnumber.replaceAll("[^0-9]", "0"));
                    itemobjectarray.get(j).itemname = keyboard.next();
                } else {
                    j++;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("\nError entering the item's name to be replaced. Let's move on.");
        }
    }



    public void customEqualsMethod() /** Check for equal names and priority numbers and prints all combinations.*/
    {
        System.out.println ("Next we will compare all names and priority numbers to each other and we will print booleans showing whether they're equals to others within the array.");
        for (int i = 0; i < numberofitems; i++)
        {
            int j = 0;
            while (j < numberofitems && j != i)
            {
                if (itemobjectarray.get(i).itemname.equalsIgnoreCase(itemobjectarray.get(j).itemname) && itemobjectarray.get(i).prioritynumber == itemobjectarray.get(j).prioritynumber)
                {
                    System.out.print(itemobjectarray.get(i).prioritynumber + " - " + itemobjectarray.get(i).itemname + " VS " + itemobjectarray.get(j).prioritynumber + " - " +  itemobjectarray.get(j).itemname + " =  True\n");
                    j++;
                }
                else
                {
                    System.out.print(itemobjectarray.get(i).prioritynumber + " - " + itemobjectarray.get(i).itemname + " VS " + itemobjectarray.get(j).prioritynumber + " - " +  itemobjectarray.get(j).itemname + " =  False\n");
                    j++;
                }
            }
        }
    }



    public void setandgetNameandBudget() throws Exception /** Print instructions on how to set the budget as well as the shopper's first and last name.*/
    {
        System.out.println("Please type in the whole dollar in your bank account's balance (aka budget) followed by Enter.\n i.e. If you have $53.45, type in \"53.45\"" +
                ". \nNegative values will be automatically converted to positive by removing the \"-\" character.");
        setBankAccount();
        System.out.println("Your bank account balance as recorded will be shown below:\n $"+ getBankAccount());
        System.out.println("\n\nNow, please type in your first name and/or last name - " +
                "followed by Enter. \nPlease note numbers, spaces and special characters will be automatically removed:");
        setShopperName();
        System.out.println("Your name as recorded will be shown below:\n" + getShopperName());
    }



    public void setQuantity() throws Exception/** Set the quantity number via a for loop. If a string, special character, or a negative number out of range is entered, the program exits. */
    {
        System.out.println("Now let's type in the quantity of each item you'd like to purchase (no greater than 999 or smaller than 1).");
        for (int i = 0; i < numberofitems; i++)
        {
            System.out.println("\nPlease type in an integer representing the quantity number (greater than 0 and smaller than 1000) for \"" + itemobjectarray.get(i).itemname + "\" followed by Enter.");
            try
            {
                itemobjectarray.get(i).quantitystringnumber = keyboard.next();
                itemobjectarray.get(i).quantitynumber = Integer.parseInt(itemobjectarray.get(i).quantitystringnumber.replaceAll("[^0-9]", "0"));
                if (itemobjectarray.get(i).quantitynumber <= 0 ||  itemobjectarray.get(i).quantitynumber >999)
                {
                    System.out.println("Quantity number entry is either a character, a zero, an integer greater than 999 or a negative number.\n Please start over. Thank you.");
                    System.exit(0);
                }
                else
                {
                    System.out.println("Quantity number entry is valid.");
                }
            }
            catch (Exception e)
            {
                System.out.println("Quantity number entry is either a character, a zero, an integer greater than 999 or a negative number.\n Please start over. Thank you.");
                System.exit(0);
            }
        }
    }



    public void getRealItems()  throws AWTException, Exception /** Connect to stores' websites and retrieve all relevant data based on user's initial shopping list */
    {
        // Declaration and instantiation of objects/variables
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\guillermo_ruiz-rico\\IdeaProjects\\CS232_Ruiz-Rico.Guillermo_Final_Presentation\\libs\\chromedriver.exe");
        String Target_BaseUrl = "https://www.target.com/s?searchTerm=";
        String Walmart_BaseUrl = "https://www.walmart.com/search/?cat_id=0&query=";
        String Wegmans_BaseUrl = "https://wegmans.com/search.html?searchKey=";

        WebDriver driver = new ChromeDriver();

        System.out.println("Now we will open 3 tabs in a browser. One for each store, Target, Walmart and Wegmans.");

        // Launch Chrome and go to the base URL
        driver.get(Target_BaseUrl + itemobjectarray.get(0).itemname);
        driver.manage().window().maximize();
        Robot r = new Robot();
        try
        {
            int k = 0;
            while (k < 2)
            {
                r.keyPress(KeyEvent.VK_CONTROL);
                r.keyPress(KeyEvent.VK_T);
                r.keyRelease(KeyEvent.VK_CONTROL);
                r.keyRelease(KeyEvent.VK_T);
                k++;
            }
            ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
            System.out.println(tabs);
            driver.switchTo().window(tabs.get(1)).get(Walmart_BaseUrl + itemobjectarray.get(0).itemname);
            driver.switchTo().window(tabs.get(2)).get(Wegmans_BaseUrl + itemobjectarray.get(0).itemname);
            driver.switchTo().window(tabs.get(0));

            for(int i=0; i<numberofitems; i++)
            {   //Target's
                if (i==0)
                {
                    driver.switchTo().window(tabs.get(0));
                }
                else
                {
                    driver.switchTo().window(tabs.get(0)).get(Target_BaseUrl + itemobjectarray.get(i).itemname);
                }
                try
                {
                    List<WebElement> TargetItemNames = driver.findElements(By.xpath("//a[@class='h-display-block h-text-bold h-text-bs flex-grow-one styles__StyledTitleLink-ytfmhe-5 hDEDqu Link-sc-1m0vfdz-0 biVtQF']"));
                    List<WebElement> TargetItemPrices = driver.findElements(By.xpath("//div[@class='styles__StyledPricePromoWrapper-ytfmhe-13 jGexTL']/span[@class='h-text-bs']"));
                    for (int j = 0; j < 3; j++)
                    {
                        itemobjectarray.get(i).Target_RealItemNumber[j] = (j + 1);
                        itemobjectarray.get(i).Target_RealItemName[j] = TargetItemNames.get(j).getText();
                        itemobjectarray.get(i).Target_RealItemPrice[j] = TargetItemPrices.get(j).getText();
                    }
                }
                catch(Exception e)
                {
                    for (int j = 0; j < 3; j++)
                    {
                        itemobjectarray.get(i).Target_RealItemNumber[j] = (j + 1);
                        itemobjectarray.get(i).Target_RealItemName[j] = "Item not found";
                        itemobjectarray.get(i).Target_RealItemPrice[j] = "Item not found";
                    }

                }

                //Walmart's
                if (i==0)
                {
                    driver.switchTo().window(tabs.get(1));
                }
                else
                {
                    driver.switchTo().window(tabs.get(1)).get(Walmart_BaseUrl + itemobjectarray.get(i).itemname);
                }
                try
                {
                List<WebElement> WalmartItemNames = driver.findElements(By.xpath("//a[@class='product-title-link line-clamp line-clamp-2']/span"));
                List<WebElement> WalmartItemCurrency = driver.findElements(By.xpath("//div[@class='price-main-block']/div[@class='product-price-ppu']/span[@class='price display-inline-block arrange-fit price price-main']/span[@class='price-group']/span[@class='price-currency']"));
                List<WebElement> WalmartItemPrices = driver.findElements(By.xpath("//div[@class='price-main-block']/div[@class='product-price-ppu']/span[@class='price display-inline-block arrange-fit price price-main']/span[@class='price-group']/span[@class='price-characteristic']"));
                List<WebElement> WalmartItemCents = driver.findElements(By.xpath("//div[@class='price-main-block']/div[@class='product-price-ppu']/span[@class='price display-inline-block arrange-fit price price-main']/span[@class='price-group']/span[@class='price-mantissa']"));
                for (int j = 0; j < 3; j++)
                {
                    itemobjectarray.get(i).Walmart_RealItemNumber[j] = (j+4);
                    itemobjectarray.get(i).Walmart_RealItemName[j] = WalmartItemNames.get(j).getText();
                    itemobjectarray.get(i).Walmart_RealItemPrice[j] = WalmartItemCurrency.get(j).getText()+WalmartItemPrices.get(j).getText()+"."+WalmartItemCents.get(j).getText();
                }
                }
                catch(Exception e)
                {
                    for (int j = 0; j < 3; j++)
                    {
                        itemobjectarray.get(i).Walmart_RealItemNumber[j] = (j+4);
                        itemobjectarray.get(i).Walmart_RealItemName[j] = "Item not found";
                        itemobjectarray.get(i).Walmart_RealItemPrice[j] = "Item not found";
                    }
                }

                //Wegmans
                if (i==0)
                 {
                    driver.switchTo().window(tabs.get(2));
                }
                else
                {
                    driver.switchTo().window(tabs.get(2)).get(Wegmans_BaseUrl + itemobjectarray.get(i).itemname);
                }
                try
                {
                    List<WebElement> WegmansItemNames = driver.findElements(By.xpath("//h5/a[@class='line-ellipse ng-scope tooltipstered']/span[@class='ng-binding']"));
                    List<WebElement> WegmansItemPrices = driver.findElements(By.xpath("//h5[@class='ng-scope']/span[@class='ng-binding']"));
                    for (int j = 0; j < 3; j++)
                    {
                        itemobjectarray.get(i).Wegmans_RealItemNumber[j] = (j + 7);
                        itemobjectarray.get(i).Wegmans_RealItemName[j] = WegmansItemNames.get(j).getText();
                        itemobjectarray.get(i).Wegmans_RealItemPrice[j] = WegmansItemPrices.get(j).getText();
                    }
                }
                catch(Exception e)
                {
                    for (int j = 0; j < 3; j++)
                    {
                        itemobjectarray.get(i).Wegmans_RealItemNumber[j] = (j + 7);
                        itemobjectarray.get(i).Wegmans_RealItemName[j] = "Item not found";
                        itemobjectarray.get(i).Wegmans_RealItemPrice[j] = "Item not found";
                    }
                }
            }

            //Closes all tabs at the end of the loop
            for(int i=0; i<tabs.size(); i++)
            {
                driver.switchTo().window(tabs.get(i)).close();
            }
        }
        catch (Exception e)
            {
                    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
                    System.out.println("Browser's operations failed. Please start over and do not press anything next time: " + tabs.size());
                    for (int i = 0; i < tabs.size(); i++)
                    {
                        driver.switchTo().window(tabs.get(i)).close();
                    }
                    System.exit(0);
            }
    }


    public void printRealItemsAndChoose() /**Show options and allow for selection*/
    {
        System.out.println("\n\n\n\n\n\n\n Now let's print all of our retrieved data from each store");
        for (int i = 0; i < numberofitems; i++)
        {
        System.out.println("See below for options pertaining to item:  " + itemobjectarray.get(i).itemname + "\n");

        System.out.println("Target's:");
            for (int j = 0; j < 3; j++)
            {
                System.out.println("Option #: " + itemobjectarray.get(i).Target_RealItemNumber[j] + " |  Description: " + itemobjectarray.get(i).Target_RealItemName[j] +" | Price: " +itemobjectarray.get(i).Target_RealItemPrice[j]);
            }

            System.out.println("Walmart's:");
            for (int j = 0; j < 3; j++)
            {
                System.out.println("Option #: "+ itemobjectarray.get(i).Walmart_RealItemNumber[j] +  " |  Description: " + itemobjectarray.get(i).Walmart_RealItemName[j] + " | Price: "  +itemobjectarray.get(i).Walmart_RealItemPrice[j]);
            }

            System.out.println("Wegmans':");
            for (int j = 0; j < 3; j++)
            {
                System.out.println("Option #: "+ itemobjectarray.get(i).Wegmans_RealItemNumber[j] + " |  Description: " + itemobjectarray.get(i).Wegmans_RealItemName[j] + " | Price: "  +itemobjectarray.get(i).Wegmans_RealItemPrice[j]);
            }
        System.out.println("--> End of all offerings for product:  " + itemobjectarray.get(i).itemname + "\n\n");
        System.out.println("Please make your choice by typing in an integer between 1 and 9 and pressing Enter.");
        itemobjectarray.get(i).realItemChoiceString = keyboard.next();

        try
        {
            {
                itemobjectarray.get(i).realItemChoiceNumber = Integer.parseInt(itemobjectarray.get(i).realItemChoiceString.replaceAll("[^0-9]", ""));
                if (itemobjectarray.get(i).realItemChoiceNumber <= 0 || itemobjectarray.get(i).realItemChoiceNumber > 9)
                {
                    throw new OutOfRangeException();
                }
                else
                {
                    System.out.println("Choice number \""+itemobjectarray.get(i).realItemChoiceNumber+"\"is within range. The selected store, item name and price have been recorded\n");
                }
            }
        }
        catch (OutOfRangeException e)
            {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }



    public void recordChoices()/** Record choices into the actual array object */
    {
        System.out.println("Please see below for selected items, price and store name: ");
        try
        {
            for (int i = 0; i < numberofitems; i++)
            {
                int j = 0;
                while (j < 3)
                {
                    if (((itemobjectarray.get(i).realItemChoiceNumber) == itemobjectarray.get(i).Target_RealItemNumber[j]))
                    {
                        itemobjectarray.get(i).realItemChoicePriceString = itemobjectarray.get(i).Target_RealItemPrice[j];
                        itemobjectarray.get(i).itemprice = Double.parseDouble(itemobjectarray.get(i).Target_RealItemPrice[j].replaceAll("[^0-9.]", "0"));
                        itemobjectarray.get(i).realItemChosenProduct = itemobjectarray.get(i).Target_RealItemName[j];
                        itemobjectarray.get(i).realItemChoiceStoreName = "Target";
                        j++;
                    }

                    else if ((itemobjectarray.get(i).realItemChoiceNumber == (itemobjectarray.get(i).Walmart_RealItemNumber[j])))
                    {
                        itemobjectarray.get(i).realItemChoicePriceString = itemobjectarray.get(i).Walmart_RealItemPrice[j];
                        itemobjectarray.get(i).itemprice = Double.parseDouble(itemobjectarray.get(i).Walmart_RealItemPrice[j].replaceAll("[^0-9.]", "0"));
                        itemobjectarray.get(i).realItemChosenProduct = itemobjectarray.get(i).Walmart_RealItemName[j];
                        itemobjectarray.get(i).realItemChoiceStoreName = "Walmart";
                        j++;
                    }
                    else if ((itemobjectarray.get(i).realItemChoiceNumber == (itemobjectarray.get(i).Wegmans_RealItemNumber[j])))
                    {
                        itemobjectarray.get(i).realItemChoicePriceString = itemobjectarray.get(i).Wegmans_RealItemPrice[j];
                        itemobjectarray.get(i).itemprice = Double.parseDouble(itemobjectarray.get(i).Wegmans_RealItemPrice[j].replaceAll("[^0-9.]", "0"));
                        itemobjectarray.get(i).realItemChosenProduct = itemobjectarray.get(i).Wegmans_RealItemName[j];
                        itemobjectarray.get(i).realItemChoiceStoreName = "Wegmans";
                        j++;
                    }
                    else
                    {
                        j++;
                    }
                }
                if  (itemobjectarray.get(i).realItemChosenProduct.equalsIgnoreCase("Item not found"))
                {
                    System.out.println("Item \"" + itemobjectarray.get(i).itemname + "\" was not found in the selected store: " + itemobjectarray.get(i).realItemChoiceStoreName+ "\n");
                    itemobjectarray.get(i).notfoundflag = "Y";
                }
                else
                {
                    System.out.println("For item \"" + itemobjectarray.get(i).itemname + "\" you will be buying:  " + itemobjectarray.get(i).realItemChosenProduct + " for " +itemobjectarray.get(i).realItemChoicePriceString + " a piece at " + itemobjectarray.get(i).realItemChoiceStoreName+ "\n");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("\n\n\n\n There is something wrong with the extracted data from the website. Please check before starting over. Thank you.");
            System.exit(0);
        }
    }



    public void getData() /** Create an array with the priority number and its name (already verified). Subsequently, sorts it using the bubble method and prints them all
     We use the "sortedflag" instance variable to avoid printing twice items with the same priority number*/
    {
        System.out.println ("\n\n\n\nBefore we go shopping let's take a look at our bank account. \n\n" + getShopperName() + "'s Account\nBalance: $" + getBankAccount());
        System.out.println ("\n\nAlso, please see below for the current shopping list. Sorted by the priority numbers\nPriority # | Item Name | Real Product Name | Quantity | Price | Store Name | Purchased Flag\n___________________________________________________________________________________________\n");
        int sortedlist[] = new int[numberofitems];
        for (int i = 0; i < numberofitems; i++) //Retrieves the priority numbers and copies them over an array
        {
            sortedlist[i] = itemobjectarray.get(i).prioritynumber;
        }
        for (int i = 0; i < numberofitems-1; i++)// Performs bubble sorting
            for (int j = 0; j < numberofitems-i-1; j++)
                if (sortedlist[j] > sortedlist[j+1])
                {
                    int placeholder = sortedlist[j];
                    sortedlist[j] = sortedlist[j+1];
                    sortedlist[j+1] = placeholder;
                }
        for (int i = 0; i < numberofitems; i++)//Prints the shopping list priority numbers followed by the item names. Similar methodology as used in the goShopping() method
        {
            int j = 0;
            while (j < numberofitems)
            {
                if ((sortedlist[i] == itemobjectarray.get(j).prioritynumber && itemobjectarray.get(j).sortedflag == "N"))
                {
                    System.out.print(itemobjectarray.get(j).prioritynumber + " | " + itemobjectarray.get(j).itemname + " | " + itemobjectarray.get(j).realItemChosenProduct + " | " + itemobjectarray.get(j).quantitynumber + " | " + "$"+itemobjectarray.get(j).itemprice +   " | " +  itemobjectarray.get(j).realItemChoiceStoreName+ " | " + itemobjectarray.get(j).purchasedflag + "\n");
                    itemobjectarray.get(j).sortedflag = "Y";
                    j++;
                }
                else
                {
                    j++;
                }
            }
        }
    }



    public void goShopping() /** Go shopping and flag the items when purchased. */
        {
            double bankAccount = getBankAccount();
            Integer sortedlist[] = new Integer[numberofitems];
            for (int i = 0; i < numberofitems; i++)
            {
                sortedlist[i] = itemobjectarray.get(i).prioritynumber;
            }
            Arrays.sort(sortedlist); //Please note that the bubble method is used in the getData() method. The built-in java feature is used here for speediness
            System.out.print("\n\nNow let's go shopping: \n");
            for (int i = 0; i < numberofitems; i++)
            {
                int j = 0;
                while (j < numberofitems)
                {
                    if ((sortedlist[i] == itemobjectarray.get(j).prioritynumber) && (bankAccount >= (itemobjectarray.get(j).itemprice*itemobjectarray.get(j).quantitynumber)) && (itemobjectarray.get(j).purchasedflag == "N") && (itemobjectarray.get(j).notfoundflag == "N"))
                    {
                        {
                            bankAccount = bankAccount - (itemobjectarray.get(j).itemprice*itemobjectarray.get(j).quantitynumber);
                            itemobjectarray.get(j).purchasedflag = "Y";
                            System.out.print("The \"" + itemobjectarray.get(j).itemname + "\" item with a price of $" + itemobjectarray.get(j).itemprice + " and a quantity of "+ itemobjectarray.get(j).quantitynumber + ", has been purchased and flagged as such.\n There is $"+ bankAccount + " left in your wallet.\n");
                            j++;
                        }
                    }
                    else
                    {
                        j++;
                    }
                }
            }
            System.out.println ("\n--> Shopping is done. Please come back after breaking the bank in order to continue buying the remaining items in the shopping list (if any).\n\nYour remaining balance is $" + bankAccount + ".");
        }



    public void showPending() /** Show what's pending for  purchase */
    {
        System.out.println ("\n Remaining items in the shopping list: \n");
        for (int i = 0; i < numberofitems; i++)
        {
            if ((itemobjectarray.get(i).purchasedflag).equals("N"))
            {
                System.out.println (itemobjectarray.get(i).prioritynumber + " | " + itemobjectarray.get(i).itemname + " | " + itemobjectarray.get(i).realItemChosenProduct + " | " + itemobjectarray.get(i).quantitynumber + " | " + "$"+itemobjectarray.get(i).itemprice + " | " +  itemobjectarray.get(i).realItemChoiceStoreName + " | " + itemobjectarray.get(i).purchasedflag + "\n");
            }
        }
        System.out.println ("\n--> End of list of outstanding items (if any). These will be saved in the text file.\n");
    }

    public void showPurchased() /** Show what was purchased */
    {
        System.out.println ("\n\n A table of purchased items will be displayed below:\n");
        for (int i = 0; i < numberofitems; i++)
        {
            if ((itemobjectarray.get(i).purchasedflag).equals("Y"))
            {
                System.out.println (itemobjectarray.get(i).prioritynumber + " | " + itemobjectarray.get(i).itemname +" | " + itemobjectarray.get(i).realItemChosenProduct + " | " + itemobjectarray.get(i).quantitynumber + " | " +"$"+itemobjectarray.get(i).itemprice + " | "  +  itemobjectarray.get(i).realItemChoiceStoreName + " | " + itemobjectarray.get(i).purchasedflag + "\n");
            }
        }
        System.out.println ("\n--> End of list of purchased items.\n");
    }



    public  void WriteTextFile()/** Overwrite text file with what's pending for purchase and include the shopper's name. */
    {
        System.out.println("To finish this program we will proceed to printing any outstanding items into the notepad.");
        try
        {
            PrintWriter PrintWriter = new PrintWriter(new FileWriter(myfileName));
            PrintWriter.println(getShopperName()+"'s Shopping List:");
            PrintWriter.println("Priority # | Item Name | Real Product Name | Quantity | Price | Store Name" );

            for (int i =0; i<numberofitems; i++)
            {
                if ((itemobjectarray.get(i).purchasedflag).equals("N"))
                {
                    PrintWriter.println(itemobjectarray.get(i).prioritynumber + " | " + itemobjectarray.get(i).itemname + " | " + itemobjectarray.get(i).realItemChosenProduct + " | " + itemobjectarray.get(i).quantitynumber + " | " + itemobjectarray.get(i).itemprice + " | " +  itemobjectarray.get(i).realItemChoiceStoreName);
                }
            }
            PrintWriter.println("\n\n --> End of shopping list");
            PrintWriter.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Problem opening the file " + myfileName);
        }
        catch (EOFException e)
        {
            System.out.println("Problem reading the file " + myfileName);
            System.out.println("Reached end of the file.");
        }
        catch (IOException e)
        {
            System.out.println("Problem reading the file " + myfileName);
        }
    }
}