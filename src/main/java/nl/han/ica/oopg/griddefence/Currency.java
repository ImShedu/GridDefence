package nl.han.ica.oopg.griddefence;

public class Currency {

    private static int currency = 500;
    private GridDefence world;

    public Currency(GridDefence world) {
        this.world = world;
    }

    public static int getCurrency() {
        return currency;
    }

    public static void setCurrency(int updateCurrency) {
        currency = updateCurrency;
    }
}