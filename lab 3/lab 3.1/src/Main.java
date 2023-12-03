import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Заданий список рядків
        List<String> strings = Arrays.asList("apple", "banana", "orange", "grape", "kiwi");

        // Відфільтруємо рядки, які містять літеру 'а', перетворимо у верхній регістр та збережемо у новий список
        List<String> result = strings.stream()
                .filter(s -> s.contains("a"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        // Виведемо результат
        System.out.println("Відфільтровані рядки, які містять літеру 'а' у верхньому регістрі:");
        System.out.println(result);
    }
}
