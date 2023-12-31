package net.sammy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static net.sammy.AppUtils.*;

public class Application {

    public static void main(String[] args) {
        Map<String, String> placesAndCapitals = Map.of("antartica", "new town",
                "fantasia", "small ville",
                "mars", "big city",
                "sun", "hot plazza",
                "underworld", "new town");

        System.out.println("Original Map:");
        System.out.println(placesAndCapitals);

        System.out.println("==");
        System.out.println("Using 'reduce' and lambda functions:");
        HashMap<String, String> reduced = placesAndCapitals.entrySet()
                .stream()
                .reduce(new HashMap<>(),
                        (acc, item) -> {
                            if (!acc.containsValue(item.getValue())) {
                                acc.put(item.getKey(), item.getValue());
                            }
                            return acc;
                        },
                        (mapA, mapB) -> {
                            mapA.putAll(mapB);
                            return mapA;
                        });
        System.out.println(reduced);

        System.out.println("==");
        System.out.println("Refactoring reduce:");
        System.out.println(
            placesAndCapitals.entrySet()
                    .stream()
                    .reduce(new HashMap<>(), accumulator, combiner)
        );

        Predicate<Map.Entry<String, String>> checkDistinct = new Predicate<>() {
            Map<String, Boolean> alreadySeen = new HashMap<>();
    
            @Override
            public boolean test(Map.Entry<String, String> entry) {
                return alreadySeen.putIfAbsent(entry.getValue(), Boolean.TRUE) == null;
            }
        };
    
        System.out.println("==");
        System.out.println("Using custom predicate:");
        Map<String, String> result = placesAndCapitals.entrySet()
                .stream()
                .filter(checkDistinct)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(result);

        System.out.println("==");
        System.out.println("Using stream twice:");
        System.out.println(
                placesAndCapitals.entrySet().stream().
                        collect(Collectors.groupingBy(Map.Entry::getValue))
                        .entrySet().stream()
                        .collect(Collectors.toMap(e -> e.getValue().get(0).getKey(), Map.Entry::getKey))
        );
    }
}
