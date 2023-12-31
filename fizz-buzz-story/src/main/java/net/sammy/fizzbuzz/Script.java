package net.sammy.fizzbuzz;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

public class Script {

    private static final String EMPTY = "";
    public static final String FIZZ = "fizz";
    public static final String BUZZ = "buzz";

    public void run() {
        System.out.println(
                String.join(" ", mapFizzBuzz())
        );
    }

    private List<String> mapFizzBuzz() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Map.entry(i, EMPTY))
                .map(this::fizz)
                .map(this::buzz)
                .map(this::format)
                .toList();
    }

    private Entry<Integer, String> fizz(Entry<Integer, String> pair) {
        if (pair.getKey() % 3 == 0)  {
            return Map.entry(pair.getKey(), pair.getValue() + FIZZ);
        } 

        return pair;
    }

    private Entry<Integer, String> buzz(Entry<Integer, String> pair) {
        if (pair.getKey() % 5 == 0)  {
            return Map.entry(pair.getKey(), pair.getValue() + BUZZ);
        } 

        return pair;
    }

    private String format(Entry<Integer, String> pair) {
        if (EMPTY.equals(pair.getValue())) {
            return pair.getKey().toString();
        } 

        return pair.getValue();
    }
}
