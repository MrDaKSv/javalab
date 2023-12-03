import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private double distance;
    private Driver driver;
    private Client client;

    public OrderHistory(double distance, Driver driver, Client client) {
        this.distance = distance;
        this.driver = driver;
        this.client = client;
    }

    public double getDistance() {
        return distance;
    }

    public Driver getDriver() {
        return driver;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return "Дистанція: " + distance + " км, Водій: " + driver.getFullName() + ", Клієнт: " + client.getFullName();
    }
    public void saveOrderHistoryToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("OrderHistory");
            writer.newLine();
            writer.write("distance=" + getDistance());
            writer.newLine();
            writer.write("driver=" + getDriver().getFullName());
            writer.newLine();
            writer.write("client=" + getClient().getFullName());
            writer.newLine();
            writer.newLine(); // Розділюємо об'єкти порожнім рядком
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


