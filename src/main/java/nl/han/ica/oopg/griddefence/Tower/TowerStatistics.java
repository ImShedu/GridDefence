package nl.han.ica.oopg.griddefence.Tower;

import java.util.HashMap;

/**
 * TowerStatistics is a class that contains all the information about the
 * towers. This includes the tower names and the tower statistics
 * <p>
 * This class is created by: Wyman Chau.
 */
public class TowerStatistics {

    /**
     * Gets the correct method for the tower.
     * 
     * @param towerNumber   int towerNumber of the tower.
     * @param upgradeNumber int upgradeNumber of the tower.
     * @return HashMap with the tower statistics method of the tower.
     */
    public static HashMap<String, Float> getTowerStats(int towerNumber, int upgradeNumber) {
        HashMap<String, Float> output = new HashMap<String, Float>();
        switch (towerNumber) {
            case 1:
                output = towerOneStats(upgradeNumber);
                break;
            case 2:
                output = towerTwoStats(upgradeNumber);
                break;
            case 3:
                output = towerThreeStats(upgradeNumber);
                break;
            case 4:
                output = towerFourStats(upgradeNumber);
                break;
            case 5:
                output = towerFiveStats(upgradeNumber);
                break;
            case 6:
                output = towerSixStats(upgradeNumber);
                break;
            default:
                System.out.println("Beep boop error stats");
                break;
        }
        return output;
    }

    /**
     * Gets the correct method for the tower.
     * 
     * @param towerNumber   int towerNumber of the tower.
     * @param upgradeNumber int upgradeNumber of the tower.
     * @return HashMap with the tower name method of the tower.
     */
    public static HashMap<String, String> getTowerName(int towerNumber, int upgradeNumber) {
        HashMap<String, String> output = new HashMap<String, String>();
        switch (towerNumber) {
            case 1:
                output = towerOneName(upgradeNumber);
                break;
            case 2:
                output = towerTwoName(upgradeNumber);
                break;
            case 3:
                output = towerThreeName(upgradeNumber);
                break;
            case 4:
                output = towerFourName(upgradeNumber);
                break;
            case 5:
                output = towerFiveName(upgradeNumber);
                break;
            case 6:
                output = towerSixName(upgradeNumber);
                break;
            default:
                System.out.println("Beep boop error name");
                break;
        }
        return output;
    }

    /**
     * Gets the exact name of the tower 1.
     * 
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with the name of the tower.
     */
    private static HashMap<String, String> towerOneName(int upgradeNumber) {
        HashMap<String, String> output = new HashMap<String, String>();

        switch (upgradeNumber) {
            case 1:
                output.put("name", "Bulbasaur");
                break;
            case 2:
                output.put("name", "Ivysaur");
                break;
            case 3:
                output.put("name", "Venusaur");
                break;
            case 4:
                output.put("name", "Mega Venusaur");
                break;
        }
        return output;
    }

    /**
     * Gets the exact name of the tower 2.
     * 
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with the name of the tower.
     */
    private static HashMap<String, String> towerTwoName(int upgradeNumber) {
        HashMap<String, String> output = new HashMap<String, String>();

        switch (upgradeNumber) {
            case 1:
                output.put("name", "Squirtle");
                break;
            case 2:
                output.put("name", "Wartortle");
                break;
            case 3:
                output.put("name", "Blastoise");
                break;
            case 4:
                output.put("name", "Mega Blastoise");
                break;
        }
        return output;
    }

    /**
     * Gets the exact name of the tower 3.
     * 
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with the name of the tower.
     */
    private static HashMap<String, String> towerThreeName(int upgradeNumber) {
        HashMap<String, String> output = new HashMap<String, String>();

        switch (upgradeNumber) {
            case 1:
                output.put("name", "Charmander");
                break;
            case 2:
                output.put("name", "Charmeleon");
                break;
            case 3:
                output.put("name", "Charizard");
                break;
            case 4:
                output.put("name", "Charizard X");
                break;
        }
        return output;
    }

    /**
     * Gets the exact name of the tower 4.
     * 
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with the name of the tower.
     */
    private static HashMap<String, String> towerFourName(int upgradeNumber) {
        HashMap<String, String> output = new HashMap<String, String>();

        switch (upgradeNumber) {
            case 1:
                output.put("name", "Pidgey");
                break;
            case 2:
                output.put("name", "Pidgeotto");
                break;
            case 3:
                output.put("name", "Pidgeot");
                break;
            case 4:
                output.put("name", "Mega Pidgeot");
                break;
        }
        return output;
    }

    /**
     * Gets the exact name of the tower 5.
     * 
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with the name of the tower.
     */
    private static HashMap<String, String> towerFiveName(int upgradeNumber) {
        HashMap<String, String> output = new HashMap<String, String>();

        switch (upgradeNumber) {
            case 1:
                output.put("name", "Abra");
                break;
            case 2:
                output.put("name", "Kadabra");
                break;
            case 3:
                output.put("name", "Alakazam");
                break;
            case 4:
                output.put("name", "Mega Alakazam");
                break;
        }
        return output;
    }

        /**
     * Gets the exact name of the tower 6.
     * 
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with the name of the tower.
     */
    private static HashMap<String, String> towerSixName(int upgradeNumber) {
        HashMap<String, String> output = new HashMap<String, String>();

        switch (upgradeNumber) {
            case 1:
                output.put("name", "Zapdos");
                break;
            case 2:
                output.put("name", "Moltres");
                break;
            case 3:
                output.put("name", "Articuno");
                break;
            case 4:
                output.put("name", "Mewtwo");
                break;
        }
        return output;
    }

    /**
     * Gets the upgrade, cost, refund, range, damage and rate of tower 1.
     * 
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with upgrade, cost, refund, range, damage and rate floats.
     */
    private static HashMap<String, Float> towerOneStats(int upgradeNumber) {
        HashMap<String, Float> output = new HashMap<String, Float>();

        switch (upgradeNumber) {
            case 1:
                output.put("upgrade", 1f);
                output.put("cost", 20f);
                output.put("refund", 8f);

                output.put("range", 3f);
                output.put("damage", 15f);
                output.put("rate", 1.0f);
                break;

            case 2:
                output.put("upgrade", 2f);
                output.put("cost", 20f);
                output.put("refund", 16f);

                output.put("range", 5f);
                output.put("damage", 15f);
                output.put("rate", 1.5f);
                break;

            case 3:
                output.put("upgrade", 3f);
                output.put("cost", 50f);
                output.put("refund", 36f);

                output.put("range", 5f);
                output.put("damage", 25f);
                output.put("rate", 2.0f);
                break;

            case 4:
                output.put("upgrade", 4f);
                output.put("cost", 185f);
                output.put("refund", 110f);

                output.put("range", 6f);
                output.put("damage", 35f);
                output.put("rate", 5.0f);
                break;
        }
        return output;
    }

    /**
     * Gets the upgrade, cost, refund, range, damage and rate of tower 2.
     * 
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with upgrade, cost, refund, range, damage and rate floats.
     */
    private static HashMap<String, Float> towerTwoStats(int upgradeNumber) {
        HashMap<String, Float> output = new HashMap<String, Float>();

        switch (upgradeNumber) {
            case 1:
                output.put("upgrade", 1f);
                output.put("cost", 50f);
                output.put("refund", 20f);

                output.put("range", 10f);
                output.put("damage", 35f);
                output.put("rate", 0.5f);
                break;

            case 2:
                output.put("upgrade", 2f);
                output.put("cost", 30f);
                output.put("refund", 32f);

                output.put("range", 12f);
                output.put("damage", 35f);
                output.put("rate", 0.5f);
                break;

            case 3:
                output.put("upgrade", 3f);
                output.put("cost", 55f);
                output.put("refund", 54f);

                output.put("range", 15f);
                output.put("damage", 35f);
                output.put("rate", 1.0f);
                break;

            case 4:
                output.put("upgrade", 4f);
                output.put("cost", 210f);
                output.put("refund", 138f);

                output.put("range", 20f);
                output.put("damage", 50f);
                output.put("rate", 1.5f);
                break;
        }
        return output;
    }

    /**
     * Gets the upgrade, cost, refund, range, damage and rate of tower 3.
     * 
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with upgrade, cost, refund, range, damage and rate floats.
     */
    private static HashMap<String, Float> towerThreeStats(int upgradeNumber) {
        HashMap<String, Float> output = new HashMap<String, Float>();

        switch (upgradeNumber) {
            case 1:
                output.put("upgrade", 1f);
                output.put("cost", 75f);
                output.put("refund", 30f);

                output.put("range", 6f);
                output.put("damage", 85f);
                output.put("rate", 0.3f);
                break;

            case 2:
                output.put("upgrade", 2f);
                output.put("cost", 100f);
                output.put("refund", 70f);

                output.put("range", 10f);
                output.put("damage", 85f);
                output.put("rate", 0.3f);
                break;

            case 3:
                output.put("upgrade", 3f);
                output.put("cost", 120f);
                output.put("refund", 118f);

                output.put("range", 10f);
                output.put("damage", 85f);
                output.put("rate", 0.9f);
                break;

            case 4:
                output.put("upgrade", 4f);
                output.put("cost", 250f);
                output.put("refund", 218f);

                output.put("range", 12f);
                output.put("damage", 100f);
                output.put("rate", 1.0f);
                break;
        }
        return output;
    }

    /**
     * Gets the upgrade, cost, refund, range, damage and rate of tower 4.
     * 
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with upgrade, cost, refund, range, damage and rate floats.
     */
    private static HashMap<String, Float> towerFourStats(int upgradeNumber) {
        HashMap<String, Float> output = new HashMap<String, Float>();

        switch (upgradeNumber) {
            case 1:
                output.put("upgrade", 1f);
                output.put("cost", 30f);
                output.put("refund", 12f);

                output.put("range", 3f);
                output.put("damage", 10f);
                output.put("rate", 1.0f);
                break;

            case 2:
                output.put("upgrade", 2f);
                output.put("cost", 45f);
                output.put("refund", 30f);

                output.put("range", 3f);
                output.put("damage", 15f);
                output.put("rate", 1.0f);
                break;

            case 3:
                output.put("upgrade", 3f);
                output.put("cost", 100f);
                output.put("refund", 70f);

                output.put("range", 4f);
                output.put("damage", 20f);
                output.put("rate", 1.5f);
                break;

            case 4:
                output.put("upgrade", 4f);
                output.put("cost", 200f);
                output.put("refund", 150f);

                output.put("range", 5f);
                output.put("damage", 25f);
                output.put("rate", 1.5f);
                break;
        }
        return output;
    }

    /**
     * Gets the upgrade, cost, refund, range, damage and rate of tower 5.
     * 
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with upgrade, cost, refund, range, damage and rate floats.
     */
    private static HashMap<String, Float> towerFiveStats(int upgradeNumber) {
        HashMap<String, Float> output = new HashMap<String, Float>();

        switch (upgradeNumber) {
            case 1:
                output.put("upgrade", 1f);
                output.put("cost", 50f);
                output.put("refund", 20f);

                output.put("range", 5f);
                output.put("damage", 25f);
                output.put("rate", 2.0f);
                break;

            case 2:
                output.put("upgrade", 2f);
                output.put("cost", 80f);
                output.put("refund", 52f);

                output.put("range", 6f);
                output.put("damage", 25f);
                output.put("rate", 2.0f);
                break;

            case 3:
                output.put("upgrade", 3f);
                output.put("cost", 180f);
                output.put("refund", 124f);

                output.put("range", 8f);
                output.put("damage", 30f);
                output.put("rate", 2.0f);
                break;

            case 4:
                output.put("upgrade", 4f);
                output.put("cost", 300f);
                output.put("refund", 244f);

                output.put("range", 10f);
                output.put("damage", 45f);
                output.put("rate", 2.0f);
                break;
        }
        return output;
    }

        /**
     * Gets the upgrade, cost, refund, range, damage and rate of tower 6.
     * 
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with upgrade, cost, refund, range, damage and rate floats.
     */
    private static HashMap<String, Float> towerSixStats(int upgradeNumber) {
        HashMap<String, Float> output = new HashMap<String, Float>();

        switch (upgradeNumber) {
            case 1:
                output.put("upgrade", 1f);
                output.put("cost", 400f);
                output.put("refund", 160f);

                output.put("range", 10f);
                output.put("damage", 80f);
                output.put("rate", 3.0f);
                break;

            case 2:
                output.put("upgrade", 2f);
                output.put("cost", 500f);
                output.put("refund", 360f);

                output.put("range", 12f);
                output.put("damage", 90f);
                output.put("rate", 3.0f);
                break;

            case 3:
                output.put("upgrade", 3f);
                output.put("cost", 800f);
                output.put("refund", 680f);

                output.put("range", 12f);
                output.put("damage", 150f);
                output.put("rate", 3.0f);
                break;

            case 4:
                output.put("upgrade", 4f);
                output.put("cost", 1000f);
                output.put("refund", 1080f);

                output.put("range", 14f);
                output.put("damage", 200f);
                output.put("rate", 4.0f);
                break;
        }
        return output;
    }
}