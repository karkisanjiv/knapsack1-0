/**
 * Used to test the functionality of the DPKnapsack class
 *
 * @author  J Cordova
 * @version 1.0
 *
 */
   public class DPKTest
	{			
	   public static void main(String[] args) 
		{
         final int DEFAULT_CAPACITY =10;
         final int LARGER_CAPACITY = 75;
         
         DPKnapsack dpk = new DPKnapsack(DEFAULT_CAPACITY, "10products.txt");
         
			System.out.println(dpk.optimalWeight());        
			System.out.println(dpk.optimalNumber());       
			System.out.println(dpk.contains("watch"));
         System.out.println(dpk.contains("hammer"));
			System.out.println(dpk.solution());   
			
         System.out.println(dpk.optimalWeight(LARGER_CAPACITY));        
			System.out.println(dpk.optimalNumber(LARGER_CAPACITY));       
			System.out.println(dpk.contains("watch", LARGER_CAPACITY));
         System.out.println(dpk.contains("hammer", LARGER_CAPACITY));
			System.out.println(dpk.solution(LARGER_CAPACITY));
      }
   }