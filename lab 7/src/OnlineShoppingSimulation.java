import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Store {
    private final Map<String, Integer> products;
    private final Lock lock = new ReentrantLock();

    public Store() {
        products = new HashMap<>();
        products.put("Product 1", 5);
        products.put("Product 2", 5);
    }

    public boolean addToCart(String product) {
        lock.lock();
        try {
            if (products.containsKey(product) && products.get(product) > 0) {
                int currentCount = products.get(product);
                products.put(product, currentCount - 1);
                System.out.println(Thread.currentThread().getName() + " added " + product + " to the cart");
                return true;
            }
            System.out.println("Sorry, " + Thread.currentThread().getName() + " couldn't add " + product + " to the cart");
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean returnToStore(String product) {
        lock.lock();
        try {
            if (products.containsKey(product)) {
                int currentCount = products.get(product);
                products.put(product, currentCount + 1);
                System.out.println(Thread.currentThread().getName() + " returned " + product + " to the store");
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public int getProductCount(String product) {
        return products.getOrDefault(product, 0);
    }
}

class User implements Runnable {
    private final Store store;
    private final String name;

    public User(String name, Store store) {
        this.name = name;
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            String randomProduct = "Product " + ((i % 2) + 1);
            boolean added = store.addToCart(randomProduct);
            if (added) {
                boolean returned = store.returnToStore(randomProduct);
                if (returned) {
                    System.out.println(name + " successfully returned " + randomProduct + " to the store");
                } else {
                    System.out.println("Failed to return " + randomProduct + " by " + name);
                }
            }
        }
    }
}

public class OnlineShoppingSimulation {
    public static void main(String[] args) {
        Store store = new Store();
        Thread[] users = new Thread[5];

        for (int i = 0; i < 5; i++) {
            User user = new User("User " + (i + 1), store);
            users[i] = new Thread(user);
            users[i].start();
        }

        for (Thread user : users) {
            try {
                user.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total products remaining: Product 1 - " + store.getProductCount("Product 1") +
                ", Product 2 - " + store.getProductCount("Product 2"));
    }
}
