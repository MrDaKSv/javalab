import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Створення ArrayList для зберігання чисел
        ArrayList<Integer> numbersList = new ArrayList<>();
        numbersList.add(10);
        numbersList.add(20);
        numbersList.add(30);

        // Додавання нового числа в ArrayList
        numbersList.add(40);

        // Вилучення числа з ArrayList
        numbersList.remove(1);

        // Зміна числа в ArrayList
        numbersList.set(0, 100);

        // Вивід елементів ArrayList
        System.out.println("Numbers in ArrayList:");
        for (Integer number : numbersList) {
            System.out.println(number);
        }

        // Створення HashMap для зберігання пари ключ-значення
        HashMap<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("Key1", "Value1");
        keyValueMap.put("Key2", "Value2");
        keyValueMap.put("Key3", "Value3");

        // Зміна значення для певного ключа в HashMap
        keyValueMap.replace("Key2", "NewValue");

        // Вилучення значення по ключу з HashMap
        keyValueMap.remove("Key1");

        // Вивід елементів HashMap
        System.out.println("\nKey-Value pairs in HashMap:");
        for (String key : keyValueMap.keySet()) {
            System.out.println(key + ": " + keyValueMap.get(key));
        }
    }
}
