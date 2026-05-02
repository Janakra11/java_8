import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

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
         *  1. static method reference: ClassName:: staticMethod
         *  2. Instance method of a specific obj : instance::
         */

    }

}
