package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PApplet;
import processing.core.PGraphics;

public class ClickableObject extends GameObject {

    private int towerNumber;

    public ClickableObject(float x, float y, float width, float height, int towerNumber) {
        super(x, y, width, height);
        this.towerNumber = towerNumber;
    }

    public boolean mouseClicked(int mouseX, int mouseY) {
		if (mouseX >= x && mouseX < x + width &&
				mouseY >= y && mouseY < y + height) {
			return true;
		}
		else {
			return false;
		}
    }
    
    public void printLine() {
        System.out.println(towerNumber);
    }
    
    // public boolean isMouseClicked() {
    //     System.out.println("test");
    // }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(PGraphics g) {
        // TODO Auto-generated method stub]
        g.fill(0, 0, 250);
        g.rect(x, y, width, height);
        

    }

}