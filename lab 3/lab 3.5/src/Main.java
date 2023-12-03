import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 3, 5, 7, 7, 11, 13, 17, 2, 19);

        long count = numbers.stream()
                .filter(Main::isPrime)
                .distinct()
                .count();

        System.out.println("Кількість різних простих чисел: " + count);
    }

    // Метод для перевірки, чи є число простим
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
