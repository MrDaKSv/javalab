import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("City A", "City B", "City C", "City D", "City E");
        Map<String, Integer> temperatures = new HashMap<>();
        temperatures.put("City A", 32); // Припустимо, що це реальна температура для кожного міста
        temperatures.put("City B", 28);
        temperatures.put("City C", 35);
        temperatures.put("City D", 30);
        temperatures.put("City E", 29);

        cities.stream()
                .peek(city -> System.out.println("Checking temperature in " + city + ": " + temperatures.get(city) + "°C"))
                .filter(city -> temperatures.get(city) > 30)
                .forEach(city -> System.out.println(city + " has a temperature above 30°C"));
    }
}
