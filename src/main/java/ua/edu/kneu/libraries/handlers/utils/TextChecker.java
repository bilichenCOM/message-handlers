package ua.edu.kneu.libraries.handlers.utils;

import java.util.List;
import java.util.Optional;

public class TextChecker {

    public static boolean textContainsElementsFromList(String text, List<String> phrases) {
        return phrases.stream()
                .anyMatch(phrase -> phrase.length() > 0 && text.toLowerCase().contains(phrase));
    }

    public static Optional<String> removeAll(String from, List<String> removeList) {
        StringBuilder input = new StringBuilder(from.toLowerCase());
        for (String remove:removeList) {
            int start;
            if (!((start = input.indexOf(remove)) >= 0)) {
                continue;
            }
            int end = start + remove.length();
            input.delete(start, end);
        }
        if (input.length() == 0) {
            return Optional.empty();
        }
        String output = input.toString();
        return Optional.of(removePunctuation(output).trim());
    }

    private static String removePunctuation(String text) {
        return text.replaceAll("[,.?!;:\"]", "");
    }

    public static String capitalize(String text) {
        if (text.length() <= 1) {
            return text.toUpperCase();
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
