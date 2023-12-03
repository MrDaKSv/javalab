import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

// Базовий клас для предметної області - Транспортний Засіб
class Transport implements Serializable {
    private String brandModel;
    private int year;
    private String fuelType;
    private String bodyType;
    private String color;
    private String licensePlate;

    private String taxi_number;
    private int passengerCapacity;
    private double cargoCapacity;

    private boolean owner;
    private double mileage;


    public Transport(String brandModel, int year, String fuelType, String bodyType, String color, String licensePlate,String taxi_number ,int passengerCapacity, double cargoCapacity,boolean owner,double mileage) {
        this.brandModel = brandModel;
        this.year = year;
        this.fuelType = fuelType;
        this.bodyType = bodyType;
        this.color = color;
        this.licensePlate = licensePlate;
        this.passengerCapacity = passengerCapacity;
        this.cargoCapacity = cargoCapacity;
        this.taxi_number=taxi_number;
        this.owner=owner;
        this.mileage=mileage;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public int getYear() {
        return year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getColor() {
        return color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getTaxi_number() {
        return taxi_number;
    }

    public void setBrand_model(String brandModel){this.brandModel=brandModel;}
    public void setTaxi_number(String taxi_number) {
        this.taxi_number = taxi_number;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }






    @Override
    public String toString() {
        return "Transport" +
                "\nbrandModel='" + brandModel + '\'' +
                "\nyear=" + year +
                "\nfuelType='" + fuelType + '\'' +
                "\nbodyType='" + bodyType + '\'' +
                "\ncolor='" + color + '\'' +
                "\nlicensePlate='" + licensePlate + '\'' +
                "\ntaxi_number='" + taxi_number + '\'' +
                "\npassengerCapacity=" + passengerCapacity +
                "\ncargoCapacity=" + cargoCapacity +
                "\nowner=" + owner +
                "\nmileage=" + mileage +
                "\n";
    }


    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Transport");
            writer.newLine();
            writer.write("brandModel=" + brandModel);
            writer.newLine();
            writer.write("year=" + year);
            writer.newLine();
            writer.write("fuelType=" + fuelType);
            writer.newLine();
            writer.write("bodyType=" + bodyType);
            writer.newLine();
            writer.write("color=" + color);
            writer.newLine();
            writer.write("licensePlate=" + licensePlate);
            writer.newLine();
            writer.write("taxi_number=" + taxi_number);
            writer.newLine();
            writer.write("passengerCapacity=" + passengerCapacity);
            writer.newLine();
            writer.write("cargoCapacity=" + cargoCapacity);
            writer.newLine();
            writer.write("owner=" + owner);
            writer.newLine();
            writer.write("mileage=" + mileage);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Метод для зчитування об'єкта з файлу



}
class Reader {
    public void readAndAddFromFile(List<Transport> transportList, List<Driver> driverList, List<Client> clientList, List<OrderHistory> orderHistoryList, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Transport")) {
                    // Читання та додавання об'єктів транспорту до списку транспорту
                    Transport transport = readTransportFromLine(reader);
                    transportList.add(transport);
                } else if (line.startsWith("Driver")) {
                    // Читання та додавання об'єктів водіїв до списку водіїв
                    Driver driver = readDriverFromLine(reader,transportList);
                    driverList.add(driver);
                } else if (line.startsWith("Client")) {
                    // Читання та додавання об'єктів клієнтів до списку клієнтів
                    Client client = readClientFromLine(reader);
                    clientList.add(client);
                } else if (line.startsWith("OrderHistory")) {
                    // Читання та додавання об'єктів історії замовлень до списку історії замовлень
                    OrderHistory orderHistory = readOrderHistoryFromLine(reader,driverList,clientList);
                    orderHistoryList.add(orderHistory);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Методи для читання об'єктів різних типів з рядка
    private Transport readTransportFromLine(BufferedReader reader) throws IOException {
        String brandModel = getValue(reader.readLine());
        int year = Integer.parseInt(getValue(reader.readLine()));
        String fuelType = getValue(reader.readLine());
        String bodyType = getValue(reader.readLine());
        String color = getValue(reader.readLine());
        String licensePlate = getValue(reader.readLine());
        String taxi_number = getValue(reader.readLine());
        int passengerCapacity = Integer.parseInt(getValue(reader.readLine()));
        double cargoCapacity = Double.parseDouble(getValue(reader.readLine()));
        boolean owner = Boolean.parseBoolean(getValue(reader.readLine()));
        double mileage = Double.parseDouble(getValue(reader.readLine()));

        return new Transport(brandModel, year, fuelType, bodyType, color, licensePlate,
                taxi_number, passengerCapacity, cargoCapacity, owner, mileage);
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Можна повернути null або викинути виключення, якщо формат невірний
        }
    }

    private Driver readDriverFromLine(BufferedReader reader,List<Transport> transportList) throws IOException {


        String fullName = getValue(reader.readLine());
        Date birthDate = parseDate(getValue(reader.readLine()));
        int experience = Integer.parseInt(getValue(reader.readLine()));
        String phoneNumber = getValue(reader.readLine());
        Transport car = null;
        String taxiNumber = getValue(reader.readLine()) ;
        for (Transport transport:transportList) {
            if(taxiNumber.equals(transport.getTaxi_number())){
                car= transport;
            }
        }

        return new Driver(fullName, birthDate, experience, phoneNumber,car);
    }


    private Client readClientFromLine(BufferedReader reader) throws IOException {
        String fullName = getValue(reader.readLine());
        Date birthDate = parseDate(getValue(reader.readLine()));
        String phoneNumber = getValue(reader.readLine());

        return new Client(fullName, birthDate, phoneNumber);
    }
    private OrderHistory readOrderHistoryFromLine(BufferedReader reader, List<Driver> driverList,List<Client> clientList) throws IOException {
        double distance = Double.parseDouble(getValue(reader.readLine()));
        String name1 = getValue(reader.readLine());
        Driver driver = null;
        for (Driver driver1 : driverList) {
            if (driver1.getFullName().equals(name1)) {
                driver = driver1;
            }
        }

        String name2 = getValue(reader.readLine());
        Client client = null;
        for (Client client1 : clientList) {
            if (client1.getFullName().equals(name2)) {
                client = client1;
            }
        }
        return new OrderHistory(distance, driver, client);
    }

    private String getValue(String line) {
        String[] parts = line.split("=", 2);
        return parts[1];
    }

}
class Object{
    int choice;

    public Object(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public void createObject(List<Transport> transportList, List<Driver> driverList, List<Client> clientList, List<OrderHistory> orderHistoryList, String filePath,
                             Map<Driver, Double> driverMileageMap, Map<Client, Integer> clientOrderCountMap ) {
        Scanner scanner = new Scanner(System.in);

        switch (choice) {
            case 1: // Транспорт
                // Введення інформації про транспорт
                System.out.println("Введіть інформацію про transport:");
                int coun = 0;
                while (true) {

                    System.out.println("Транспорт #" + (coun + 1));
                    System.out.print("Марка: ");
                    String brend_model = scanner.nextLine();
                    System.out.print("Рік випуску: ");
                    int year_or_release = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера після nextInt
                    System.out.print("Тип пального: ");
                    String type_of_oil = scanner.nextLine();
                    System.out.print("Тип кузова: ");
                    String type_of_body = scanner.nextLine();
                    System.out.print("Колір: ");
                    String color = scanner.nextLine();
                    System.out.print("Номерний знак: ");
                    String licensePlate = scanner.nextLine();
                    System.out.print("Унікальний номер таксі: ");
                    String taxi_number = scanner.nextLine();
                    System.out.print("Місткість пасажирів: ");
                    int passengerCapacity = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Вантажопідйомність: ");
                    double cargoCapacity = scanner.nextDouble();
                    scanner.nextLine();
                    boolean owner=false;
                    double mileage=0.0;

                    Transport transport = new Transport(brend_model, year_or_release, type_of_oil, type_of_body, color, licensePlate,taxi_number, passengerCapacity, cargoCapacity,owner,mileage);
                    transportList.add(transport);
                    transport.saveToFile(filePath);



                    System.out.println("Чи бажаєти ви і далі заповняти це?: 1 - так,0 - ні");
                    int numberCount = scanner.nextInt();
                    scanner.nextLine();
                    if (numberCount == 0) {

                        break;
                    }
                    else{
                        coun++;
                    }

                }
                break;
            case 2: // Водій
                System.out.println("Введіть інформацію про водіїв:");
                // Введення інформації про водіїв
                coun = 0;
                while (true) {

                    System.out.println("Водій #" + (coun + 1));
                    System.out.print("ПІБ: ");
                    String name = scanner.nextLine();
                    System.out.print("Дата народження (формат: рік-місяць-день): ");
                    String dateStr = scanner.nextLine();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date birthDate = null;

                    try {
                        birthDate = dateFormat.parse(dateStr);
                    } catch (ParseException e) {
                        System.out.println("Неправильний формат дати. Спробуйте ще раз.");
                        continue; // Повторний ввід дати
                    }
                    System.out.print("Стаж водіння: ");
                    int experience = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера після nextInt
                    System.out.print("Номер телефону: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Введіть номер taxi яку використовує цей водій: ");
                    while(true) {
                        String index_taxi_number = scanner.nextLine();
                        boolean carFound = false,carUsed=false;

                        // Перевіряємо, чи введений номер машини співпадає з номером одного з транспортних засобів
                        for (Transport transport : transportList) {
                            if (transport.getTaxi_number().equals(index_taxi_number)) {
                                if(!transport.isOwner()) {

                                    // Якщо номер машини співпадає, створюємо зв'язок і виходимо з циклу
                                    Driver driver = new Driver(name, birthDate, experience, phoneNumber, transport);
                                    driverList.add(driver);
                                    carFound = true;
                                    transport.setOwner(true);
                                    driver.saveDriverToFile(filePath);
                                    break;
                                }
                                else{
                                    carFound = true;
                                    carUsed=true;
                                }
                            }
                        }

                        if (!carFound) {
                            System.out.println("Транспортний засіб з введеним номером не знайдений. Будь ласка, введіть коректний номер.");
                        }
                        else if(carUsed){
                            System.out.println("Транспортний засіб зайнятий. Будь ласка, введіть інший номер.");
                        }
                        else{
                            break;
                        }
                    }
                    System.out.println("Чи бажаєте ви ввести ще одного водія? Введіть 1 - так, 0 - ні");
                    int numberCount = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера після nextInt

                    if (numberCount == 0) {
                        break; // Виходимо з циклу, якщо користувач ввів 0
                    } else {
                        coun++;
                    }
                }
                break;
            case 3: // Клієнт
                System.out.println("Введіть інформацію про клієнтів:");
                int clientCount = 0;

                while (true) {
                    System.out.println("Клієнт #" + (clientCount + 1));
                    System.out.print("ПІБ: ");
                    String name = scanner.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    // Вводимо дату від користувача
                    System.out.print("Дата народження (формат: рік-місяць-день): ");
                    String dateStr = scanner.nextLine();

                    try {
                        Date birthDate = dateFormat.parse(dateStr); // Парсимо рядок у дату
                        scanner.nextLine(); // Очистка буфера після nextInt
                        System.out.print("Номер телефону: ");
                        String phoneNumber = scanner.nextLine();
                        // Тепер можна використовувати birthDate для створення об'єкта клієнта або для інших операцій з датою
                        Client client = new Client(name, birthDate, phoneNumber);
                        clientList.add(client);
                        client.saveClientToFile(filePath);
                    } catch (ParseException e) {
                        e.printStackTrace(); // Обробка помилок парсингу дати
                    }


                    System.out.println("Чи бажаєте ви ввести ще одного клієнта? Введіть 1 - так, 0 - ні");
                    int numberCount = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера після nextInt

                    if (numberCount == 0) {
                        break; // Виходимо з циклу, якщо користувач ввів 0
                    } else {
                        clientCount++;
                    }
                }
                break;
            case 4: // Історія замовлень
                // Введення інформації про історію замовлень
                System.out.println("Введіть інформацію про історію замовлень:");


                clientCount = 0;
                while (true) {

                    System.out.println("Замовлення #" + (clientCount + 1));
                    System.out.print("Дистанція (км): ");
                    double distance = scanner.nextDouble();
                    scanner.nextLine(); // Очистка буфера після nextDouble
                    System.out.print("Виберіть водія (від 1 до " + driverList.size() + "): ");
                    int driverIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Очистка буфера після nextInt
                    System.out.print("Виберіть клієнта (від 1 до " + clientList.size() + "): ");
                    int clientIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Очистка буфера після nextInt
                    // Створення об'єкта історії замовлення і додавання його до списку
                    OrderHistory order = new OrderHistory(distance, driverList.get(driverIndex), clientList.get(clientIndex));
                    orderHistoryList.add(order);
                    order.saveOrderHistoryToFile(filePath);
                    // Перевірка чи водій вже є в історії замовлень
                    System.out.println("Чи бажаєте ви ввести ще одне замовлення? Введіть 1 - так, 0 - ні");
                    int numberCount = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера після nextInt

                    if (numberCount == 0) {
                        break; // Виходимо з циклу, якщо користувач ввів 0
                    }
                    else{
                        clientCount++;
                    }
                }

                for (OrderHistory orderHistory : orderHistoryList) {
                    Driver driver = orderHistory.getDriver();
                    double mileage = driverMileageMap.getOrDefault(driver, 0.0);
                    double newMileage = mileage + orderHistory.getDistance();
                    driverMileageMap.put(driver, newMileage);
                    Transport driverCar = driver.getCar();
                    driverCar.setMileage(newMileage);
                }
                break;
            default:
                System.out.println("Неправильний вибір.");
                break;
        }
    }

}


class TransportProcessor {

    public void writeToBinaryFileFromConsole(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                Transport transport = readTransportFromConsole(scanner);
                outputStream.writeObject(transport);
                System.out.println("Об'єкт успішно записано у бінарний файл " + fileName);

                System.out.println("Бажаєте додати ще один об'єкт? (1/0)");
                String input = scanner.nextLine().trim();
                if (!input.equalsIgnoreCase("1")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Transport> readFromBinaryFileToList(String fileName) {
        List<Transport> transportList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    Transport transport = (Transport) inputStream.readObject();
                    transportList.add(transport);
                } catch (EOFException e) {
                    break; // Кінець файлу, читання завершено
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transportList;
    }

    private Transport readTransportFromConsole(Scanner scanner) {
        System.out.println("Введіть інформацію про транспорт:");

        System.out.print("Марка: ");
        String brand_model = scanner.nextLine();
        System.out.print("Рік випуску: ");
        int year_of_release = Integer.parseInt(scanner.nextLine());
        System.out.print("Тип пального: ");
        String type_of_oil = scanner.nextLine();
        System.out.print("Тип кузова: ");
        String type_of_body = scanner.nextLine();
        System.out.print("Колір: ");
        String color = scanner.nextLine();
        System.out.print("Номерний знак: ");
        String licensePlate = scanner.nextLine();
        System.out.print("Унікальний номер таксі: ");
        String taxi_number = scanner.nextLine();
        System.out.print("Місткість пасажирів: ");
        int passengerCapacity = Integer.parseInt(scanner.nextLine());
        System.out.print("Вантажопідйомність: ");
        double cargoCapacity = Double.parseDouble(scanner.nextLine());

        boolean owner = false;
        double mileage = 0.0;

        return new Transport(brand_model, year_of_release, type_of_oil, type_of_body, color, licensePlate, taxi_number, passengerCapacity, cargoCapacity, owner, mileage);
    }
}


class DataManager {

    public void updateObject(String objectType, List<Transport> transportList, List<Driver> driverList, List<Client> clientList, List<OrderHistory> orderHistoryList) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        switch (objectType) {
            case "1":
                System.out.println("Оберіть транспорт за номером:");
                for (int i = 0; i < transportList.size(); i++) {
                    System.out.println((i + 1) + ". " + transportList.get(i));
                }
                System.out.println("Введіть число");
                int transportIndex = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера після nextInt
                if (transportIndex > 0 && transportIndex <= transportList.size()) {
                    updateTransport(transportList.get(transportIndex - 1));
                    flag=true;
                } else {
                    System.out.println("Неправильний номер транспорту.");
                }
                break;
            case "2":
                System.out.println("Оберіть водія за номером:");
                for (int i = 0; i < driverList.size(); i++) {
                    System.out.println((i + 1) + ". " + driverList.get(i));
                }
                System.out.println("Введіть число");
                int driverIndex = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера після nextInt
                if (driverIndex > 0 && driverIndex <= driverList.size()) {
                    updateDriver(driverList.get(driverIndex - 1));
                    flag=true;
                } else {
                    System.out.println("Неправильний номер водія.");
                }
                break;
            case "3":
                System.out.println("Оберіть клієнта за номером:");
                for (int i = 0; i < clientList.size(); i++) {
                    System.out.println((i + 1) + ". " + clientList.get(i));
                }
                System.out.println("Введіть число");
                int clientIndex = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера після nextInt
                if (clientIndex > 0 && clientIndex <= clientList.size()) {
                    updateClient(clientList.get(clientIndex - 1));
                    flag=true;
                } else {
                    System.out.println("Неправильний номер клієнта.");
                }
                break;

            default:
                System.out.println("Об'єкт не знайдено");
                break;
        }
        if (flag){
            clearAndUpdateFile(transportList,driverList,clientList,orderHistoryList);
            System.out.println("Об'єкт успішно змінений :)");
        }

    }

    public void deleteObject(String objectType, List<Transport> transportList, List<Driver> driverList, List<Client> clientList, List<OrderHistory> orderHistoryList) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        switch (objectType) {
            case "1":
                System.out.println("Оберіть транспорт для видалення за номером:");
                for (int i = 0; i < transportList.size(); i++) {
                    System.out.println((i + 1) + ". " + transportList.get(i));
                }
                System.out.println("Введіть число:");
                int transportIndex = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера після nextInt
                if (transportIndex > 0 && transportIndex <= transportList.size()) {
                    transportList.remove(transportIndex - 1);
                    flag = true;
                } else {
                    System.out.println("Неправильний номер транспорту.");
                }
                break;
            case "2":
                System.out.println("Оберіть водія для видалення за номером:");
                for (int i = 0; i < driverList.size(); i++) {
                    System.out.println((i + 1) + ". " + driverList.get(i));
                }
                System.out.println("Введіть число:");
                int driverIndex = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера після nextInt
                if (driverIndex > 0 && driverIndex <= driverList.size()) {
                    driverList.remove(driverIndex - 1);
                    flag = true;
                } else {
                    System.out.println("Неправильний номер водія.");
                }
                break;
            case "3":
                System.out.println("Оберіть клієнта для видалення за номером:");
                for (int i = 0; i < clientList.size(); i++) {
                    System.out.println((i + 1) + ". " + clientList.get(i));
                }
                System.out.println("Введіть число:");
                int clientIndex = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера після nextInt
                if (clientIndex > 0 && clientIndex <= clientList.size()) {
                    clientList.remove(clientIndex - 1);
                    flag = true;
                } else {
                    System.out.println("Неправильний номер клієнта.");
                }
                break;
            case "4":
                System.out.println("Оберіть історію замовлення для видалення за номером:");
                for (int i = 0; i < orderHistoryList.size(); i++) {
                    System.out.println((i + 1) + ". " + orderHistoryList.get(i));
                }
                System.out.println("Введіть число:");
                int orderHistoryIndex = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера після nextInt
                if (orderHistoryIndex > 0 && orderHistoryIndex <= orderHistoryList.size()) {
                    orderHistoryList.remove(orderHistoryIndex - 1);
                    flag = true;
                } else {
                    System.out.println("Неправильний номер історії замовлення.");
                }
                break;
            default:
                System.out.println("Об'єкт не знайдено");
                break;
        }

        if (flag) {
            clearAndUpdateFile(transportList,driverList,clientList,orderHistoryList);
            System.out.println("Об'єкт успішно видалився :(");
        }
    }


    private static final String FILE_PATH = "C:\\Users\\Bogdan\\Desktop\\javalab\\lab 2\\text.txt"; // Замініть на свій шлях до файлу

    private void clearAndUpdateFile(List<Transport> transportList, List<Driver> driverList, List<Client> clientList, List<OrderHistory> orderHistoryList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Очищення файлу
            writer.write(""); // Записуємо порожній рядок, щоб очистити вміст

            // Оновлення файлу з оновленими списками
            for (Transport transport:transportList
                 ) {
                transport.saveToFile(FILE_PATH);
            }
            for (Driver driver:driverList
                 ) {
                driver.saveDriverToFile(FILE_PATH);
            }
            for (Client client:clientList
                 ) {
                client.saveClientToFile(FILE_PATH);
            }
            for (OrderHistory orderHistory:orderHistoryList
                 ) {
                orderHistory.saveOrderHistoryToFile(FILE_PATH);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void updateTransport(Transport transport) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Обрано транспорт:");
        System.out.println(transport); // Припустимо, що є метод toString() для виведення даних про транспорт

        // Логіка зміни полів
        System.out.print("Нова марка: ");
        String newBrand = scanner.nextLine();
        System.out.println(newBrand);
        transport.setBrand_model(newBrand);
        System.out.println(transport.getBrandModel());
        // Аналогічно оновлюйте інші поля за необхідності
    }

    private void updateDriver(Driver driver) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Обрано водія:");
        System.out.println(driver); // Припустимо, що є метод toString() для виведення даних про водія

        // Логіка зміни полів
        System.out.print("Нове ім'я: ");
        String newName = scanner.nextLine();
        driver.setFullName(newName);

        // Аналогічно оновлюйте інші поля за необхідності
    }

    private void updateClient(Client client) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Обрано клієнта:");
        System.out.println(client); // Припустимо, що є метод toString() для виведення даних про клієнта

        // Логіка зміни полів
        System.out.print("Нове ім'я: ");
        String newName = scanner.nextLine();
        client.setFullName(newName);

        // Аналогічно оновлюйте інші поля за необхідності
    }
}

public class TaxiService {

    public static void main(String[] args) {
        // Створення списків об'єктів
        Scanner scanner = new Scanner(System.in);
        List<Transport> transportList = new ArrayList<>();
        List<Driver> driverList = new ArrayList<>();
        List<Client> clientList = new ArrayList<>();
        List<OrderHistory> orderHistoryList = new ArrayList<>();

        Map<Driver, Double> driverMileageMap = new HashMap<>();
        Map<Client, Integer> clientOrderCountMap = new LinkedHashMap<>();

        String filePath = "C:\\Users\\Bogdan\\Desktop\\javalab\\lab 2\\text.txt";


        Reader reader = new Reader();
        reader.readAndAddFromFile(transportList, driverList, clientList, orderHistoryList, filePath);

        while (true){
            boolean time = false;
            System.out.println("Оберіть з яким типом фалйу хочете працювати:");
            System.out.println("Натисність 1: щоб обрати Текстовий файл");
            System.out.println("Натисність 2: щоб обрати бінарний файл");
            System.out.println("Ведіть число:");
            int fileChoice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера після nextInt
            if (fileChoice == 1) {

                while (true) {
                    System.out.println("Наш функціонал для роботи з текстовим файлом:");
                    System.out.println("Натисність 1: щоб ввести інфу про обєкти");
                    System.out.println("Натисність 2: щоб вивести інформацію");
                    System.out.println("Натисність 3: щоб ввімкнути редагування");
                    System.out.println("Натисність 4: щоб видалити обєкт з бази");
                    System.out.println("Натисність 0: щоб завершити програму");
                    System.out.println("Ведіть число:");
                    int Mainswitch_of = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера після nextInt
                    switch (Mainswitch_of) {
                        case 1:
                            System.out.println("Виберіть обєкт" +
                                    "\n1.Транспорт" +
                                    "\n2.Водій" +
                                    "\n3.Клієнт" +
                                    "\n4.Замовлення");
                            System.out.println("Ведіть число:");
                            int objectChoice = scanner.nextInt();
                            Object object = new Object(objectChoice);
                            object.createObject(transportList, driverList, clientList, orderHistoryList,
                                    filePath, driverMileageMap, clientOrderCountMap);
                            break;

                        case 2:
                            // Вивід інформації про водіїв


                            System.out.println("Вивід інформації");
                            System.out.println("Натисність 1: щоб вивести інфу про водіїв");
                            System.out.println("Натисність 2: щоб вивести інфу про клієнтів");
                            System.out.println("Натисність 3: щоб вивести інфу про замовлення");
                            System.out.println("Натисність 4: щоб вивести посортовні авто за дистанцією");
                            System.out.println("Натисність 5: щоб вивести клієнтів за частотою");

                            while (true) {
                                System.out.println("Ведіть число:");
                                int switch_of = scanner.nextInt();
                                scanner.nextLine(); // Очистка буфера після nextInt
                                switch (switch_of) {
                                    case 1:
                                        for (int i = 0; i < driverList.size(); i++) {
                                            Driver driver = driverList.get(i);
                                            System.out.println("Водій #" + (i + 1));
                                            System.out.println(driver.toString());
                                        }
                                        break;
                                    case 2:
                                        for (int i = 0; i < clientList.size(); i++) {
                                            Client client = clientList.get(i);
                                            System.out.println("Кліент #" + (i + 1));
                                            System.out.println(client.getFullName());
                                        }
                                        break;

                                    case 3:
                                        for (int i = 0; i < orderHistoryList.size(); i++) {
                                            OrderHistory orderHistory = orderHistoryList.get(i);
                                            System.out.println("Замовлення #" + (i + 1));
                                            System.out.println(orderHistory.toString());
                                        }
                                        break;
                                    case 4:
                                        Collections.sort(transportList, new Comparator<Transport>() {
                                            @Override
                                            public int compare(Transport car1, Transport car2) {
                                                // Порівняння за пробігом у зворотньому порядку
                                                return Double.compare(car2.getMileage(), car1.getMileage());
                                            }
                                        });

                                        // Виведення відсортованого списку
                                        for (Transport car : transportList) {
                                            System.out.println("Пробіг: " + car.getMileage() + " км - Номер реєстрації: " + car.getTaxi_number());
                                        }

                                        break;
                                    case 5:
                                        for (OrderHistory orderHistory : orderHistoryList) {
                                            Client client = orderHistory.getClient();
                                            // Якщо клієнт вже є у відображенні, збільшуємо кількість замовлень на 1
                                            clientOrderCountMap.put(client, clientOrderCountMap.getOrDefault(client, 0) + 1);
                                        }
                                        for (Map.Entry<Client, Integer> entry : clientOrderCountMap.entrySet()) {
                                            Client client = entry.getKey();
                                            int orderCount = entry.getValue();
                                            System.out.println("Клієнт: " + client.getFullName() + ", Кількість замовлень: " + orderCount);
                                        }
                                        break;

                                    default:
                                        break;
                                }

                                System.out.println("Чи бажаєте ви вивести ще щось? Введіть 1 - так, 0 - ні");
                                int numberCount = scanner.nextInt();
                                scanner.nextLine(); // Очистка буфера після nextInt

                                if (numberCount == 0) {
                                    break; // Виходимо з циклу, якщо користувач ввів 0
                                }


                            }
                            scanner.close();
                            break;
                        case 3:
                            System.out.println("Виберіть обєкт" +
                                    "\n1.Транспорт" +
                                    "\n2.Водій" +
                                    "\n3.Клієнт" +
                                    "\n4.Замовлення");
                            System.out.println("Ведіть число:");
                            String objectChoiceString = scanner.nextLine();
                            DataManager dataManager = new DataManager();
                            dataManager.updateObject(objectChoiceString, transportList, driverList, clientList, orderHistoryList);

                            break;
                        case 4:
                            System.out.println("Виберіть обєкт" +
                                    "\n1.Транспорт" +
                                    "\n2.Водій" +
                                    "\n3.Клієнт" +
                                    "\n4.Замовлення");
                            System.out.println("Ведіть число:");
                            objectChoiceString = scanner.nextLine();
                            dataManager = new DataManager();
                            dataManager.deleteObject(objectChoiceString, transportList, driverList, clientList, orderHistoryList);

                            break;
                        default:

                            break;
                    }
                    System.out.println("Чи бажаєте ви зробити ще щось? Введіть 1 - так, 0 - ні");
                    int numberCount = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера після nextInt

                    if (numberCount == 0) {
                        break; // Виходимо з циклу, якщо користувач ввів 0
                    }
                }
                time = true;
            }
            else if (fileChoice == 2) {
                String fileName = "example.bin";
                TransportProcessor transportProcessor = new TransportProcessor();

                while (true) {
                    System.out.println("Наш функціонал для роботи з бінарним  файлом:");
                    System.out.println("Натисність 1: щоб ввести інфу про обєкти");
                    System.out.println("Натисність 2: щоб вивести інформацію");
                    System.out.println("Натисність 3: щоб вивести інформацію");
                    System.out.println("Натисність 0: щоб завершити програму");
                    System.out.println("Ведіть число:");
                    int Mainswitch_of = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера після nextInt
                    switch (Mainswitch_of) {
                        case 1:

                            transportProcessor.writeToBinaryFileFromConsole(fileName);

                            break;

                        case 2:
                            // Вивід інформації про водіїв
                            transportList = transportProcessor.readFromBinaryFileToList(fileName);
                            System.out.println("Вивід інформації");
                            for (Transport transport : transportList) {
                                System.out.println(transport.toString());
                                System.out.println();
                            }
                            break;

                        case 3:
                            try {
                                File file = new File(fileName);
                                if (file.exists()) {
                                    RandomAccessFile raf = new RandomAccessFile(file, "rw");
                                    raf.setLength(0); // Встановлюємо довжину файлу на 0, очищаємо вміст
                                    raf.close();
                                    System.out.println("Файл очищено успішно.");
                                } else {
                                    System.out.println("Файл не існує.");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;

                        default:

                            break;
                    }
                    System.out.println("Чи бажаєте ви зробити ще щось? Введіть 1 - так, 0 - ні");
                    int numberCount = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера після nextInt

                    if (numberCount == 0) {
                        break; // Виходимо з циклу, якщо користувач ввів 0
                    }
                }
                time = true;
            }
            else {
                System.out.println("Оберіть корекне число");
            }
            if (time){
                System.out.println("Чи бажаєте ви обрати інший тип файлу? Введіть 1 - так, 0 - ні");
                int numberCount = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера після nextInt

                if (numberCount == 0) {
                    break; // Виходимо з циклу, якщо користувач ввів 0
                }
            }
        }
    }
}
