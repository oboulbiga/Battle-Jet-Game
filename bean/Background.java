
package bean;

import java.awt.Image;

import java.awt.Toolkit;
import jetGame.StartingClass;


public class Background {

    private int bgX, bgY;
    private int speedY;
    private Image background;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    public Background(int x, int y) {
        this.bgX = x;
        this.bgY = y;
        this.speedY = -StartingClass.game.getLevel()-1;
        background = toolkit.getImage("src/res/warshipsBackground-Recover.jpg");
    }

    public void update() {
        bgY -= speedY;
        if (bgY >= 1750) {
            bgY = -(2 * 1450);
        }
    }

    public int getBgX() {
        return bgX;
    }

    public void setBgX(int bgX) {
        this.bgX = bgX;
    }

    public int getBgY() {
        return bgY;
    }

    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    public int getspeedY() {
        return speedY;
    }

    public void setspeedY(int speedY) {
        this.speedY = speedY;
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

}
