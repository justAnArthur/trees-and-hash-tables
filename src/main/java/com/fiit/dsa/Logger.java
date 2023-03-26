package com.fiit.dsa;

import com.fiit.dsa.hashTable.test.ClosedHashTableTest;
import com.fiit.dsa.hashTable.test.OpenHashTableTest;
import com.fiit.dsa.tree.test.AVLTreeTest;
import com.fiit.dsa.tree.test.SplayTreeTest;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class Logger {

    private static final String basePath = "src/main/resources/";

    private static String[] toTestSize(Integer max, Integer step) {
        String[] values = new String[max / step];

        for (int i = step; i <= max; i += step)
            values[i / step - 1] = String.valueOf(i);

        return values;
    }

    public static void main(String[] args) throws RunnerException {

        String[] stringedToTestClasses = {
                AVLTreeTest.class.getSimpleName(),
                SplayTreeTest.class.getSimpleName(),
                OpenHashTableTest.class.getSimpleName(),
                ClosedHashTableTest.class.getSimpleName(),
        };

        for (String stringedToTestClass : stringedToTestClasses) {
            Options options = new OptionsBuilder()
                    .include(stringedToTestClass)
                    .timeUnit(TimeUnit.MILLISECONDS)
                    .mode(Mode.SingleShotTime)
                    .warmupIterations(0)
                    .measurementIterations(1)
                    .forks(1)
                    .result(basePath + stringedToTestClass.substring(0, stringedToTestClass.length() - 4) + "/sample.csv")
                    .resultFormat(ResultFormatType.CSV)
                    .param("size", "1000")
                    .build();

            new Runner(options).run();
        }
    }
}