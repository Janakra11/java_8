import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**********
 *
 *
 * ################################################
 *    JAVA 8 - COMPREHENSIVE GUIDE WITH EXAMPLES
 * ################################################
 *
 * Java 8 (released March 2014) introduced major features:
 * 1. Lambda Expressions
 * 2. Functional Interfaces
 * 3. Method References
 * 4. Default & static Methods in Interfaces
 * 5. Stream API
 * 6. Optional Class
 * 7. New Date and Time API (java.util)
 * 8. Collectors
 * 9. forEach() on Iterable
 * 10.Nasborn JavaScripts Engine (deprecated later)
 * 11.Base64 Encoding/Decoding
 * 12.Parallel Arrays
 */

public class Java8Features {


    public static void main(String args[]) {

        System.out.println("=".repeat(60));
        System.out.println("              JAVA 8 FEATURES - EXAMPLES");
        System.out.println("=".repeat(60));


        //--------------------------------------------------------
        // LAMBDA EXPRESSIONS
        //---------------------------------------------------------

        System.out.println("\n 1.  LAMBDA EXPRESSIONS-----");

        /*
         * Syntax  : (Parameters) -> expression
         *           (Parameters) -> { statements;}
         *
         * A lambda expression is a short block of code which takes
         * in parameters and returns a value. It is similar to a method,
         *  but it does not need a name and can implemented right in the
         * body of a method.
         *
         */

        //Before Java 8 (Anonymous class)
        Runnable oldWay = new Runnable() {
            @Override
            public void run() {
                System.out.println(" [oldWay] Running with anonymous class");
            }
        };

        oldWay.run();


        // Java 8 Lambda
        Runnable newWay = () -> System.out.println(" [Lambda] Running with lambda expression");
        newWay.run();

        //Lambda with parameters
        Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
        names.sort(comparator);
        System.out.println("Sorted names: "+names);

        //Lambda with block body
        Comparator<Integer> intComparater = (a, b) -> {
            System.out.println(" Compairing " + a + " and " + b);
            return Integer.compare(a,b);
        };
        List<Integer> numbers = Arrays.asList(5,3,8,1,9,2);
        numbers.sort(intComparater);
        System.out.println( " Sorted numbers:"  + numbers);


        //-----------------------------------------------------------
        // 2.FUNCTIONAL INTERFACES
        //-----------------------------------------------------------
        System.out.println("\n--- 2. Functional Interface");

        /**
         *
         * A functional Interface has ONE abstract method.
         * Java 8 provides build-in functional interfaces in java.util.function:
         *
         * Interface as follows:
         *
         * 1. Predicate<T>   : boolean test(T t) = Takes T return boolean value
         * 2. Functional<T>  : R apply(T t) = Takes T, Return R
         * 3. Consumer<T>    : void accept(T t) = Takes T , return void
         * 4. Supplier<T>    : R get()   = Takes nothing, return R
         * 5. BiPredicate<T,U,R> : boolean test(T t, U u) = Takes T and U, return boolean value
         * 6. BiFunction<T,U,R>  : R apply(T t, U u) = Takes T and U , return R
         * 7. BiConsumer<T U>    : void accept(T t, U u) = Takes T and U, return void
         * 8. UnaryOperator<T>   : T apply(T t) = takes T and return T use this return type is same
         * 9. BinaryOperator<T>  : T apply(T t, T t) = takes two T and return t use this one when
         *                         inputs parameters and return values same datatype
         */

        // Predicate<T> - boolean-valued function
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive  = n -> n > 0;
        System.out.println( " isEven(4):"+ isEven.test(4));
        System.out.println(" isPositive(7):"+ isPositive.test(7));

        //Predicate composition: add(), or(), negate()
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        System.out.println(" isEven and isPositive for 4:" + isEvenAndPositive.test(4));
        System.out.println("  isEven and is Positive for -4: " + isEvenAndPositive.test(-4));

        //Function<T, R> -  transforms input to output
        Function<String, Integer> strLength = String::length;
        Function<Integer, String> intToStr = i -> "Number:"+i;
        //Function composition: andThen(), compose()
        Function<String, String> lengthToStr = strLength.andThen(intToStr);
        System.out.println(" Function compose : " + lengthToStr.apply("hello"));

        //Consumer<T> - consumes input, void return
        Consumer<String> printer = s -> System.out.println(" Consumer prints: "+s);
        printer.accept("Hello Jav 8!");

        //BiConsumer<T, U>
        BiConsumer<String, Integer> biPrinter = (s,i) -> System.out.println(" BiConsumer: "+ s + "=" + i );
        biPrinter.accept("Age", 25);

        //Supplier<T> -supplies a value, no input
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> newList = listSupplier.get();
        newList.add("supplier");
        System.out.println(" Supplier created list:" + newList);

        //UnaryOperator<T>
        UnaryOperator<String> toUpperCase = String::toUpperCase;
        System.out.println( " UnaryOperator: " + toUpperCase.apply("hello"));

        //BinaryOperator<T>
        BinaryOperator<Integer> add = (a,b) -> a+b;
        System.out.println( " BinaryOperator add: " + add.apply(10,20));

        //------------------------------------------------------
        //  3. METHOD REFERENCE
        //------------------------------------------------------
        System.out.println("\n--- 3. METHOD REFERENCE");

        /***
         *  Method reference are a shorthand for lambda calling a specific method
         *  Syntax : ClassName::methodName
         *
         *  Types:
         *  1. static method reference:            ClassName:: staticMethod
         *  2. Instance method of a specific obj   instance::instanceMethod
         *  3. Instance method of a arbitrary obj  className::instanceMethod
         *  4. Constructor reference:              className::new
         */

        List<String> words = Arrays.asList("hello", "world", "java", "eight");

        //1. static method reference
        words.stream()
                .map(String::toUpperCase)      //instance method of arbitrary obj
                .forEach(System.out::println); // static-like method reference

        //2. Instance method of a specific object
        String prefix = "JAVA8 ";
        Function<String, String> addPrefix = prefix::concat;
        System.out.println(" "+ addPrefix.apply("Method Reference"));

        //3. Instance method of arbitrary object
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(" Is String empty? "+ isEmpty.test(""));
        System.out.println(" Is 'hello' empy? " + isEmpty.test("hello"));

        //--------------------------------------------------------
        // 4. DEFAULT AND STATIC METHODS IN INTERFACES
        //---------------------------------------------------------

        /***
         *  Java8 allows interfaces to have :
         *  - default methods : concrete methods with 'default' keyword
         *  - static methods  : utility methods in the interface itself
         *
         *  This enables backward compatibility when adding new methods to interfaces.
         */

        Vehicle car = Car("Mahindra XUV700");
        Vehicle bike = Car("Hero xtereme 125cc");

        car.start();
        car.describe();//default method
        car.stop();//default method

        bike.start();
        bike.describe();//default method
        bike.stop();//default method

        //Static method on interface
        Vehicle.printInfo();

        //------------------------------------------------------------------
        // 5. STREAM API
        //------------------------------------------------------------------
        System.out.println("\n---  5.Stream API------------");

        /**
         * Stream provide a functional approach to processing collections.
         * A Stream is a sequence of elements supporting sequential and parallel
         * aggregation operations.
         *
         * Stream pipeline:
         *  Source -> Intermediate Operation -> Terminal Operation
         *
         *  Intermediate (lazy): filter, map, flatmap, distinct, sorted, peak, limit, skip
         *  Terminal (eager): forEach, collect, reduce, count, min max, anyMatch, allMatch,
         *                    findFirst
         */

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //filter + map + collect
        List<Integer> evenSquares = nums.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n*n)
                .collect(Collectors.toList());
        System.out.println(" Even squares : " + evenSquares);

        // reduce
        int sum = nums.stream()
                .reduce(0, Integer::sum);
        System.out.println(" sum of 1-10:" + sum);

        //count
        long evenCount = nums.stream()
                .filter(n -> n%2 ==0)
                .count();
        System.out.println(" Count of even nums : "+ evenCount);

        //min and max
        Optional<Integer> max = nums.stream().max(Integer::compareTo);
        Optional<Integer> min = nums.stream().min(Integer::compareTo);
        System.out.println(" Max: "+max.orElse(-1)+",  Min:"+min.orElse(-1));

        // sorted + distinct
        List<Integer> dupes = Arrays.asList(3,1,4,1,5,9,2,6,5,3);
        List<Integer> sortedDistinct = dupes.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(" sorted distinct: " + sortedDistinct);

        //faltMap - flatten nested list
        List<List<Integer>> nested = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,5,6),
                Arrays.asList(7,8,9)
        );

        List<Integer> flat =  nested.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(" flatMap result : " + flat);

        //anyMatch, allMatch, noneMatch
        boolean anyEven = nums.stream().anyMatch(n -> n%2 == 0);
        boolean allPositive = nums.stream().allMatch(n -> n > 0);
        boolean noneNegative = nums.stream().noneMatch(n -> n < 0);
        System.out.println("  anyEven: "+ anyEven+ ", allPositive: "+ allPositive+", nonNegative:"+ noneNegative);


        //findFirst
        Optional<Integer> firstEven = nums.stream()
                .filter(n -> n%2 == 0)
                .findFirst();
        System.out.println(" First even: "+ firstEven.orElse(-1));

        //limli andskip
        List<Integer> limited = nums.stream()
                .skip(2).limit(5)
                .collect(Collectors.toList());
        System.out.println(" skip 2, limit 5: "+limited);

        //Parallel stream
        long parallelSum = nums.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        System.out.println(" Parallel sum: "+ parallelSum );

        //stream of strings
        List<String> employees = Arrays.asList("devansh", "janakraj", "akshay", "vedika", "balkrishna");
        String result = employees.stream()
                .filter(e -> e.length() > 3)
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(" Employee (len>3, sorted): "+result);

        //-----------------------------------------------------------------------
        // 6. OPTIONAL CLASS
        //-------------------------------------------------------------------------
        System.out.println("\n--- 6. OPTIONAL CLASS ----");

        /***
         * Optional<T> is container that may or may not contain a non-null value.
         * It helps avoid NullPointerException and makes null handling explicit.
         *
         * Key Methods:
         *  Optional.of(value)    -create Optional with non-null value
         *  Optional .ofNullable(value) -creates Optional, allows null
         *  Optional.empty()       -creates empty Optional
         *  isPresent()            -returns true if value present
         *  get()                  -returns value or default
         *  orElse(default)        -returns value or supplier result
         *  orElseThrow(supplier)  -returns value or throws exception
         *  ifPresent(consumer)    -execute consumer if value present
         *  map(function)          -transform value if present
         *  filter(predicate)      - filters value if present
         */

        // Creating Optionals
        Optional<String> present = Optional.of("hello");
        Optional<String> empty = Optional.empty();
        Optional<String> nullable = Optional.ofNullable(null);

        System.out.println(" present.isPresent():" + present.isPresent());
        System.out.println(" empty.isPresent():" + empty.isPresent());
        System.out.println(" nullable.isPresent():" + nullable.isPresent());

        //orElse
        System.out.println(" present.orElse('default'):" + present.orElse("default"));
        System.out.println(" empty.orElse('default'):" + empty.orElse("default"));

        //orElseGet
        System.out.println(" empty.orElseGet:" + empty.orElseGet(() -> "generated default"));

        //ifPresent
        present.ifPresent(v -> System.out.println(" ifPresent value: "+v));

        //map
        Optional<Integer> length = present.map(String::length);
        System.out.println(" map to length:"+ length.orElse(0));

        //filter
        Optional<String> filtered = present.filter(s->s.startsWith("h"));
        System.out.println(" filter (start with h): "+ filtered.orElse("not Found"));

        //Chaining with optional (avoiding NPE)
        String city = Optional.ofNullable(getUser())
                .map(User::getAddress)
                .map(Address::getCity)
                .orElse("Unknown City");
        System.out.println(" chained Optional City: "+ city);

        //--------------------------------------------------------------------
        // 7. NEW DATE & TIME API(java.time)
        //---------------------------------------------------------------------
        System.out.println("\n--- 7. NEW DATE & TIME API ----");

        /***
         *
         * Java 8 introduced java.time package (inspired by Joda-time
         *
         * LocalDate - date without time (yyyy-MM-dd)
         * LocalTime - time without date (HH:mm:ss)
         * LocalDateTime - date and time without timezone
         * ZoneDateTime - date and time with timezone
         * Instant - machine-readable timestamp
         * Duration - time-based amount  ( hours, minutes, seconds)
         * Period - date-based amount (years, months, days)
         * DateTimeFormatter - for formatting/parsing
         */

        //LocalDate
        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(1989, Month.DECEMBER, 12);
        System.out.println(" Today: "+ today);
        System.out.println(" Birthday: "+ birthDate);
        System.out.println(" day of week:" + today.getDayOfWeek());
        System.out.println(" Is leap year: "+ today.isLeapYear());

        //Date arithmetic
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate lastMonth = today.minusMonths(1);
        System.out.println(" Next Week: "+nextWeek);
        System.out.println(" Last Month: "+lastMonth);

        //LocalTime
        LocalTime now = LocalTime.now();















    }

    static class Car implements Vehicle{

        private final String model;

        Car(String name){ this.model = name; }

        @Override
        public void start() {
            System.out.println(" [Car]  "+ model+ " started (electric motor).");
        }

        //Uses default stop() from interface
        //override describe()
        @Override
        public void describe() {
            System.out.println(" [Car] I'm a Car:"+ model );
        }
    }


    static class Bike implements Vehicle{

        private final String model;

        Bike(String name){ this.model = name; }

        @Override
        public void start() {
            System.out.println(" [Bike]  "+ model+ " started (electric motor).");
        }

        //Uses default stop() from interface
        //override describe()
        @Override
        public void describe() {
            System.out.println(" [Bike] I'm a Car:"+ model );
        }
    }

    //Helper classes for Optional chaining example
    static class User {
        private final Address address;
        User(Address address){ this.address = address;}
        Address getAddress(){ return address; }
    }

    static class Address{
        private final String city;
        Address(String city) { this.city = city; }
        String getCity(){ return city; }
    }

    static User getUser(){
        return new User(new Address("Lohegoa,Pune-411047"));
    }

}
