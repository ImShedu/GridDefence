package nl.han.ica.oopg.griddefence.Enemy;

import java.util.ArrayList;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.griddefence.GridDefence;

public class EnemySpawner {
    private float enemiesPerSecond;
    private GridDefence world;
    private static ArrayList<Enemy> enemyList = new ArrayList<>();
    private int x, y;
    private int currentWave = 1;
    private int waves;
    private int spawnedEnemies = 0;
       
    public EnemySpawner(GridDefence world, int waves) {
        this.world = world;
        this.enemiesPerSecond = 2;
        this.waves = waves;
        runGame();
    }
    
    public void runGame() {
		announceWave();
		startWave();
		//spawnEnemies(getEnemiesForWave("soldier"), 1000, 400);
    }
    
    public void spawnerDoneHandler() {
    	currentWave++;
    	spawnedEnemies = 0;
    	waveDoneHandler();
    }
    
    /**
     * Returns the number of a certain enemy type to spawn for a certain wave
     * @param String type The type of enemy you want to check
     * @return int the amount of enemies to be spawned
     */
    
    public int getEnemiesForWave(String type) {
    	switch(type) {
    	case "soldier":
    		return (int) (currentWave*1.5+Math.random());
		case "car":
    		return (int)((currentWave-3)*0.5+Math.random()*0.02*currentWave);
    	case "tank":
    		return (int)((currentWave)*0.4+Math.random()*0.01*currentWave);
    	}
		return 0; // fallback
    }
    
    /**
     * This handles the waves, checks every second if there are still enemies.I
     * If there are none, the next wave is started.
     */
    public void waveDoneHandler () { // Would be nice to do this event based
    	if(enemyList.size() != 0) {
    		System.out.println("The wave is not done yet, waiting another second");
    		setTimeout(() -> waveDoneHandler(), 1000);
    	} else {
    		if(currentWave <= waves) {
    			startWave();
    		} else {
    			wavesDone();
    		}
    	}
    }
    
    public void startWave() {
    	announceWave();
    	int soldiers = getEnemiesForWave("soldier");
    	int cars = getEnemiesForWave("car");
    	int tanks = getEnemiesForWave("tank");
    	
    	System.out.println(currentWave+"Is starting with:");
    	System.out.println("Soldiers: "+soldiers);
    	System.out.println("Cars:"+cars);
    	System.out.println("Tanks:"+tanks);
    	spawnEnemies(soldiers, 1000, 500);
    }
    
    public void wavesDone() {
    	System.out.println("All ("+waves+") waves have been completed.");
    }
    
    public static void handleEnemyDeath(Enemy temp) {
    	enemyList.remove(temp);
    	System.out.println(temp);
    }
    
    public void announceWave() {
    	//System.out.println("Staring wave "+currentWave+"!");
    }
    
    /**
     * Function which handles the spawning of multiple enemies at once.
     * Calls spawnerDoneHandler once done.
     * @param amount int the Amount of enemies to be spawned.
     * @param interval int The time (in milliseconds))to wait between the enemies.
     * @param random int The amount of milliseconds to randomise the spawner by.
     */
    public void spawnEnemies(int amount, int interval, int random) {
    	if(spawnedEnemies < amount) {
    		spawnEnemy(1,1);
    		System.out.println("spawning new enemy :)");
    		setTimeout(() -> spawnEnemies(amount, interval, random), (int) (interval+random*Math.random()));
    	} else {
    		spawnerDoneHandler();
    	}
    }
    
    /**
     * Asynchronous timeout function, waits for for the given delay and executes the runnable supplied.
     * @param runnable The code to be run when the delay has expired
     * @param delay Time to wait in milliseconds
     */
    
	public static void setTimeout(Runnable runnable, int delay){
		new Thread(() -> {
			try {
				Thread.sleep(delay);
				runnable.run();
			}
			catch (Exception e){
			System.err.println(e);
			}
		}).start();
	}

	/**
	 * Spawns a new enemy into the world.
	 * @param x	int The X coordinate for the new enemy
	 * @param y int The Y coordinate for the new enemy
	 */
    public void spawnEnemy(int x, int y) {
    	spawnedEnemies++;
        Enemy temp = new Enemy(x, y, 40, 2, 2, 1, world);
        world.addGameObject(temp, 40, 40);
        temp.setDirectionSpeed(90, 14);
        enemyList.add(temp);
    }

}