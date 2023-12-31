package net.sammy;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class AppUtils {
    private AppUtils() {}

    static BiFunction<Map<String, String>, Map.Entry<String, String>, Map<String, String>> accumulator =
            (acc, item) -> {
                if (!acc.containsValue(item.getValue())) {
                    acc.put(item.getKey(), item.getValue());
                }
                return acc;
            };

    static BinaryOperator<Map<String, String>> combiner =
            (mapA, mapB) -> {
                mapA.putAll(mapB);
                return mapA;
            };
}
