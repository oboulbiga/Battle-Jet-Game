
package bean;

import java.awt.Image;

import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;
import jetGame.StartingClass;


public class Enemy {

    private int speedY, speedX, centerX, centerY;

    private Background bg = StartingClass.getBg1();

    private List<Missile> Missiles = new ArrayList<>();

    private boolean crash = false;

    private Image image;

    private Level level;

    private Rectangle r = new Rectangle();

    public void update() {
        if (crash == false) {
            centerY -= speedY;
            speedY = -StartingClass.game.getLevel();
            if (speedX > 0) {
                if (this.getCenterX() < 1270) {
                    this.setCenterX(this.getCenterX() + speedX);
                }
            } else if (speedX < 0) {
                if (this.getCenterX() > 20) {
                    this.setCenterX(this.getCenterX() + speedX);
                }
            }
            if (StartingClass.game.getLevel() == 3 || StartingClass.game.getLevel() == 4) {
                follow();
            }
            r.setBounds(centerX, centerY, 69, 60);

        }
    }

    public void follow() {
        if (centerY < StartingClass.Jet.getCenterY()) {
            if (centerX < StartingClass.Jet.getCenterX()) {
                speedX = 3;
            } else if (centerX > StartingClass.Jet.getCenterX()) {
                speedX = -3;
            } else if (centerX == StartingClass.Jet.getCenterX()) {
                speedX = 0;
            }
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public Background getBg() {
        return bg;
    }

    public void setBg(Background  bg) {
        this.bg = bg;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean destroyTis() {
        return true;
    }

    public boolean isCrash() {
        return crash;
    }

    public void setCrash(boolean crash) {
        this.crash = crash;
    }

    public void shootMal() {
        Missile p = new Missile(this.getCenterX() + 25, this.getCenterY() + 25, true);

        Missiles.add(p);

    }

    public boolean checkCollision(Rectangle rect) {
        if (rect.intersects(r)) {
            return true;
        }
        return false;
    }

    public void collisionWithJet(Jet a) {
        if (checkCollision(StartingClass.Jet.getR()) == true) {
            System.out.println("VIE --");
            StartingClass.Jet.life = StartingClass.Jet.life - 1;
        }
    }



    public int getVitesseX() {
        return speedX;
    }

    public void setVitesseX(int vitesseX) {
        this.speedX = vitesseX;
    }

    public List<Missile> getMissiles() {
        return Missiles;
    }

    public void setMissiles(List<Missile> Missiles) {
        this.Missiles = Missiles;
    }

    public Rectangle getR() {
        return r;
    }

    public void setR(Rectangle r) {
        this.r = r;
    }

}
