package nl.han.ica.oopg.griddefence;

/**
 * Currency is the value the player gains by defeating each enemy. There's only
 * 1 instance of currency in the game.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class Currency {

    private static int currency = 100;

    /**
     * Gets the current amount of currency.
     * 
     * @return The amount of currency.
     */
    public static int getCurrency() {
        return currency;
    }

    /**
     * Adds the given number on top of the current amount of currency.
     */
    public static void addCurrency(int plusCurrency) {
        currency += plusCurrency;
    }

    /**
     * Decreases the current amount of currency with the given number.
     */
    public static void decreaseCurrency(int minusCurrency) {
        currency -= minusCurrency;
    }
}