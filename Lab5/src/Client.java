import java.util.Calendar;
import java.util.Date;

class Client {
    String name;
    String passport;
    int rentalPeriod;
    String carId;
    Date dateOfEnd;

    public Client(String name, String passport, int rentalPeriod, String carId, Long dateOfEnd) {
        this.name = name;
        this.passport = passport;
        this.rentalPeriod = rentalPeriod;
        this.carId = carId;
        this.dateOfEnd = new Date(dateOfEnd);
    }

    public Client(String name, String passport, int rentalPeriod, String carId) {
        this.name = name;
        this.passport = passport;
        this.rentalPeriod = rentalPeriod;
        this.carId = carId;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, rentalPeriod);
        this.dateOfEnd = calendar.getTime();
    }
}