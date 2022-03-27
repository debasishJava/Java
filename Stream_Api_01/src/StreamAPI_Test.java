import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.Vector;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI_Test {

private static String fileName = "test.txt";
    
    private static Employee[] arrayOfEmps = {
        new Employee(1, "Jeff Bezos", 100000.0), 
        new Employee(2, "Bill Gates", 200000.0), 
        new Employee(3, "Mark Zuckerberg", 300000.0)
    };

    private static List<Employee> empList = Arrays.asList(arrayOfEmps);
    private static EmployeeRepository employeeRepository = new EmployeeRepository(empList);

//create a stream from individual objects
    public void whenBuildStreamFromElements_ObtainStream() {
        Stream.Builder<Employee> empStreamBuilder = Stream.builder();
        
        empStreamBuilder.accept(arrayOfEmps[0]);
        empStreamBuilder.accept(arrayOfEmps[1]);
        empStreamBuilder.accept(arrayOfEmps[2]);

        Stream<Employee> empStream = empStreamBuilder.build();
        
    }
	/*
	 * forEach
			forEach() is simplest and most common operation; it loops over the stream elements, 
			calling the supplied function on each element.
	
			forEach() is a terminal operation, which means that, after the operation is performed, 
			the stream pipeline is considered consumed, and can no longer be used.
	 */
    public void whenIncrementSalaryForEachEmployee_thenApplyNewSalary() {
        Employee[] arrayOfEmps = {
            new Employee(1, "Jeff Bezos", 100000.0), 
            new Employee(2, "Bill Gates", 200000.0), 
            new Employee(3, "Mark Zuckerberg", 300000.0)
        };

        List<Employee> empList = Arrays.asList(arrayOfEmps);
        
        empList.stream().forEach(e -> e.salaryIncrement(10.0));
        
    }

    /*
     * peek()
     * 
     * sometimes we need to perform multiple operations on each element of the stream before 
     * any terminal operation is applied.

       peek() can be useful in situations like this. Simply put, 
       it performs the specified operation on each element of the stream and returns 
       a new stream which can be used further. peek() is an intermediate operation:
     */
    
    /*
     * 
     * Here, the first peek() is used to increment the salary of each employee. 
     * The second peek() is used to print the employees. Finally, collect() is used as the terminal operation.
     */
    public void whenIncrementSalaryUsingPeek_thenApplyNewSalary() {
        Employee[] arrayOfEmps = {
            new Employee(1, "Jeff Bezos", 100000.0), 
            new Employee(2, "Bill Gates", 200000.0), 
            new Employee(3, "Mark Zuckerberg", 300000.0)
        };

        List<Employee> empList = Arrays.asList(arrayOfEmps);
        
        empList.stream()
          .peek(e -> e.salaryIncrement(10.0))
          .peek(System.out::println)
          .collect(Collectors.toList());

    }

    /*
     *map
		map() produces a new stream after applying a function to each element of the original stream. 
		The new stream could be of different type. 
		
		Here, we obtain an Integer stream of employee ids from an array. 
		Each Integer is passed to the function employeeRepository::findById() – which returns 
		the corresponding Employee object; this effectively forms an Employee stream.
     */
    
    /*
     * collect() performs mutable fold operations (repackaging elements to some data structures and applying some 
     * additional logic, concatenating them, etc.) on data elements held in the Stream instance.

		The strategy for this operation is provided via the Collector interface implementation. 
		In the example above, we used the toList collector to collect all Stream elements into a List instance.
     */
    public void whenMapIdToEmployees_thenGetEmployeeStream() {
        Integer[] empIds = { 1, 2, 3 };
        
        List<Employee> employees = Stream.of(empIds)
          .map(employeeRepository::findById)
          .collect(Collectors.toList());
        
    }

    /*
     * flatMap
         A stream can hold complex data structures like Stream<List<String>>. 
         In cases like this, flatMap() helps us to flatten the data structure to simplify further operations:
     */
    public void whenFlatMapEmployeeNames_thenGetNameStream() {
        List<List<String>> namesNested = Arrays.asList( 
          Arrays.asList("Jeff", "Bezos"), 
          Arrays.asList("Bill", "Gates"), 
          Arrays.asList("Mark", "Zuckerberg"));

        List<String> namesFlatStream = namesNested.stream()
          .flatMap(Collection::stream)
          .collect(Collectors.toList());

    }

    /*
     * filter()
     * this produces a new stream that contains elements of the original stream that pass a given test (specified by a Predicate)
     * 
     * we first filter out null references for invalid employee ids and then again apply a 
     * filter to only keep employees with salaries over a certain threshold.
     */
    public void whenFilterEmployees_thenGetFilteredStream() {
        Integer[] empIds = { 1, 2, 3, 4 };
        
        List<Employee> employees = Stream.of(empIds)
          .map(employeeRepository::findById)
          .filter(e -> e != null)
          .filter(e -> e.getSalary() > 200000)
          .collect(Collectors.toList());
        
    }

    /*
     * findFirst
		findFirst() returns an Optional for the first entry in the stream; the Optional can, of course, be empty
		
		Here, the first employee with the salary greater than 100000 is returned. 
		If no such employee exists, then null is returned.
     */
    
    /*
     * Lazy Evaluation
       One of the most important characteristics of Java streams is that they allow for 
       significant optimizations through lazy evaluations.

       Computation on the source data is only performed when the terminal operation is initiated, 
       and source elements are consumed only as needed.

       All intermediate operations are lazy, so they’re not executed until a result of a processing is actually needed.
     */
    public void whenFindFirst_thenGetFirstEmployeeInStream() {
        Integer[] empIds = { 1, 2, 3, 4 };
        
        Employee employee = Stream.of(empIds)
          .map(employeeRepository::findById)
          .filter(e -> e != null)
          .filter(e -> e.getSalary() > 100000)
          .findFirst()
          .orElse(null);
        
    }

    public void whenCollectStreamToList_thenGetList() {
        List<Employee> employees = empList.stream().collect(Collectors.toList());
        
    }

    /*
     * toArray()
     * If we need to get an array out of the stream, we can simply use toArray():
     * 
     * The syntax Employee[]::new creates an empty array of Employee – which is then filled with elements from the stream.
     */
    public void whenStreamToArray_thenGetArray() {
        Employee[] employees = empList.stream().toArray(Employee[]::new);

    }
    
    /*
     * Java stream operations are divided into intermediate and terminal operations.

		Intermediate operations such as filter() return a new stream on which 
		further processing can be done. Terminal operations, such as forEach(), 
		mark the stream as consumed, after which point it can no longer be used further.

		A stream pipeline consists of a stream source, followed by zero or 
		more intermediate operations, and a terminal operation.

		Here’s a sample stream pipeline, where empList is the source, filter() 
		is the intermediate operation and count is the terminal operation:
     */
    public void whenStreamCount_thenGetElementCount() {
        Long empCount = empList.stream()
          .filter(e -> e.getSalary() > 200000)
          .count();

    }

    /*
     * Short-circuiting operations allow computations on infinite streams to complete in finite time:
     * 
     * Here, we use short-circuiting operations skip() to skip first 3 elements, and limit() 
     * to limit to 5 elements from the infinite stream generated using iterate().
     */
    public void whenLimitInfiniteStream_thenGetFiniteElements() {
        Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);
    
        List<Integer> collect = infiniteStream
          .skip(3)
          .limit(5)
          .collect(Collectors.toList());
  
    }
 
    /*
     * sorted() operation – this sorts the stream elements based on the comparator passed we pass into it.
     * 
     * short-circuiting will not be applied for sorted().

		This means, in the example above, even if we had used findFirst() after the sorted(), 
		the sorting of all the elements is done before applying the findFirst(). 
		This happens because the operation cannot know what the first element is until the entire stream is sorted.
     */
    public void whenSortStream_thenGetSortedStream() {
        List<Employee> employees = empList.stream()
          .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
          .collect(Collectors.toList());

    }


    /*
     * min() and max() return the minimum and maximum element in the stream respectively, 
     * based on a comparator. They return an Optional since a result may or may not exist (due to, say, filtering):
     */
    public void whenFindMin_thenGetMinElementFromStream() {
        Employee firstEmp = empList.stream()
          .min((e1, e2) -> e1.getId() - e2.getId())
          .orElseThrow(NoSuchElementException::new);

    }
    
    /*
     * We can also avoid defining the comparison logic by using Comparator.comparing():
     */
    public void whenFindMax_thenGetMaxElementFromStream() {
        Employee maxSalEmp = empList.stream()
          .max(Comparator.comparing(Employee::getSalary))
          .orElseThrow(NoSuchElementException::new);

    }
    
   /*
    * distinct() does not take any argument and returns the distinct elements in the stream,
    *  eliminating duplicates. It uses the equals() method of the elements to decide whether two elements are equal or not: 
    */
    public void whenApplyDistinct_thenRemoveDuplicatesFromStream() {
        List<Integer> intList = Arrays.asList(2, 5, 3, 2, 4, 3);
        List<Integer> distinctIntList = intList.stream().distinct().collect(Collectors.toList());
        
    }

    /*
     * allMatch, anyMatch, and noneMatch
      These operations all take a predicate and return a boolean. Short-circuiting is applied and 
      processing is stopped as soon as the answer is determined:
      
      
      allMatch() checks if the predicate is true for all the elements in the stream. Here, 
      it returns false as soon as it encounters 5, which is not divisible by 2.

      anyMatch() checks if the predicate is true for any one element in the stream. 
      Here, again short-circuiting is applied and true is returned immediately after the first element.

      noneMatch() checks if there are no elements matching the predicate. 
      Here, it simply returns false as soon as it encounters 6, which is divisible by 3.
     */
    public void whenApplyMatch_thenReturnBoolean() {
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
        
        boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
        boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
        boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);
        
    }
   
    /*
     * Stream is a stream of object references. However, there are also the IntStream, 
     * LongStream, and DoubleStream – which are primitive specializations for int, long and double respectively. 
     * These are quite convenient when dealing with a lot of numerical primitives.

       These specialized streams do not extend Stream but extend BaseStream on top of which Stream is also built.

       As a consequence, not all operations supported by Stream are present in these stream 
       implementations. For example, the standard min() and max() take a comparator, 
       whereas the specialized streams do not.

      Creation
      The most common way of creating an IntStream is to call mapToInt() on an existing stream:
     */
    public void whenFindMaxOnIntStream_thenGetMaxInteger() {
        Integer latestEmpId = empList.stream()
          .mapToInt(Employee::getId)
          .max()
          .orElseThrow(NoSuchElementException::new);

    }

    /*
     * Specialized streams provide additional operations as compared to the standard Stream – 
     * which are quite convenient when dealing with numbers.

      For example sum(), average(), range() etc:
     */
    public void whenApplySumOnIntStream_thenGetSum() {
        Double avgSal = empList.stream()
          .mapToDouble(Employee::getSalary)
          .average()
          .orElseThrow(NoSuchElementException::new);
        
    }
    
    /*
     * Reduction Operations
        A reduction operation (also called as fold) takes a sequence of input elements and 
        combines them into a single summary result by repeated application of a combining operation. 
        
        
        reduce
       The most common form of reduce() is:

       T reduce(T identity, BinaryOperator<T> accumulator)
        where identity is the starting value and accumulator is the binary operation we repeated apply.
     */
    public void whenApplyReduceOnStream_thenGetValue() {
        Double sumSal = empList.stream()
          .map(Employee::getSalary)
          .reduce(0.0, Double::sum);

    }
    
    /*
     * Collectors.joining() will insert the delimiter between the two String elements of the stream.
     *  It internally uses a java.util.StringJoiner to perform the joining operation.
     */
    public void whenCollectByJoining_thenGetJoinedString() {
        String empNames = empList.stream()
          .map(Employee::getName)
          .collect(Collectors.joining(", "))
          .toString();
        
    }
    
    /*
     * toSet
      We can also use toSet() to get a set out of stream elements:
     */
    public void whenCollectBySet_thenGetSet() {
        Set<String> empNames = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        
    }
  
    /*
     * toCollection
       We can use Collectors.toCollection() to extract the elements into any other collection
        by passing in a Supplier<Collection>. We can also use a constructor reference for the Supplier:
     */
    public void whenToVectorCollection_thenGetVector() {
        Vector<String> empNames = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(Vector::new));
        
    }

    /*
     * summarizingDouble() is a collector – which applies a double-producing mapping 
     * function to each input element and returns a special class containing statistical 
     * information for the resulting values:
     */
    public void whenApplySummarizing_thenGetBasicStats() {
        DoubleSummaryStatistics stats = empList.stream()
          .collect(Collectors.summarizingDouble(Employee::getSalary));

    }

    /*
     * summaryStatistics() can be used to generate similar result when we’re using one of the specialized streams:
     */
    public void whenApplySummaryStatistics_thenGetBasicStats() {
        DoubleSummaryStatistics stats = empList.stream()
          .mapToDouble(Employee::getSalary)
          .summaryStatistics();

    }

    /*
     * partitioningBy
         We can partition a stream into two – based on whether the elements satisfy certain criteria or not.

       split our List of numerical data, into even and odds:
     */
    public void whenStreamPartition_thenGetMap() {
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
        Map<Boolean, List<Integer>> isEven = intList.stream().collect(
          Collectors.partitioningBy(i -> i % 2 == 0));
        
    }
    
    /*
     * groupingBy
        groupingBy() offers advanced partitioning – where we can partition the stream into more than just two groups.

         It takes a classification function as its parameter. This classification function is applied to each element of the stream.

        The value returned by the function is used as a key to the map that we get from the groupingBy collector:
     */
    public void whenStreamGroupingBy_thenGetMap() {
        Map<Character, List<Employee>> groupByAlphabet = empList.stream().collect(
          Collectors.groupingBy(e -> new Character(e.getName().charAt(0))));

    }
    
    /*
     * mapping() maps the stream element Employee into just the employee id – 
     * which is an Integer – using the getId() mapping function. 
     * These ids are still grouped based on the initial character of employee first name.
     */
    public void whenStreamMapping_thenGetMap() {
        Map<Character, List<Integer>> idGroupedByAlphabet = empList.stream().collect(
          Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
            Collectors.mapping(Employee::getId, Collectors.toList())));

    }

    /*
     * reducing() is similar to reduce() 
     * It simply returns a collector which performs a reduction of its input elements:
     * 
     * 
     * Here reducing() gets the salary increment of each employee and returns the sum.

        reducing() is most useful when used in a multi-level reduction, 
        downstream of groupingBy() or partitioningBy(). To perform a simple 
        reduction on a stream, use reduce() instead.
     */
    public void whenStreamReducing_thenGetValue() {
        Double percentage = 10.0;
        Double salIncrOverhead = empList.stream().collect(Collectors.reducing(
            0.0, e -> e.getSalary() * percentage / 100, (s1, s2) -> s1 + s2));

    }
    
    /*
     * Here we group the employees based on the initial character of their first name. 
     * Within each group, we find the employee with the longest name.
     */
    public void whenStreamGroupingAndReducing_thenGetMap() {
        Comparator<Employee> byNameLength = Comparator.comparing(Employee::getName);
        
        Map<Character, Optional<Employee>> longestNameByAlphabet = empList.stream().collect(
          Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
            Collectors.reducing(BinaryOperator.maxBy(byNameLength))));

    }

    /*
     * Using the support for parallel streams, we can perform stream operations 
     * in parallel without having to write any boilerplate code; we just have to designate the stream as parallel:
     * 
     * 
     * Here salaryIncrement() would get executed in parallel on multiple elements of the stream, 
     * by simply adding the parallel() syntax.

        This functionality can, of course, be tuned and configured further, 
        if you need more control over the performance characteristics of the operation.

        As is the case with writing multi-threaded code, we need to be aware of few things while using parallel streams:

		We need to ensure that the code is thread-safe. Special care needs to be taken 
		if the operations performed in parallel modifies shared data.
		We should not use parallel streams if the order in which operations
		 are performed or the order returned in the output stream matters.
		  For example operations like findFirst() may generate the different result in case of parallel streams.
		Also, we should ensure that it is worth making the code execute in parallel.
		 Understanding the performance characteristics of the operation in particular,
		  but also of the system as a whole – is naturally very important here.
     */
    public void whenParallelStream_thenPerformOperationsInParallel() {
        Employee[] arrayOfEmps = {
          new Employee(1, "Jeff Bezos", 100000.0), 
          new Employee(2, "Bill Gates", 200000.0), 
          new Employee(3, "Mark Zuckerberg", 300000.0)
        };

        List<Employee> empList = Arrays.asList(arrayOfEmps);
        
        empList.stream().parallel().forEach(e -> e.salaryIncrement(10.0));
        
    }

    /*
     * Infinite Streams
       Sometimes, we might want to perform operations while the elements are still 
       getting generated. We might not know beforehand how many elements we’ll need.
        Unlike using list or map, where all the elements are already populated, 
        we can use infinite streams, also called as unbounded streams.

       There are two ways to generate infinite streams: generate & iterate

       generate
       We provide a Supplier to generate() which gets called whenever new stream elements need to be generated:
     */
    public void whenGenerateStream_thenGetInfiniteStream() {
        Stream.generate(Math::random)
          .limit(5)
          .forEach(System.out::println);
    }

    /*
     * iterate
       iterate() takes two parameters: an initial value, 
       called seed element and a function which generates next 
       element using the previous value. iterate(), by design, is stateful and 
       hence may not be useful in parallel streams:
       
       
       Here, we pass 2 as the seed value, which becomes the first element of our stream. 
       This value is passed as input to the lambda, which returns 4. This value, 
       in turn, is passed as input in the next iteration.

        This continues until we generate the number of elements specified by limit() 
        which acts as the terminating condition.
     */
    public void whenIterateStream_thenGetInfiniteStream() {
        Stream<Integer> evenNumStream = Stream.iterate(2, i -> i * 2);
    
        List<Integer> collect = evenNumStream
          .limit(5)
          .collect(Collectors.toList());
  
    }

    /*
     * forEach() to write each element of the stream into the file by calling PrintWriter.println().
     */
    public void whenStreamToFile_thenGetFile() throws IOException {
        String[] words = {
          "hello", 
          "refer",
          "world",
          "level"
        };
        
        try (PrintWriter pw = new PrintWriter(
          Files.newBufferedWriter(Paths.get(fileName)))) {
            Stream.of(words).forEach(pw::println);
        }
    }
   
    /*
     * Files.lines() returns the lines from the file as a Stream which is consumed by the 
     * getPalindrome() for further processing.

       getPalindrome() works on the stream, completely unaware of how the stream was generated. 
       This also increases code reusability and simplifies unit testing.
     */
    private List<String> getPalindrome(Stream<String> stream, int length) {
        return stream.filter(s -> s.length() == length)
          .filter(s -> s.compareToIgnoreCase(
            new StringBuilder(s).reverse().toString()) == 0)
          .collect(Collectors.toList());
    }
    
    public void whenFileToStream_thenGetStream() throws IOException {
        whenStreamToFile_thenGetFile();
        
        List<String> str = getPalindrome(Files.lines(Paths.get(fileName)), 5);        
    }
}
