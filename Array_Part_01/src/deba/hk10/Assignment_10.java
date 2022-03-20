package deba.hk10;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 10. Develop a program to create an array of integers, SORT the elements in array in Ascending order
     Support if an array has values 3, 4, 5, 2, 6 after sorting array must 
 O/P: [3, 4, 5, 2, 6] 
 O/P: [2, 3, 4, 5, 6]
 */
public class Assignment_10 {
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
		// before acending
		System.out.println("Before ascending: " + Arrays.toString(ia));

		for (int i = 0; i < ia.length; i++) {
			for (int j = i + 1; j < ia.length; j++) {
				int temp = 0;
				if (ia[i] > ia[j]) {
					temp = ia[i];
					ia[i] = ia[j];
					ia[j] = temp;

				}
			} // for
		} // for

		// after ascending
		System.out.println("after ascending: " + Arrays.toString(ia));
	}

	public static void executeUsingStreams() {
		// stream api
		Arrays.stream(ia).boxed().sorted().forEach(x -> System.out.print(x + " "));
	}

	public static void resourceClose() {
		sc.close();
	}

}
