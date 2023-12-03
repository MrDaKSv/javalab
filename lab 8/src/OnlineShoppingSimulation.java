import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Store {
    private Map<String, Integer> products;
    private Lock lock;
    private Condition condition;

    public Store() {
        products = new HashMap<>();
        products.put("Product 1", 2);
        products.put("Product 2", 2);
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public boolean addToCart(String product) {
        lock.lock();
        try {
            if (products.containsKey(product) && products.get(product) > 0) {
                int currentCount = products.get(product);
                products.put(product, currentCount - 1);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void removeFromCart(String product) {
        lock.lock();
        try {
            int currentCount = products.getOrDefault(product, 0);
            products.put(product, currentCount + 1);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void waitForAvailability(String product) throws InterruptedException {
        lock.lock();
        try {
            while (!products.containsKey(product) || products.get(product) <= 0) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean tryToAcquireLock() {
        return lock.tryLock();
    }

    public void interruptiblyAcquireLock() throws InterruptedException {
        lock.lockInterruptibly();
    }

    public void unlockLock() {
        lock.unlock();
    }
}

class User implements Runnable {
    private Store store;
    private String name;

    public User(String name, Store store) {
        this.name = name;
        this.store = store;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            String randomProduct = "Product " + (random.nextInt(2) + 1);
            boolean added = store.addToCart(randomProduct);
            if (added) {
                System.out.println(name + " added " + randomProduct + " to the cart");
                try {
                    Thread.sleep(random.nextInt(2000) + 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                store.removeFromCart(randomProduct);
                System.out.println(name + " removed " + randomProduct + " from the cart");
            } else {
                System.out.println("Sorry, " + name + " couldn't add " + randomProduct + " to the cart");
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
    }
}
