import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
// У файлі Product.java
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return "Product{name='" + name + "', price=" + price + '}';
    }
}

class PriceRangeCollector implements Collector<Product, Map<String, List<Product>>, Map<String, List<Product>>> {

    private static final String CHEAP = "Cheap";
    private static final String MODERATE = "Moderate";
    private static final String EXPENSIVE = "Expensive";

    private final double cheapThreshold;
    private final double moderateThreshold;

    public PriceRangeCollector(double cheapThreshold, double moderateThreshold) {
        this.cheapThreshold = cheapThreshold;
        this.moderateThreshold = moderateThreshold;
    }

    @Override
    public Supplier<Map<String, List<Product>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, List<Product>>, Product> accumulator() {
        return (priceRanges, product) -> {
            if (product.getPrice() <= cheapThreshold) {
                priceRanges.computeIfAbsent(CHEAP, k -> new ArrayList<>()).add(product);
            } else if (product.getPrice() <= moderateThreshold) {
                priceRanges.computeIfAbsent(MODERATE, k -> new ArrayList<>()).add(product);
            } else {
                priceRanges.computeIfAbsent(EXPENSIVE, k -> new ArrayList<>()).add(product);
            }
        };
    }

    @Override
    public BinaryOperator<Map<String, List<Product>>> combiner() {
        return (map1, map2) -> {
            map2.forEach((key, value) -> map1.merge(key, value, (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            }));
            return map1;
        };
    }

    @Override
    public Function<Map<String, List<Product>>, Map<String, List<Product>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}


public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", 20));
        products.add(new Product("Product 2", 40));
        products.add(new Product("Product 3", 60));
        products.add(new Product("Product 4", 80));
        products.add(new Product("Product 5", 100));

        double cheapThreshold = 30;
        double moderateThreshold = 70;

        Map<String, List<Product>> productsByPriceRange = products.stream()
                .collect(new PriceRangeCollector(cheapThreshold, moderateThreshold));

        // Виведення продуктів за діапазонами цін
        System.out.println("Cheap products: " + productsByPriceRange.getOrDefault("Cheap", Collections.emptyList()));
        System.out.println("Moderate products: " + productsByPriceRange.getOrDefault("Moderate", Collections.emptyList()));
        System.out.println("Expensive products: " + productsByPriceRange.getOrDefault("Expensive", Collections.emptyList()));
    }
}
