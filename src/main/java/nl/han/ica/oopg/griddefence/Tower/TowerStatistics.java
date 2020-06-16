package nl.han.ica.oopg.griddefence.Tower;

import java.util.HashMap;

/**
 * TowerStatistics is a class that contains all the information about the
 * towers. This includes the tower names and the tower statistics
 * <p>
 * This class is created by: Wyman Chau.
 */
public class TowerStatistics {
    // { TowerNumber / UpgradeNumber / Cost / Refund / Range / Damage / Rate } >>
    // Name???
    public static float[][] towerStats1 = new float[][] { { 1f, 1f, 20f, 8f, 3f, 15f, 1.0f },
            { 1f, 2f, 20f, 16f, 5f, 15f, 1.5f }, { 1f, 3f, 50f, 36f, 5f, 25f, 2.0f },
            { 1f, 4f, 185f, 110f, 6f, 35f, 5.0f }, { 2f, 1f, 50f, 20f, 10f, 5f, 0.5f }, //35
            { 2f, 2f, 30f, 32f, 12f, 35f, 0.5f }, { 2f, 3f, 55f, 54f, 15f, 35f, 1.0f },
            { 2f, 4f, 210f, 138f, 20f, 50f, 1.5f }, { 3f, 1f, 75f, 30f, 6f, 85f, 0.3f },
            { 3f, 2f, 100f, 70f, 10f, 85f, 0.3f }, { 3f, 3f, 120f, 118f, 10f, 85f, 0.9f },
            { 3f, 4f, 250f, 218f, 12f, 100f, 1.0f }, { 4f, 1f, 30f, 12f, 3f, 10f, 1.0f },
            { 4f, 2f, 45f, 30f, 3f, 15f, 1.0f }, { 4f, 3f, 100f, 70f, 4f, 20f, 1.5f },
            { 4f, 4f, 200f, 150f, 5f, 25f, 1.5f }, { 5f, 1f, 50f, 20f, 5f, 25f, 2.0f },
            { 5f, 2f, 80f, 52f, 6f, 25f, 2.0f }, { 5f, 3f, 180f, 124f, 8f, 30f, 2.0f },
            { 5f, 4f, 300f, 244f, 10f, 45f, 2.0f }, { 6f, 1f, 400f, 160f, 10f, 80f, 3.0f },
            { 6f, 2f, 500f, 360f, 12f, 90f, 3.0f }, { 6f, 3f, 800f, 680f, 12f, 150f, 3.0f },
            { 6f, 4f, 1000f, 1080f, 14f, 200f, 4.0f } };

    public static String[][] towerName1 = new String[][] { { "1", "Bulbasaur", "Ivysaur", "Venusaur", "Mega Venusaur" },
            { "2", "Squirtle", "Wartortle", "Blastoise", "Mega Blastoise" },
            { "3", "Charmander", "Charmeleon", "Charizard", "Charizard X" },
            { "4", "Pidgey", "Pidgeotto", "Pidgeot", "Mega Pidgeot" },
            { "5", "Abra", "Kadabra", "Alakazam", "Mega Alakazam" },
            { "6", "Zapdos", "Moltres", "Articuno", "Mewtwo" } };

    /**
     * Gets the exact name of the given tower.
     * 
     * @param towerNumber   int towerNumber of the tower.
     * @param upgradeNumber int upgradeNumber of the tower.
     */
    public static String getTowerName(int towerNumber, int upgradeNumber) {
        String name = null;

        for (int i = 0; i < towerName1.length; i++) {
            if (towerName1[i][0].equals("" + towerNumber)) {
                name = towerName1[i][upgradeNumber];
            }
        }
        return name;
    }

    /**
     * Gets the exact statistic of the given tower.
     * 
     * @param statistic     String required statistic of the tower.
     * @param towerNumber   int towerNumber of the tower.
     * @param upgradeNumber int upgradeNumber of the tower.
     */
    public static float getTowerStats(String statistic, int towerNumber, int upgradeNumber) {
        float output = 0;

        for (int i = 0; i < towerStats1.length; i++) {
            // for (int j = 0; j < towerStats1[i].length; j++) {
            if (towerStats1[i][0] == towerNumber) {
                if (towerStats1[i][1] == upgradeNumber) {
                    // System.out.println("TN:"+towerNumber+" UN:"+upgradeNumber+" /
                    // "+towerStats1[i][2]);
                    switch (statistic) {
                        case "cost":
                            output = towerStats1[i][2];
                            break;

                        case "refund":
                            output = towerStats1[i][3];
                            break;

                        case "range":
                            output = towerStats1[i][4];
                            break;

                        case "damage":
                            output = towerStats1[i][5];
                            break;

                        case "rate":
                            output = towerStats1[i][6];
                            break;
                    }
                }
            }
        }
        // }
        return output;
    }

    /**
     * Gets the correct method for the tower.
     * 
     * @param towerNumber   int towerNumber of the tower.
     * @param upgradeNumber int upgradeNumber of the tower.
     * @return HashMap with the tower statistics method of the tower.
     */
    public static HashMap<String, Float> getTowerStats1(int towerNumber, int upgradeNumber) {
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
    public static HashMap<String, String> getTowerName1(int towerNumber, int upgradeNumber) {
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