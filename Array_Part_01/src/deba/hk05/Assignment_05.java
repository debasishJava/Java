package deba.hk05;

import java.util.Scanner;
import java.util.stream.IntStream;

/*
 *5. Develop a program to create an array of integers, search for the element 2, 
 if available display "the value 2 is available" 
 if no available display "the value 2 is not available" 
     Support if an array has values 3, 4, 5, 6, 7 you must display 
      O/P: the value 2 is not available
     Support if an array has values 3, 4, 5, 2, 6 you must display 
      O/P: the value 2 is available 
 */

public class Assignment_05 {

	private static Scanner sc = new Scanner(System.in);
	private static int[] ia = null;

	public static void main(String[] args) {
		// calling to initialize array
		System.out.println("Enter length of array : ");
		initializeArray(sc.nextInt());

		execute();

		System.out.println("==============================================================");

		executeUsingStreams();

		resourceClose();

	}

	public static int[] initializeArray(int length) {

		ia = new int[length];

		for (int i = 0; i < ia.length; i++) {
			System.out.println("enter value for " + (i + 1) + " element");
			ia[i] = sc.nextInt();
		}

		return ia;
	}

	public static void execute() {
		// display availability of 2
		int count = 0;
		for (int i = 0; i < ia.length; i++) {
			if (ia[i] == 2)
				count++;
		}

		if (count > 0)
			System.out.println("2 is available");
		else
			System.out.println("2 is not available");
	}

	public static void executeUsingStreams() {
		long count1 = IntStream.range(0, ia.length).filter(i -> ia[i] == 2).count();

		if (count1 > 0)
			System.out.println("2 is available");
		else
			System.out.println("2 is not available");
	}

	public static void resourceClose() {
		sc.close();
	}
}
