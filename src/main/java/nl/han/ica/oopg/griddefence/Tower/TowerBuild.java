// package nl.han.ica.oopg.griddefence.Tower;

// import java.util.HashMap;

// import nl.han.ica.oopg.griddefence.GridDefence;
// import nl.han.ica.oopg.objects.Sprite;
// import processing.core.PGraphics;
// import processing.core.PImage;

// public class TowerBuild extends Tower {

//     private Sprite towerSprite;

//     public TowerBuild(float x, float y, float width, float height, GridDefence world, int towerNumber, String upgrade) {
//         super(x, y, width, height, world, towerNumber, upgrade);
//         towerProperties(towerNumber, upgrade);
//         towerSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower"+ towerNumber + upgrade + ".png"); // tower1upgrade0.png
//     }

//     // Basic tower information (Testing purpose)
//     public void towerProperties(int towerNumber, String upgrade) {
//         HashMap<String, Float> properties = getTowerOneProperties(upgrade);
//         if (upgrade == "upgrade0") {
//             System.out.println("Range: "+properties.get("range"));
//             System.out.println("Damage: "+properties.get("damage"));
//             System.out.println("Rate: "+properties.get("rate"));
//         }

//         if (upgrade == "upgrade1") {
//             System.out.println("Range: "+properties.get("range"));
//             System.out.println("Damage: "+properties.get("damage"));
//             System.out.println("Rate: "+properties.get("rate"));
//         }

//         if (upgrade == "upgrade2") {
//             System.out.println("Range: "+properties.get("range"));
//             System.out.println("Damage: "+properties.get("damage"));
//             System.out.println("Rate: "+properties.get("rate"));
//         }

//         if (upgrade == "upgrade3") {
//             System.out.println("Range: "+properties.get("range"));
//             System.out.println("Damage: "+properties.get("damage"));
//             System.out.println("Rate: "+properties.get("rate"));
//         }
//     }

//     // Draw towersprite
//     public void draw(PGraphics g) {
//         g.image(towerSprite.getPImage(), x, y);
//     }

// }