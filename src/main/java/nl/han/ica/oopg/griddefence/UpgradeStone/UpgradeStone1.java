package nl.han.ica.oopg.griddefence.UpgradeStone;

import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Tower.Tower;

public class UpgradeStone1 extends UpgradeStone {


    public UpgradeStone1(GridDefence world, Tower tower) {
        super(world, tower);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void stoneMechanic() {
        tower.setTowerRate(7);
    }
}