package MobileCommunication;

public class UnlimitedRate implements IRate {
    private final float price;
    private final int internet;

    UnlimitedRate() {
        this.price = 150;
        this.internet = 100;
    }

    @Override
    public String getName() {
        return "Безлимитный тариф";
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public int getMinutes() {
        return 0;
    }

    @Override
    public int getInternet() {
        return internet;
    }

    @Override
    public String toString() {
        return "Название: Безлимитный тариф" +
                "\n   Цена: " + this.price +
                "\n   Количество мегабайт: " + this.internet;
    }
}
