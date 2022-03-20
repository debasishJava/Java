package deba.hk08;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 *  8. Develop a program to create an array of integers, INSERT the element 2 in this ARRAY at 3rd index 
     Support if an array has values 3, 4, 5, 6 after inserting 2  array must be
 O/P: [3, 4, 5, 6] 
 O/P: [3, 4, 5, 2, 6] 
 */
public class Assignment_08 {

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
		// a array to copy the elements(updated)
		int[] updated = null;
		// insert 2 if there is 3rd index
		if (ia.length > 3) {
			updated = new int[ia.length + 1];
			for (int index = 0, j = 0; index < ia.length; index++) {
				if (j == 3) {
					updated[index] = 2;
					j++;
					updated[j] = ia[index - 1];
					index--;
				} else {
					updated[j] = ia[index];
					j++;
				}
			} // for
		} // if

		System.out.println("without adding 2 : " + Arrays.toString(ia));
		System.out.println("after adding 2 : " + Arrays.toString(updated));
	}

	public static void executeUsingStreams() {
		List<Integer> list = Arrays.stream(ia).boxed().collect(Collectors.toList());
		list.add(3, 2);
		list.stream().forEach(System.out::println);
	}

	public static void resourceClose() {
		sc.close();
	}
}
