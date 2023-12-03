import java.util.List;

public class Main {
    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Creating lists of different types
        List<Integer> integerList = List.of(1, 2, 3);
        List<String> stringList = List.of("Apple", "Orange", "Banana");

        // Calling the method with different types of lists
        printList(integerList);
        printList(stringList);
    }
}
