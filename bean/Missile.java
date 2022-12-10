
package bean;

import java.awt.Image;

import java.awt.Rectangle;
import java.awt.Toolkit;
import jetGame.StartingClass;


public class Missile {

    private int x, y, vitesseY;
    private Image bullet;
    private Rectangle r = new Rectangle();
    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    public Missile(int x, int y, boolean isEnnemie) {
        this.x = x;
        this.y = y;

        if (isEnnemie) {
            vitesseY = (StartingClass.game.getLevel() + 7);
            bullet = toolkit.getImage("src/res/tiremal.png");
        } else {
            vitesseY = 9;
            bullet = toolkit.getImage("src/res/tire1.png");
        }

    }

    public void update() {
        
        if (y > 0) {
            y -= vitesseY;
            r.setBounds(this.x, this.y, 10, 20);
        } else {
            StartingClass.Jet.Missiles.remove(this);
        }

    }

    public Rectangle getR() {
        return r;
    }

    public void setR(Rectangle r) {
        this.r = r;
    }

    public void updateMissileEnnemi() {
        if (y < 700) {
            if (checkCollision(StartingClass.Jet.getR())) {
                if (StartingClass.Jet.getshield() == 0) {
                    this.setY(700);
                    StartingClass.Jet.destroy();
                } else {
                    this.setY(700);
                    StartingClass.Jet.setBouclier(StartingClass.Jet.getshield() - 1);
                }

            }
            y += vitesseY;
            r.setBounds(this.x, this.y, 10, 20);

        }
    }

    public void updateMissileBoss() {
        if (y < 700) {
            if (checkCollision(StartingClass.Jet.getR())) {
                if (StartingClass.Jet.getshield() == 0) {
                    this.setY(700);
                    StartingClass.Jet.destroy();
                } else {
                    this.setY(700);
                    StartingClass.Jet.setBouclier(StartingClass.Jet.getshield() - 1);

                }

            }
            y += vitesseY;
            r.setBounds(this.x, this.y, 10, 20);
        } else {
            EnemyLeader.Missiles.remove(this);
        }
    }

    public int getX() {
        return x;
    }

    public void removerThis() {
        for (int i = 0; i < Jet.Missiles.size(); i++) {
            if (Jet.Missiles.get(i) == this) {
                Jet.Missiles.remove(i);
            }
        }
    }

    public void removerThisBoss() {
        for (int i = 0; i < EnemyLeader.Missiles.size(); i++) {
            if (EnemyLeader.Missiles.get(i) == this) {
                EnemyLeader.Missiles.remove(i);
            }
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVitesseY() {
        return vitesseY;
    }

    public void setVitesseY(int vitesseY) {
        this.vitesseY = vitesseY;
    }

    public Image getBullet() {
        return bullet;
    }

    public void setBullet(Image bullet) {
        this.bullet = bullet;
    }

    public boolean checkCollision(Rectangle rect) {
        if (rect.intersects(r)) {
            return true;
        }
        return false;
    }

}
