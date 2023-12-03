import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("One", "Two", "Three", "Four", "Five");

        String concatenatedString = String.join(", ", words);
        System.out.println("Concatenated string: " + concatenatedString);
    }
}
