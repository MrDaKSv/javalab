class MaximumFinder {

    public static <T extends Comparable<T>> T findMax(T[] elements) {
        if (elements == null || elements.length == 0) {
            return null;
        }

        T max = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i].compareTo(max) > 0) {
                max = elements[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] doubleArray = {1.5, 2.5, 3.5, 4.5, 5.5};
        String[] stringArray = {"apple", "orange", "banana"};

        System.out.println("Maximum Integer: " + findMax(intArray));
        System.out.println("Maximum Double: " + findMax(doubleArray));
        System.out.println("Maximum String: " + findMax(stringArray));
    }
}
