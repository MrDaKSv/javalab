import java.util.List;
import java.util.ArrayList;
class Calculator {
    public double sumNumbers(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number num : numbers) {
            sum += num.doubleValue(); // Підрахунок суми чисел
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {

        List<Number> numbers = new ArrayList<>();
        numbers.add(4); // Додаємо Integer
        numbers.add(10.0); // Додаємо Double

        Calculator calculator = new Calculator();
        double result = calculator.sumNumbers(numbers);
        System.out.println("Sum of integers: " + result);
    }
}