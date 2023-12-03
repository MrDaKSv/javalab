import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Item {
    private String name;
    private double price;

    // Конструктор, геттери та сеттери
    // ...

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

class Order {
    private List<Item> items;

    // Конструктор, геттери та сеттери
    // ...

    public Order(List<Item> items) {
        this.items = items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Product 1", 30));
        itemList.add(new Item("Product 2", 40));
        itemList.add(new Item("Product 3", 55));
        itemList.add(new Item("Product 4", 20));
        itemList.add(new Item("Product 5", 60));
        itemList.add(new Item("Product 6", 61));
// Додайте ще товарів за потребою

        Order order = new Order(itemList);

        List<Order> orders = new ArrayList<>();
        orders.add(order);

// Фільтрація елементів замовлень за ціною понад 50 грн
        List<Item> filteredItems = orders.stream()
                .flatMap(ord -> ord.getItems().stream())
                .filter(item -> item.getPrice() > 50)
                .collect(Collectors.toList());


        // Виведення результатів
        System.out.println("Елементи з ціною понад 50 грн:");
        filteredItems.forEach(item -> System.out.println(item.getName() + ": " + item.getPrice()));
    }
}
