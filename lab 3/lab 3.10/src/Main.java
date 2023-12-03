import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> sentences = Arrays.asList(
                "The quick brown fox jumps over the lazy dog",
                "This is a sentence with some common words like and, is, the"
        );

        Set<String> stopWords = Set.of("the", "and", "is"); // Стоп-слова

        Set<String> uniqueWords = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split("\\W+"))) // Розділити на слова
                .map(String::toLowerCase) // Перевести у нижній регістр
                .filter(word -> !stopWords.contains(word)) // Відфільтрувати стоп-слова
                .filter(word -> !word.isEmpty()) // Видалити порожні слова
                .collect(Collectors.toSet()); // Повернути унікальні слова

        System.out.println("Unique words: " + uniqueWords);
    }
}
