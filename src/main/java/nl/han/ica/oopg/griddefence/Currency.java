package nl.han.ica.oopg.griddefence;

public class Currency {

    private static int currency = 100;

    public static int getCurrency() {
        return currency;
    }

    public static void setCurrency(int updateCurrency) {
        currency = updateCurrency;
    }
}