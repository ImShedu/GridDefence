package nl.han.ica.oopg.griddefence;

/**
 * Currency is the value the player gains by defeating each enemy. There's only
 * 1 instance of currency in the game.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class Currency {

    private static int currency = 10000;

    public static int getCurrency() {
        return currency;
    }

    public static void addCurrency(int plusCurrency) {
        currency += plusCurrency;
    }

    public static void decreaseCurrency(int minusCurrency) {
        currency -= minusCurrency;
    }
}