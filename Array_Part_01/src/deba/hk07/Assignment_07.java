package deba.hk07;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * 7. Develop a program to create an array of integers, REMOVE the element 2, FROM THE ARRAY 
     Support if an array has values 3, 4, 5, 2, 6 after remove 2 array must be
 O/P: [3, 4, 5, 2, 6] 
 O/P: [3, 4, 5, 6] 
 */
public class Assignment_07 {

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
		int count = 0;
		for (int index = 0; index < ia.length; index++) {
			if (ia[index] == 2)
				count++;
		}
		// create a new array for copy
		int[] updated = new int[ia.length - count];

		// copying non '2' values to updated array
		for (int index = 0, j = 0; index < ia.length; index++) {
			if (ia[index] != 2) {
				updated[j] = ia[index];
				j++;
			}
		}

		System.out.println(Arrays.toString(ia));
		System.out.println(Arrays.toString(updated));
	}

	public static void executeUsingStreams() {
		// stream api
		IntStream.range(0, ia.length)
		.filter(i -> ia[i] != 2)
		.forEach(i -> System.out.println(ia[i]));
	}

	public static void resourceClose() {
		sc.close();
	}

}
