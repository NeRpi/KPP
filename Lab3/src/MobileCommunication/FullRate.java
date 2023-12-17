package MobileCommunication;

public class FullRate implements IRate {
    private final float price;
    private final int minutes;
    private final int internet;

    FullRate() {
        this.price = 200;
        this.minutes = 100;
        this.internet = 100;
    }

    @Override
    public String getName() {
        return "Полный тариф";
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
        return internet;
    }

    public String toString() {
        return "Название: Полный тариф" +
                "\n   Цена: " + this.price +
                "\n   Количество минут: " + this.minutes +
                "\n   Количество мегабайт: " + this.internet;
    }
}
