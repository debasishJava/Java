package deba.hk09;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 *  9. Develop a program to create an array of integers, REPLACE the element 2 with 9
     Support if an array has values 3, 4, 5, 2, 6 after replacing 2  array must be
 O/P: [3, 4, 5, 2, 6] 
 O/P: [3, 4, 5, 9, 6] 
 */
public class Assignment_09 {

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
		System.out.println("before replacing: " + Arrays.toString(ia));

		// creating another array for updating the value 2 -> 9
		for (int index = 0; index < ia.length; index++) {
			if (ia[index] == 2)
				ia[index] = 9;
		}

		System.out.println("after replacing: " + Arrays.toString(ia));
	}

	public static void executeUsingStreams() {
		// stream api
		List<Integer> list = Arrays.stream(ia).boxed().collect(Collectors.toList());
		Collections.replaceAll(list, 2, 9);
		list.forEach(x -> System.out.print(x + " "));
	}

	public static void resourceClose() {
		sc.close();
	}
}
