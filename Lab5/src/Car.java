class Car {
    String id;
    String brand;
    double price;
    boolean isAvailable;

    public Car(String id, String brand, double price, boolean isAvailable) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", Марка: " + this.brand + ", Стоимость: " + this.price;
    }
}
