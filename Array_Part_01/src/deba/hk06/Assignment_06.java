package deba.hk06;

import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * 7. Develop a program to create an array of integers, search for the element 2, 
  if available display "its index number" to tell that value 2 is present in array at so and so location
  if not available display "-1" to tell that value 2 is not present in array 
     Support if an array has values 3, 4, 5, 2, 6 you must display 
 O/P: 3 
     Support if an array has values 3, 4, 5, 6, 7 you must display 
 O/P: -1
 */
public class Assignment_06 {

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
		// check for value '2'
		for (int index = 0; index < ia.length; index++) {
			if (ia[index] == 2)
				System.out.println("2 is present at index " + (index + 1));
		}
	}

	public static void executeUsingStreams() {
		// stream api

		IntStream.range(0, ia.length).forEach(i -> {
			if (ia[i] == 2)
				System.out.println("2 is present at " + (i + 1) + " location");
			else
				System.out.println("2 is not present at location " + (i + 1));
		});

		// OR

		IntStream.range(0, ia.length).filter(i -> ia[i] == 2).forEach(i -> {
			if (ia[i] == 2)
				System.out.println("The 2 is present at location " + (i + 1));
		});

		long count = IntStream.range(0, ia.length).filter(i -> ia[i] == 2).count();

		if (count < 0)
			System.out.println(-1);
	}

	public static void resourceClose() {
		sc.close();
	}
}
