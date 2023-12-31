package net.sammy.fizzbuzz;

import java.util.List;
import java.util.stream.IntStream;

public class ScriptString {

    private static final String EMPTY = "";
    public static final String FIZZ = "fizz";
    public static final String BUZZ = "buzz";

    public void run() {
        System.out.println(
                String.join(" ", stringFizzBuzz())
        );
    }

    private List<String> stringFizzBuzz() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(i -> i + EMPTY)
                .map(this::stringFizz)
                .map(this::stringBuzz)
                .map(this::stringFormat)
                .toList();
    }

    private String stringFizz(String string) {
        try {
            int i = Integer.parseInt(removeFizzBuzzText(string));
            if (i % 3 == 0) {
                return (string + FIZZ);
            }
        } catch (NumberFormatException e) {}

        return string;
    }

    private String stringBuzz(String string) {
        try {
            int i = Integer.parseInt(removeFizzBuzzText(string));
            if (i % 5 == 0) {
                return (string + BUZZ);
            }
        } catch (NumberFormatException e) {}

        return string;
    }

    private String stringFormat(String string) {
        try {
            Integer.parseInt(string);
            return string;
        }
        catch (NumberFormatException e) {
            if (string.contains(FIZZ)) {
                return string.substring(string.indexOf(FIZZ));
            }
            return string.substring(string.indexOf(BUZZ));
        }
    }

    private String removeFizzBuzzText(String string) {
        return string.replace(FIZZ, EMPTY).replace(BUZZ, EMPTY);
    }
}
