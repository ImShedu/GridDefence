package nl.han.ica.oopg.griddefence.Enemy;

import java.util.ArrayList;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.griddefence.GridDefence;

public class EnemySpawner implements IAlarmListener {

    private float enemiesPerSecond;
    private GridDefence world;
    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private int x, y;

    public EnemySpawner(GridDefence world, float enemiesPerSecond) {
        this.world = world;
        this.enemiesPerSecond = enemiesPerSecond;
        // waveSystem();
        startAlarm();
    }

    // private void waveSystem() {
    //     // wave 1 clals 10 times
    //     // wave 2 calls 16 times

    //     for (int i = 0; i < 10; i++) {
    //         startAlarm();
    //         System.out.println("test"+i);
    //     }
    //     testSpawn.stop();
    // }

    private void startAlarm() {
        Alarm testSpawn = new Alarm("spawner", 1 / enemiesPerSecond);
        testSpawn.addTarget(this);
        testSpawn.start();
    }

    public ArrayList getEnemyList() {
        return enemyList;
    }

    @Override
    public void triggerAlarm(String alarmName) {
        // TODO Auto-generated method stub
        Enemy testEnemy = new Enemy(x, y, 40, 2, 2, 1, world);
        world.addGameObject(testEnemy, 40, 40);
        testEnemy.setDirectionSpeed(90, 5);
        enemyList.add(testEnemy);
        startAlarm();
    }

    public void spawnEnemy(int x, int y) {
        Enemy testEnemy1 = new Enemy(x, y, 40, 2, 2, 1, world);
        world.addGameObject(testEnemy1, 40, 40);
        testEnemy1.setDirectionSpeed(90, 1);
        enemyList.add(testEnemy1);
    }

}