package MobileCommunication;

import java.util.Comparator;

public interface IRate {
    String getName();

    float getPrice();

    int getMinutes();

    int getInternet();

    String toString();

    public static class PriceComparator implements Comparator<IRate> {
        @Override
        public int compare(IRate o1, IRate o2) {
            return Float.compare(o1.getPrice(), o2.getPrice());
        }
    }
}
