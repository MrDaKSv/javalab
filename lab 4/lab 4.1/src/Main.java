class Box<T> {
    private T item;

    public void put(T item) {
        this.item = item;
    }

    public T take() {
        T takenItem = item;
        item = null;
        return takenItem;
    }

    public static void main(String[] args) {
        // Приклади використання
        Box<Integer> integerBox = new Box<>();
        integerBox.put(10);
        System.out.println("Item taken from integerBox: " + integerBox.take());

        Box<String> stringBox = new Box<>();
        stringBox.put("Hello, Generics!");
        System.out.println("Item taken from stringBox: " + stringBox.take());

        Box<Double> doubleBox = new Box<>();
        doubleBox.put(12.3);
        System.out.println("Item taken from stringBox: " + doubleBox.take());
    }
}
