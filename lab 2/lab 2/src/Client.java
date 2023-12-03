import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Client {
    private String fullName;  // ПІБ клієнта
    private Date birthDate;    // Дата народження клієнта
    private String phoneNumber;  // Номер телефону клієнта

    // Конструктор класу для ініціалізації полів
    public Client(String fullName, Date birthDate, String phoneNumber) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    // Геттери і сеттери для доступу до полів класу
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void saveClientToFile(String fileName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Client");
            writer.newLine();
            writer.write("fullName=" + getFullName());
            writer.newLine();

            // Форматуємо дату у відповідному форматі перед записом у файл
            String formattedDate = dateFormat.format(getBirthDate());
            writer.write("birthDate=" + formattedDate);
            writer.newLine();

            writer.write("phoneNumber=" + getPhoneNumber());
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}