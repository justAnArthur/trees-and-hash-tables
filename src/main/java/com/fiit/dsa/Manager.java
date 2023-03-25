package com.fiit.dsa;

import com.fiit.dsa.hashTable.test.ClosedHashTableTest;
import com.fiit.dsa.hashTable.test.OpenHashTableTest;
import com.fiit.dsa.tree.test.AVLTreeTest;
import com.fiit.dsa.tree.test.SplayTreeTest;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Manager {

    private final Scanner scanner;

    public Manager() {
        scanner = new Scanner(System.in);
    }

    private String handleInput(String text, String[] acceptableStringedOptions) {
        System.out.println(text + " " + Arrays.toString(acceptableStringedOptions) + " :");

        String line = scanner.nextLine();

        if (line.isEmpty())
            handleInput(text, acceptableStringedOptions);

        return Arrays.stream(acceptableStringedOptions)
                .filter(string -> string.toLowerCase().contains(line.toLowerCase()))
                .findFirst()
                .orElseGet(() -> handleInput(text, acceptableStringedOptions));
    }

    private Integer handleNumber(String text) {
        System.out.println(text + " `number` :");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception ex) {
            return handleNumber(text);
        }
    }

    public ChainedOptionsBuilder setUpBenchmarkOptions() {
        ChainedOptionsBuilder optionsBuilder;

        ArrayList<Class<?>> classes = new ArrayList<>() {{
            add(AVLTreeTest.class);
            add(SplayTreeTest.class);
            add(OpenHashTableTest.class);
            add(ClosedHashTableTest.class);
        }};

        final String classToBeTested = handleInput("Class to be tested",
                classes.stream().map(Class::getSimpleName).toArray(String[]::new)
        );

        ArrayList<String> methodsOfClassToBeTested =
                Arrays.stream(
                                classes
                                        .stream()
                                        .filter((c) -> c.getSimpleName().equals(classToBeTested))
                                        .findFirst().get()
                                        .getMethods()

                        )
                        .filter(method -> method.isAnnotationPresent(Benchmark.class))
                        .map(Method::getName)
                        .collect(Collectors.toCollection(ArrayList::new));

        methodsOfClassToBeTested.add("*");

        String methodToBeTested = handleInput("Method to be tested",
                methodsOfClassToBeTested.toArray(String[]::new)
        );

        String classToBeTestedWithSelectedMethod = classToBeTested;

        if (!methodToBeTested.equals("*"))
            classToBeTestedWithSelectedMethod = classToBeTestedWithSelectedMethod.concat("." + methodToBeTested);

        optionsBuilder = new OptionsBuilder()
                .include(classToBeTestedWithSelectedMethod)
                .param("size", String.valueOf(handleNumber("Size")));

        return optionsBuilder;
    }

    public static void main(String[] args) throws RunnerException {

        Options options = new Manager()
                .setUpBenchmarkOptions()
                .timeUnit(TimeUnit.MILLISECONDS)
                .mode(Mode.SingleShotTime)
                .forks(1)
                .build();

        new Runner(options).run();
    }
}
