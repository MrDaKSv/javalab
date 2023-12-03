import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Driver {
    private String fullName;
    private Date birthDate;
    private int experience;
    private String phoneNumber;

    private Transport car;



    public Driver(String fullName, Date birthDate, int experience, String phoneNumber,Transport car) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.experience = experience;
        this.phoneNumber = phoneNumber;
        this.car=car;
    }

    public Transport getCar() {
        return car;
    }

    public void setCar(Transport car) {
        this.car = car;
    }

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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", experience=" + experience +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", car=" + car.toString() +
                '}';
    }

    public void saveDriverToFile(String fileName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Driver");
            writer.newLine();
            writer.write("fullName=" + getFullName());
            writer.newLine();

            // Форматуємо дату у відповідному форматі перед записом у файл
            String formattedDate = dateFormat.format(getBirthDate());
            writer.write("birthDate=" + formattedDate);
            writer.newLine();

            writer.write("experience=" + getExperience());
            writer.newLine();
            writer.write("phoneNumber=" + getPhoneNumber());
            writer.newLine();
            writer.write("car=" + getCar().getTaxi_number());
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

