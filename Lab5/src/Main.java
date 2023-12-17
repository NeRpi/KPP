import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Car> cars = new ArrayList<>();
    static List<Client> clients = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();
    private static final String ZIP_FILE_NAME = "./src/car_data.zip";
    private static final String CARS_FILE_NAME = "cars.txt";
    private static final String CLIENTS_FILE_NAME = "clients.txt";
    private static final String ORDERS_FILE_NAME = "orders.txt";
    private static Object order;


    public static void main(String[] args) {

        cars = loadCarsFromZip();
        clients = loadClientsFromZip();
        orders = loadOrdersFromZip();

        while (true) {
            System.out.println("Выберите роль: \n 1 - Клиент\n 2 - Администратор\n 3 - Выход\n");
            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (roleChoice) {
                case 1 -> clientMenu();
                case 2 -> adminMenu();
                case 3 -> {
                    System.out.println("Выход из программы.");
                    System.exit(0);
                }
                default -> System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }

    private static void clientMenu() {
        while (true) {
            System.out.println("Меню клиента:\n 1 - Посмотреть свободные автомобили\n 2 - Заполнить форму заказа\n 3 - Выход");
            int clientChoice = scanner.nextInt();
            scanner.nextLine();

            switch (clientChoice) {
                case 1:
                    displayAvailableCars();
                    break;
                case 2:
                    fillOrderForm();
                    break;
                case 3:
                    return; // Возврат в предыдущее меню
                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }

    private static void displayAvailableCars() {
        System.out.println("Свободные автомобили:");
        cars.stream().filter(car -> car.isAvailable).forEach(System.out::println);
    }

    private static void fillOrderForm() {
        System.out.println("Введите ваше имя:");
        String name = scanner.nextLine();
        System.out.println("Введите ваши паспортные данные:");
        String passport = scanner.nextLine();
        System.out.println("Введите срок аренды (в днях):");
        int rentalPeriod = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        System.out.println("Введите номер ID автомобиля:");
        String carId = scanner.nextLine();

        // Проверка id машины

        if (cars.stream().noneMatch(car -> Objects.equals(car.id, carId))) {
            System.out.println("Не верный id машины");
        } else {
            // Создаем новый заказ и добавляем его в список заказов
            Order order = new Order(name, passport, rentalPeriod, carId);
            orders.add(order);
            System.out.println("Форма заказа успешно заполнена.");
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("Меню администратора:\n " +
                    "1 - Данные о клиентах\n 2 - Данные обо всех автомобилях\n 3 - Данные о заявках\n " +
                    "4 - Добавление новых данных об автомобилях\n 5 - Сохранить данны\n 6 - Сортировка клиентов по потраченной сумме\n " +
                    "7 - Сортировка списка авто по статусу\n 8 - Сортировка списка заявок\n 9 - Отчет в формате csv об автомобилях\n 10 - Выход");

            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (adminChoice) {
                case 1:
                    displayClientData();
                    break;
                case 2:
                    displayCarData();
                    break;
                case 3:
                    displayOrderData();
                    break;
                case 4:
                    addCar();
                    break;
                case 5:
                    saveDataZip();
                    break;
                case 6:
                    sortClientsByTotalSpent();
                    break;
                case 7:
                    sortCarsByAvailability();
                    break;
                case 8:
                    sortOrders();
                    break;
                case 9:
                    generateCsvReport();
                    break;
                case 10:
                    return; // Возврат в предыдущее меню
                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }

    private static void displayClientData() {
        System.out.println("Данные о клиентах:");
        for (Client client : clients) {
            System.out.println("Имя: " + client.name + ", Паспортные данные: " + client.passport +
                    ", Срок аренды: " + client.rentalPeriod + " дней, Стоимость: " + calculateTotalCost(client));
        }
    }

    private static void displayCarData() {
        System.out.println("Данные обо всех автомобилях:");
        for (Car car : cars) {
            System.out.println("ID: " + car.id + ", Марка: " + car.brand + ", Стоимость: " + car.price +
                    ", Статус: " + (car.isAvailable ? "Свободен" : "Занят"));
        }
    }

    private static void displayOrderData() {
        System.out.println("Данные о заявках:");
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println("Заявка " + (i + 1) + ":");
            System.out.println("Имя: " + order.name + ", Паспортные данные: " + order.passport +
                    ", Срок аренды: " + order.rentalPeriod + " дней, ID автомобиля: " + order.carId);
        }
        System.out.println("Меню: 1 - Принять, 2 - Отклонить, 3 - Выход");
        int ordersChoice = scanner.nextInt();
        scanner.nextLine();

        switch (ordersChoice) {
            case 1:
                acceptOrder();
                break;
            case 2:
                rejectOrder();
                break;
            case 3:
                return; // Возврат в предыдущее меню
            default:
                System.out.println("Некорректный выбор. Заявка не обработана.");
        }
    }

    private static void rejectOrder() {
        System.out.println("Введите номер заявки для отклонения:");
        int orderNumber = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (orderNumber >= 1 && orderNumber <= orders.size()) {
            Order selectedOrder = orders.get(orderNumber - 1);

            System.out.println("Введите причину отклонения:");
            String rejectionReason = scanner.nextLine();

            // Логика отклонения заявки
            // Здесь вы должны добавить логику, которая удалит заявку из списка orders
            System.out.println("Заявка отклонена. Причина: " + rejectionReason);

            // Удаление заявки из списка orders
            orders.remove(selectedOrder);
        } else {
            System.out.println("Некорректный номер заявки.");
        }
    }

    private static void acceptOrder() {
        System.out.println("Введите номер заявки для принятия:");
        int orderNumber = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (orderNumber >= 1 && orderNumber <= orders.size()) {
            Order selectedOrder = orders.get(orderNumber - 1);
            Client client = new Client(selectedOrder.name, selectedOrder.passport, selectedOrder.rentalPeriod, selectedOrder.carId);
            clients.add(client);

            Optional<Car> findCar = cars.stream().filter(car -> Objects.equals(car.id, selectedOrder.carId)).findFirst();
            findCar.ifPresent(car -> car.isAvailable = false);

            System.out.println("Заявка принята. Добавлено в клиенты.");

            // Удаление заявки из списка orders
            orders.remove(selectedOrder);
        } else {
            System.out.println("Некорректный номер заявки.");
        }
    }

    private static void addCar() {
        System.out.println("Введите марку автомобиля:");
        String brand = scanner.nextLine();
        System.out.println("Введите стоимость аренды в день:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        String id = UUID.randomUUID().toString(); // Генерация уникального ID для автомобиля
        boolean isAvailable = true; // При добавлении автомобиля он всегда считается свободным

        // Создаем новый автомобиль и добавляем его в список автомобилей
        Car car = new Car(id, brand, price, isAvailable);
        cars.add(car);
        System.out.println("Новый автомобиль успешно добавлен.");
    }

    private static void sortClientsByTotalSpent() {
        clients.sort(Comparator.comparingDouble(Main::calculateTotalCost));
        System.out.println("Клиенты отсортированы по потраченной сумме.");
    }

    private static void sortCarsByAvailability() {
        cars.sort(Comparator.comparing(car -> car.isAvailable ? 0 : 1));
        System.out.println("Список автомобилей отсортирован по статусу (свободен/занят).");
    }

    private static void sortOrders() {
        orders.sort(Comparator.comparing(Order::getRentalPeriod).thenComparing(Order::getName));
        System.out.println("Список заявок отсортирован по дате начала аренды и имени клиента.");
    }

    private static void generateCsvReport() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("car_report.csv"))) {
            writer.println("ID,Марка,Стоимость,Статус,Клиент,Паспорт,Дата возврата");

            for (Car car : cars) {
                if (!car.isAvailable) {
                    Optional<Client> findClient = clients.stream().filter(client -> Objects.equals(client.carId, car.id)).findFirst();
                    if (findClient.isPresent()) {
                        Client client = findClient.get();
                        writer.println(car.id + "," + car.brand + "," + car.price + "," + "Занят" + "," + client.name + ',' + client.passport + "," + client.dateOfEnd);
                    }
                }
            }

            System.out.println("Отчет в формате CSV успешно создан (car_report.csv).");
        } catch (IOException e) {
            System.out.println("Ошибка при создании отчета в формате CSV.");
            e.printStackTrace();
        }
    }

    private static double calculateTotalCost(Client client) {
        // Простой метод для расчета общей суммы потраченных денег клиентом
        return client.rentalPeriod * cars.stream()
                .filter(car -> car.id.equals(client.carId))
                .findFirst()
                .map(car -> car.price)
                .orElse(0.0);
    }


    private static List<Car> loadCarsFromZip() {
        List<Car> loadedCars = new ArrayList<>();

        try (ZipFile zipFile = new ZipFile(ZIP_FILE_NAME)) {
            ZipEntry carsEntry = zipFile.getEntry(CARS_FILE_NAME);

            if (carsEntry != null) {
                try (InputStream inputStream = zipFile.getInputStream(carsEntry);
                     InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                     BufferedReader reader = new BufferedReader(inputStreamReader)) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] carData = line.split(",");
                        if (carData.length == 4) {
                            String id = carData[0].trim();
                            String brand = carData[1].trim();
                            double price = Double.parseDouble(carData[2].trim());
                            boolean isAvailable = Boolean.parseBoolean(carData[3].trim());

                            loadedCars.add(new Car(id, brand, price, isAvailable));
                        }
                    }
                }
            } else {
                System.out.println("Файл cars.txt не найден в архиве.");
            }

        } catch (IOException e) {
            System.out.println("Ошибка при загрузке данных о машинах из zip-архива.");
            e.printStackTrace();
        }

        return loadedCars;
    }

    private static List<Client> loadClientsFromZip() {
        List<Client> loadedClients = new ArrayList<>();

        try (ZipFile zipFile = new ZipFile(ZIP_FILE_NAME)) {
            ZipEntry clientsEntry = zipFile.getEntry(CLIENTS_FILE_NAME);

            if (clientsEntry != null) {
                try (InputStream inputStream = zipFile.getInputStream(clientsEntry);
                     InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                     BufferedReader reader = new BufferedReader(inputStreamReader)) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] clientData = line.split(",");
                        if (clientData.length == 5) {
                            String name = clientData[0].trim();
                            String passport = clientData[1].trim();
                            int rentalPeriod = Integer.parseInt(clientData[2].trim());
                            String carId = clientData[3].trim();
                            if (new Date(Long.parseLong(clientData[4])).after(new Date())) {
                                long dateOfEnd = Long.parseLong(clientData[4]);
                                loadedClients.add(new Client(name, passport, rentalPeriod, carId, dateOfEnd));
                            } else {
                                Optional<Car> findCar = cars.stream().filter(car -> Objects.equals(car.id, carId)).findFirst();
                                findCar.ifPresent(car -> car.isAvailable = true);
                            }

                        }
                    }
                }
            } else {
                System.out.println("Файл clients.txt не найден в архиве.");
            }

        } catch (IOException e) {
            System.out.println("Ошибка при загрузке данных о клиентах из zip-архива.");
            e.printStackTrace();
        }

        return loadedClients;
    }

    private static List<Order> loadOrdersFromZip() {
        List<Order> loadedOrders = new ArrayList<>();

        try (ZipFile zipFile = new ZipFile(ZIP_FILE_NAME)) {
            ZipEntry ordersEntry = zipFile.getEntry(ORDERS_FILE_NAME);

            if (ordersEntry != null) {
                try (InputStream inputStream = zipFile.getInputStream(ordersEntry);
                     InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                     BufferedReader reader = new BufferedReader(inputStreamReader)) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] orderData = line.split(",");
                        if (orderData.length == 4) {
                            String name = orderData[0].trim();
                            String passport = orderData[1].trim();
                            int rentalPeriod = Integer.parseInt(orderData[2].trim());
                            String carId = orderData[3].trim();

                            loadedOrders.add(new Order(name, passport, rentalPeriod, carId));
                        }
                    }
                }
            } else {
                System.out.println("Файл orders.txt не найден в архиве.");
            }

        } catch (IOException e) {
            System.out.println("Ошибка при загрузке данных о заявках из zip-архива.");
            e.printStackTrace();
        }

        return loadedOrders;
    }

    private static void saveDataZip() {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(ZIP_FILE_NAME))) {

            saveToZip(zipOutputStream, CARS_FILE_NAME, cars);
            System.out.println("Данные о машинах успешно сохранены в zip-архив.");

            saveToZip(zipOutputStream, CLIENTS_FILE_NAME, clients);
            System.out.println("Данные о клиентах успешно сохранены в zip-архив.");

            saveToZip(zipOutputStream, ORDERS_FILE_NAME, orders);
            System.out.println("Данные о заявках успешно сохранены в zip-архив.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных в zip-архив.");
            e.printStackTrace();
        }
    }

    private static <T> void saveToZip(ZipOutputStream zipOutputStream, String fileName, List<T> dataList) throws IOException {
        ZipEntry entry = new ZipEntry(fileName);
        zipOutputStream.putNextEntry(entry);
        for (T data : dataList) {
            String dataLine = getDataLine(data);
            zipOutputStream.write(dataLine.getBytes());
        }
        zipOutputStream.closeEntry();
    }

    private static String getDataLine(Object data) {
        if (data instanceof Car car) {
            return String.format(Locale.US, "%s,%s,%.2f,%b%n", car.id, car.brand, car.price, car.isAvailable);
        } else if (data instanceof Client client) {
            return String.format("%s,%s,%s,%s,%s%n", client.name, client.passport, client.rentalPeriod, client.carId, client.dateOfEnd.getTime());
        } else if (data instanceof Order order) {
            return String.format("%s,%s,%s,%s%n", order.name, order.passport, order.rentalPeriod, order.carId);
        }
        return "";
    }
}
