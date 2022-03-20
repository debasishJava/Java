package deba.hk04;

import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * 4. Develop a program to create an array of integers, display only the elements divisible by 3
     Support if an array has values 3, 4, 5, 6, 7 you must display values "/ 3 " 3, 6
                                                    0  1  2  3  4
 */
public class Assignment_04 {

	private static Scanner sc = new Scanner(System.in);
	private static int[] ia = null;

	public static void main(String[] args) {
		// calling to initialize array
		System.out.println("Enter length of array : ");
		initializeArray(sc.nextInt());

		divisibleByThree();

		System.out.println("======================================");

		divisibleByThreeUsingStreams();

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

	public static void divisibleByThree() {
		// print divisible by 3
		for (int i = 0; i < ia.length; i++) {
			if (ia[i] % 3 == 0) {
				System.out.println("value at " + i + " index: " + ia[i]);
			}
		}
	}

	public static void divisibleByThreeUsingStreams() {
		// stream api
		IntStream.range(0, ia.length)
		.filter(i -> ia[i] % 3 == 0)
		.mapToObj(i -> "value at index " + i + " is: " + ia[i])
		.forEach(System.out::println);
	}

	public static void resourceClose() {
		sc.close();
	}

}
