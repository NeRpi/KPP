class Order {
    String name;
    String passport;
    int rentalPeriod;
    String carId;

    public Order(String name, String passport, int rentalPeriod, String carId) {
        this.name = name;
        this.passport = passport;
        this.rentalPeriod = rentalPeriod;
        this.carId = carId;
    }
    public int getRentalPeriod() {
        return rentalPeriod;
    }

    public String getName() {
        return name;
    }
}