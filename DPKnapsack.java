/**
 * @author Sanjiv Karki
 * Due Date: March 13, 2017
 */
 
import java.util.*;
import java.io.*;

/**
 * This DPKnapsack class uses different arraylist that implements a dynamic programming solution to 0-1
 * knapsack problem.
 */
public class DPKnapsack
{
   /**
    * Initializes arraylist and other necessary variables.
    */
   private int capacity;
   private String fileName;
   public List<String> itemName  = new ArrayList<String>();
   public List<Integer> itemWeight = new ArrayList<Integer>();
   public List<Integer> itemValue  = new ArrayList<Integer>();
   public List itemsSelected;
   public int globalTotalWeight=0;
   public int[][] twoDarr; 
   int numberOfSelectedItem=0;
   int countItems=0;
   
   /**
    * Constructor to create the knapsack table for the default capacity and obtain optimal solution subset
    * @param capacity  the maximum capacity of the knapsack table inorder to have the maximal profit
    * @param itemFile  the file whose data are processed inorder to calculate the knapsack table to obtain optimal
    * solution subset
    */
   DPKnapsack(int capacity, String itemFile)
   {
      this.capacity = capacity;
      fileName = itemFile;
      String str;
      String strArr[];
      
      /**
       * This try block opens the file. If successful this block is executed,
       * otherwise control is transmitted to catch block.
       */
       
      try
      {
         File inputFile = new File (fileName);
         Scanner in = new Scanner (inputFile);
         
         while(in.hasNext())
         {
            str = in.nextLine();
            strArr = str.split(" ");
            itemName.add(strArr[0]);
            itemWeight.add(Integer.parseInt(strArr[1]));
            itemValue.add(Integer.parseInt(strArr[2]));
            countItems++;       
         }
                  
         
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found."+e);
      }
      globalTotalWeight=knapsackItem(getCapacity());
   }
   
   /**
    * This private method returns the capacity of the knapsack table
    * @return Returns the capacity.
    */
    
   private int getCapacity()
   {
      return this.capacity;
   }
   
   /**
    * This private method creates the knapsack Table and returns the bottom right value of the table
    * @param cap the capacity of the knapsack Table.
    * @return Returns an integer.  
    */ 
   private int knapsackTableMax(int cap)
   {
      int W = cap;
	   twoDarr = new int[countItems+1][W+1];
      
	   for(int i = 0; i <= countItems; i++)
	   {
	     for(int w = 0; w <= W; w++)
	     {
	         if (i==0 || w==0)
	              twoDarr[i][w] = 0;
	         else if (itemWeight.get(i-1) <= w)
	               twoDarr[i][w] = Math.max(itemValue.get(i-1) + twoDarr[i-1][w-itemWeight.get(i-1)],  twoDarr[i-1][w]);
	         else
	               twoDarr[i][w] = twoDarr[i-1][w];
	     }
	   }
     return twoDarr[countItems][W];
   }
   
   /**
    * This private method selects the item from the knapsack table and returns the total weight of selected item.
    * @param cap the capacity of knapsack table
    * @return Returns an integer.
    */
   private int knapsackItem(int cap)
   {
      knapsackTableMax(cap);
      numberOfSelectedItem=0;
      itemsSelected = new ArrayList<>();
      int a = itemWeight.size();
      int b = cap;
      int totalWeight = 0;
      
      
      while (a>0 && b>0)
      {
         if (twoDarr[a][b] != twoDarr[a-1][b])
         {              
            itemsSelected.add(itemName.get(a-1));
            totalWeight += itemWeight.get(a-1);
            b = b-itemWeight.get(a-1);
            a = a-1;
            numberOfSelectedItem++;
         }
         else
         {
            a=a-1;
         }
      }
      return totalWeight;
   }
   
   /**
    * This method returns the overall weight of the items in the optimal solution subset
    * @return Returns the overall weight of the items.
    */
   public int optimalWeight()
   {
      return globalTotalWeight;
   }
   
   /**
    * This method returns the number of items in the optimal solution subset
    * @return Returns the number of item selected as Integer.
    */
   public int optimalNumber()
   {
      return numberOfSelectedItem;
      
   }
   
   /**
    * This method returns true if item is in the optimal solution subset, false otherwise.
    * @param item the name of item to be checked if selected.
    * @return true if the optimal solution subset contains the item otherwise false
    */
   public boolean contains(String item)
   {
      if(itemsSelected.contains(item))
         return true;
      return false;
   }
   
   /**
    * This method returns the information of the items that are selected and their corresponding value
    * and weight.
    * @return Returns the all informations in properly formatted form.
    */
   public String solution()
   {
      String result="";
      System.out.println();
      System.out.println("Items selected in the optimal solution subset are given below;\n");
      System.out.println(String.format("%-20s %-15s %-20s\n ","Items Selected","Item Value","Item Weight"));
      for(int i=0; i<itemsSelected.size(); i++)
      {
         for(int j=0; j<itemName.size(); j++)
         {
            if(itemsSelected.get(i)==itemName.get(j))
            {
               result+=String.format("%-20s %-15s %-8s\n",itemName.get(j),itemValue.get(j),itemWeight.get(j));
            }
         }
      }
      return result; 
   }
    
   /**
    * This method overloads the optimalWeight method and returns the overall weight of the items 
    * in the optimal solution subset for a knapsack.
    * @param maxWeight the capacity of knapsack table.
    * @return Returns the overall weight of items selected.
    */
   public int optimalWeight(int maxWeight)
   {
      return knapsackItem(maxWeight);
   }
   
   /**
    * This method overloads the optimalNumber method and returns the number of the items 
    * in the optimal solution subset for a knapsack.
    * @param maxWeight the capacity of knapsack table.
    * @return Returns the number of selected item from knapsack table as integer.
    */

   public int optimalNumber(int maxWeight)
   {
      knapsackItem(maxWeight);
      return numberOfSelectedItem;
   }
   
   /**
    * This method overloads the contains method and returns true if item is in the optimal solution subset for a knapsack with maxWeight, false otherwise.
    * @param maxWeight the capacity of knapsack table.
    * @param item the name of item.
    * @return Returns a boolean.
    */

   public boolean contains(String item, int maxWeight)
   {
     knapsackItem(maxWeight);
     if(itemsSelected.contains(item)){
     return true;}
     return false;
   }
   
   /**
    * This method overloads the solution method and returns the information of the items that are selected and their corresponding value
    * and weight.
    * @param maxWeight the capacity of knapsack table.
    * @return a solution.
    */ 
   public String solution(int maxWeight)
   {
      knapsackItem(maxWeight);  
      return solution();
   }
   }
