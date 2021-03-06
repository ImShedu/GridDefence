package nl.han.ica.oopg.griddefence.Enemy;

import java.util.ArrayList;
import java.util.HashMap;

import nl.han.ica.oopg.griddefence.GridDefence;

/**
 * EnemySpawner spawns the object Enemy into the game.
 * <p>
 * This class is created by: Elias Eskes.
 */
public class EnemySpawner {
	private GridDefence world;
	private static ArrayList<Enemy> enemyList = new ArrayList<>();
	private int currentWave = 1;
	private int waves;
	private int spawnedEnemies = 0;

	/**
	 * 
	 * @param world GridDefence The world for the enemy to be in.
	 */
	public EnemySpawner(GridDefence world) {
		this.world = world;
	}

	/**
	 * 
	 * @param world GridDefence The world for the enemy to be in.
	 * @param waves int The number of waves.
	 */
	public EnemySpawner(GridDefence world, int waves) {
		this.world = world;
		this.waves = waves;
		runGame();
	}

	/**
	 * This will start running the wave system by calling two other methods.
	 */
	private void runGame() {
		announceWave();
		startWave();
	}

	/**
	 * Removes the defeated enemy from the enemy list.
	 * 
	 * @param temp Enemy The temporary enemy that will be removed.
	 */
	public static void handleEnemyDeath(Enemy temp) {
		enemyList.remove(temp);
	}

	/**
	 * Calls the waveDoneHandler() after spawning all the enemies in this wave.
	 */
	private void spawnerDoneHandler() {
		spawnedEnemies = 0;
		waveDoneHandler(); // Start running the waveDoneHandler
	}

	/**
	 * Gets the current wave number.
	 * 
	 * @return The current wave number.
	 */
	public int getCurrentWave() {
		return currentWave;
	}

	/**
	 * Gets the list of enemies.
	 * 
	 * @return The list of enemies.
	 */
	public static ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	/**
	 * This handles the waves, checks every second if there are still enemies. If
	 * there are none, the next wave is started.
	 */
	private void waveDoneHandler() { // Would be nice to do this event based
		if (enemyList.size() != 0) {
			// System.out.println("The wave is not done yet, waiting another second");
			setTimeout(() -> waveDoneHandler(), 1000);
		} else {
			if (currentWave <= waves) {
				currentWave++;
				startWave(); // Start the next wave
			} else {
				wavesDone();
			}
		}
	}

	/**
	 * Returns the number of a certain enemy type to spawn for a certain wave
	 * 
	 * @param wave int The wave for which to get the amounts
	 * @return HashMap<String, Integer> dratini, dragonair, dragonite
	 */
	private HashMap<String, Integer> getEnemies(int wave) {
		HashMap<String, Integer> output = new HashMap<String, Integer>();
		output.put("dratini", (int) (wave * 1.5 + Math.random()));
		output.put("dragonair", (int) ((wave - 3) * 0.5 + Math.random() * 0.02 * wave));
		output.put("dragonite", (int) ((wave - 8) * 0.4 + Math.random() * 0.01 * wave));
		output.put("megapinsir", (int) ((wave - 10) * 0.2 + Math.random() * 0.01 * wave));
		return output;
	}

	/**
	 * Gets the speed, hp and damange for enemies in a wave.
	 * 
	 * @param type string, Type of Enemy you want to get the properties for
	 * @return HashMap with speed, hp and damage integers
	 */
	public HashMap<String, Integer> getEnemyProperties(String type) {
		HashMap<String, Integer> output = new HashMap<String, Integer>();
		switch (type) {
			case "dratini":
				output.put("speed", (int) ((1 + 0.8 * currentWave) * 10));
				output.put("hp", (int) (10 + 0.5 * currentWave));
				output.put("damage", (int) (5 + 0.1 * currentWave));
				output.put("currency", (int) 2);
				break;
			case "dragonair":
				output.put("speed", (int) ((1.3 + 0.2 * currentWave) * 10));
				output.put("hp", (int) (25 + 0.5 * currentWave));
				output.put("damage", (int) (20 + 0.1 * currentWave));
				output.put("currency", (int) 4);
				break;
			case "dragonite":
				output.put("speed", (int) ((1.1 + 0.2 * currentWave) * 10));
				output.put("hp", (int) (100 + 0.5 * currentWave));
				output.put("damage", (int) (33 + 0.1 * currentWave));
				output.put("currency", (int) 10);
				break;
			case "megapinsir":
				output.put("speed", (int) ((0.8 + 0.2 * currentWave) * 10));
				output.put("hp", (int) (500 + 0.5 * currentWave));
				output.put("damage", (int) (50 + 0.1 * currentWave));
				output.put("currency", (int) 20);
				break;
		}
		return output;
	}

	/**
	 * This handles the waves, checks every second if there are still enemies. If
	 * there are none, the next wave is started. Starts a wave uses the currentWave
	 * integer to get which enemies should be created
	 */
	private void startWave() {
		announceWave();
		HashMap<String, Integer> enemies = getEnemies(currentWave);

		System.out.println("Wave " + currentWave + " is starting with:");
		for (String key : enemies.keySet()) {
			int value = enemies.get(key);
			System.out.println(key + ": " + value);

			HashMap<String, Integer> properties = getEnemyProperties(key);
			System.out.println("Stats this wave:" + properties);
			spawnEnemies(value, key, 1000, 500);
		}
	}

	/**
	 * Makes a println when all the waves have been defeated.
	 */
	private void wavesDone() {
		System.out.println("All (" + waves + ") waves have been completed.");
	}

	/**
	 * Makes a println stating the current wave.
	 */
	private void announceWave() {
		System.out.println("Staring wave " + currentWave + "!");
	}

	/**
	 * Function which handles the spawning of multiple enemies at once. Calls
	 * spawnerDoneHandler once done.
	 * 
	 * @param amount   int the Amount of enemies to be spawned.
	 * @param interval int The time (in milliseconds))to wait between the enemies.
	 * @param random   int The amount of milliseconds to randomise the spawner by.
	 */
	private void spawnEnemies(int amount, String type, int interval, int random) {
		if (spawnedEnemies < amount) {
			spawnEnemy(1, 1, type);
			setTimeout(() -> spawnEnemies(amount, type, interval, random), (int) (interval + random * Math.random()));
		} else {
			spawnerDoneHandler();
		}
	}

	/**
	 * Asynchronous timeout function, waits for for the given delay and executes the
	 * runnable supplied.
	 * 
	 * @param runnable The code to be run when the delay has expired
	 * @param delay    Time to wait in milliseconds
	 */

	private void setTimeout(Runnable runnable, int delay) {
		new Thread(() -> {
			try {
				Thread.sleep(delay);
				runnable.run();
			} catch (Exception e) {
				System.err.println(e);
			}
		}).start();
	}

	/**
	 * Spawns a new enemy into the world.
	 * 
	 * @param x    int The X coordinate for the new enemy
	 * @param y    int The Y coordinate for the new enemy
	 * @param type String the type of enemy to create [dratini, dragonair,
	 *             dragonite]
	 */
	private void spawnEnemy(int x, int y, String type) {
		spawnedEnemies++;
		Enemy newEnemy = new Enemy(world, x, y, world.getTileSize(), type, getEnemyProperties(type));
		world.addGameObject(newEnemy, 40, 40);
		enemyList.add(newEnemy);
	}
}