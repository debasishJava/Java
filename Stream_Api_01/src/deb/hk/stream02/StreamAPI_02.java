package deb.hk.stream02;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPI_02 {

	public static void main(String[] args) {
		//Count the empty string
		List<String> strList = Arrays.asList("abc", "", "bcd", "", "defg", "jk");
		long count = strList.stream()
				.filter(x -> x.isEmpty())
				.count();
		System.out.printf("List %s has %d empty strings %n", strList, count);
		
		//count string with length more than 3
		long num = strList.stream()
				.filter(x -> x.length() > 3)
				.count();
		System.out.printf("List %s has %d strings of length more than 3 %n", strList, num);
		
		//count number of string which startwith "a"
		count = strList.stream()
				.filter(x -> x.startsWith("a"))
				.count();
		System.out.printf("List %s has %d strings which startswith 'a' %n", strList, count);
		
		//remove all empty strings from list
		List<String> filtered = strList.stream()
				.filter(x -> !x.isEmpty())
				.collect(Collectors.toList());
		System.out.printf("Original List: %s, List without empty strings: %s %n", strList, filtered);
		
		//create a list with string more than 2 characters
		filtered = strList.stream()
				.filter(x -> x.length() > 2)
				.collect(Collectors.toList());
		System.out.printf("Orignal list: %s, filtered list: %s %n", strList, filtered);
		
		//convert string to uppercase and join them with coma
		List<String> g7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "UK", "Canada");
		String g7Countries = g7.stream()
				.map(x -> x.toUpperCase())
				.collect(Collectors.joining(", "));
		System.out.println(g7Countries);
		
		
		//create List of square of all distinct numbers
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		List<Integer> distinct = numbers.stream()
				.map(i ->  i*i)
				.distinct()
				.collect(Collectors.toList());
		System.out.println("Original List: "+numbers+ ", Square without duplicates: "+distinct);
		
		
		//Get count, min, max, sum, and average for numbers
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics stats = primes.stream()
				.mapToInt((x) -> x)
				.summaryStatistics();
		System.out.println("Highest prime number in List: "+ stats.getMax());
		System.out.println("Lowest prime number in List: "+ stats.getMin());
		System.out.println("Sum of all prime number in List: "+ stats.getSum());
		System.out.println("Average of  prime numbers in List: "+ stats.getAverage());
	}
}
