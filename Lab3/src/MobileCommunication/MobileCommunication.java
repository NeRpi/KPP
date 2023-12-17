package MobileCommunication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MobileCommunication {
    private final ArrayList<IRate> rates;
    private int countClients;

    public MobileCommunication() {
        this.rates = new ArrayList<>(Arrays.asList(new FullRate(), new UnlimitedRate(), new BasicRate()));
        this.countClients = 1000;
    }

    public void getAllRates() {
        System.out.println("\nТарифы компании: ");
        for (int i = 0; i < rates.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, rates.get(i).getName());
        }
    }

    public void getRatesByPrice() {
        ArrayList<IRate> sortingRates = new ArrayList<>(rates);
        sortingRates.sort(new IRate.PriceComparator());
        System.out.println("\nТарифы компании по цене: ");
        for (int i = 0; i < sortingRates.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, sortingRates.get(i));
        }
    }

    public void getByParams(float minPrice, float maxPrice, int minMinutes, int maxMinutes, int minInternet, int maxInternet) {
        List<IRate> filterRates = this.rates.stream().filter(rate -> rate.getPrice() >= minPrice && rate.getPrice() <= maxPrice && rate.getMinutes() >= minMinutes && rate.getMinutes() <= maxMinutes && rate.getInternet() >= minInternet && rate.getInternet() <= maxInternet).collect(Collectors.toList());
        if (filterRates.isEmpty()) {
            System.out.println("\nНе удалось найти подходящий тариф");
        } else {
            System.out.println("\nПодходящте тарифы: ");
            for (int i = 0; i < filterRates.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, filterRates.get(i));
            }
        }
    }

    public int getCountClients() {
        return countClients;
    }

    public void run() {
        this.getAllRates();
        this.getRatesByPrice();
        this.getByParams(150, 200, 0, 100, 100, 100);
        System.out.println("\nКоличество клиентов: " + this.getCountClients());
    }
}
