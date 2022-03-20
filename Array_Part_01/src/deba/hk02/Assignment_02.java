package deba.hk02;

import java.util.Scanner;
import java.util.stream.IntStream;

/*
 *  2. Develop a program to create an array of integers, display only even indexes elements
     Support if an array has values 3, 4, 5, 6, 7 you must display values "even index" values 3 5 7
                                                    0  1  2  3  4
 */
public class Assignment_02 {

	private static Scanner sc = new Scanner(System.in);
	private static int[] ia = null;

	public static void main(String[] args) {

		// calling to initialize array
		System.out.println("Enter length of array : ");
		initializeArray(sc.nextInt());

		displayEven();
		
		System.out.println("================================================================");

		displayEvenUsingStreams();
		
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

	public static void displayEven() {
		// print even number
		for (int i = 0; i < ia.length; i++) {
			System.out.println("value at " + i + " index: " + ia[i]);
			i++;
		}
	}

	public static void displayEvenUsingStreams() {
		// stream api
		IntStream.range(0, ia.length)
		.filter(i -> i % 2 == 0)
		.mapToObj(i -> ia[i])
		.forEach(System.out::println);
	}

	public static void resourceClose() {
		sc.close();
	}
}
