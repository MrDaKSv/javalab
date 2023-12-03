package org.example;
interface Validator<T> {
    boolean validate(T item);
}

class StringValidator implements Validator<String> {
    @Override
    public boolean validate(String item) {
        // Перевірка на наявність літер і пробілів в рядку
        return item != null && item.matches("[a-zA-Z\\s]+");
    }

}

class IntegerValidator implements Validator<Integer> {
    @Override
    public boolean validate(Integer item) {
        // Перевірка, чи є число простим
        if (item == null || item <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(item); i++) {
            if (item % i == 0) {
                return false;
            }
        }
        return true;
    }
}


public class Main {
    public static void main(String[] args) {
        StringValidator stringValidator = new StringValidator();
        IntegerValidator integerValidator = new IntegerValidator();

        String text = "Hello world";
        int number = -5;

        // Використання валідаторів для перевірки даних
        System.out.println("String is valid: " + stringValidator.validate(text));
        System.out.println("Integer is valid: " + integerValidator.validate(number));
    }
}
