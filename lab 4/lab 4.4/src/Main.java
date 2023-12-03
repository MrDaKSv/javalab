class Pair<T> {
    private T first;
    private T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public boolean isEqualTo(Pair<T> otherPair) {
        return this.first.equals(otherPair.getFirst()) &&
                this.second.equals(otherPair.getSecond());
    }
}



public class Main {
    public static void main(String[] args) {
        Pair<String> stringPair = new Pair<>("hello", "world");
        Pair<Integer> intPair = new Pair<>(10, 20);

        // Порівняння двох пар
        boolean areEqual = stringPair.isEqualTo(new Pair<>("hello", "world"));

        System.out.println("String Pair: " + stringPair.getFirst() + ", " + stringPair.getSecond());
        System.out.println("Are the string pairs equal? " + areEqual);

        boolean isEqual = intPair.isEqualTo(new Pair<>(10,20));
        System.out.println("Integer Pair: " + intPair.getFirst() + ", " + intPair.getSecond());
        System.out.println("Are the string pairs equal? " + isEqual);
    }
}
