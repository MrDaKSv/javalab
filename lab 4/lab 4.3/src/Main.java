import java.util.List;

class MyClass {
    // Метод, що приймає список будь-яких типів
    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    public static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3);
        List<String> strList = List.of("apple", "banana", "orange");

        // Виклик методу printList зі списками різних типів
        printList(intList);
        printList(strList);
    }
}
