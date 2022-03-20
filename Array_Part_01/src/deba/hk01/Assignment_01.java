package deba.hk01;

import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * 1. Develop a program to create an array of integers, display all 
 * values on console with their position
     starts with 1. Support if an array has values 3, 4, 5, 6, 7 you must display values as 
     Value 1: 3
     Value 2: 4
     Value 3: 5
     Value 4: 6
     Value 5: 7
 */
public class Assignment_01 {

	private static Scanner sc = new Scanner(System.in);
	private static int[] ia = null;

	public static void main(String[] args) {
		//calling to initialize array
		System.out.println("Enter length of array : ");
		initializeArray(sc.nextInt());
		
		//normal display
		display();
		

		System.out.println("====================================================================");
		
		//stream api
		usingStreams();

	}

	public static int[] initializeArray(int length) {
	
		ia = new int[length];

		for (int i = 0; i < ia.length; i++) {
			System.out.println("enter value for " + (i + 1) + " element");
			ia[i] = sc.nextInt();
		}

		return ia;
	}

	public static void display() {
		for (int i = 0; i < ia.length; i++) {
			System.out.println("Value " + (i + 1) + ": " + ia[i]);
		}
	}

	public static void usingStreams() {
		// using stream-api
		IntStream.range(0, ia.length).mapToObj(index -> String.format("value %d: %d", (index + 1), ia[index]))
				.forEach(System.out::println);
	}
}
