package admin;

import java.util.Scanner;

public class InterestCalculationSB {
	
	  @SuppressWarnings("resource")
	public static void main(String args[]) {
	      
	        //creating scanner to accept principle, rate and time input form user
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Welcome in Java program to calculate Simple interest");
	      
	        System.err.println("Please enter principle amount :");
	        float amount = scanner.nextFloat();
	      
	        System.err.println("Enter time in years : ");
	        float time = scanner.nextFloat();
	      
	        System.out.println("Enter rate annually : ");
	        float rate = scanner.nextFloat();
	      
	        float interest = simpleInterest(amount, rate, time);
	      
	        System.out.println("Simple interested calculate by program is : " + interest);
	    }
	  
	    public static float simpleInterest(float principle, float rate, float time){
	        float interest = (principle*rate*time)/100;
	        return interest;
	    }
}
