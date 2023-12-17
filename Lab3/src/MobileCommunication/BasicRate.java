package MobileCommunication;

public class BasicRate implements IRate {
    private final float price;
    private final int minutes;

    BasicRate() {
        this.price = 100;
        this.minutes = 100;
    }

    @Override
    public String getName() {
        return "Базовый тариф";
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getInternet() {
        return 0;
    }

    @Override
    public String toString() {
        return "Название: Базовый тариф" +
                "\n   Цена: " + this.price +
                "\n   Количество минут: " + this.minutes;
    }
}
