
package bean;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jetGame.StartingClass;
import service.MediaPlayer;


public class EnemyLeader extends Enemy implements Runnable {

    private int maxHealth, currentHealth, power, vitesse;

    public static List<Missile> Missiles = new ArrayList<>();

    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    private Thread move;

    private Image healtBarEmpty;
    
    public EnemyLeader() {
        move = new Thread(this, "Boss Thread");
        maxHealth = 50;
        currentHealth = maxHealth;
        vitesse = -(StartingClass.game.getLevel());
        setCenterX(600);
        setCenterY(-250);
        getR().setBounds(this.getCenterX(), this.getCenterY(), 150, 80);
        setImage(toolkit.getImage("src/res/Boss B-3.mini.png"));
        healtBarEmpty = toolkit.getImage("src/res/BossHealthBarEmpty.png");
    }

    @Override
    public boolean checkCollision(Rectangle rect) {
        if (rect.intersects(this.getR())) {
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        if (vitesse > 0) {
            if (this.getCenterX() < 1200) {
                this.setCenterX(this.getCenterX() + vitesse);
            }

        } else if (vitesse < 0) {
            if (this.getCenterX() > 10) {
                this.setCenterX(this.getCenterX() + vitesse);
            }
        }
        this.getR().setBounds(this.getCenterX(), this.getCenterY(), 130, 240);
//        follow();
        for (int i = 0; i < StartingClass.Jet.Missiles.size(); i++) {
            if (checkCollision(StartingClass.Jet.Missiles.get(i).getR())) {
                StartingClass.Jet.Missiles.remove(i);
                if (currentHealth > 1) {
                    currentHealth--;

                }

            }

        }

    }

    @Override
    public void follow() {

        if (this.getCenterX() < (StartingClass.Jet.getCenterX() + 30)) {
            vitesse = (StartingClass.game.getLevel());
        }
        if (this.getCenterX() > (StartingClass.Jet.getCenterX() - 30)) {
            vitesse = -(StartingClass.game.getLevel());
        }
        if (this.getCenterX() == (StartingClass.Jet.getCenterX()- 30)) {
            vitesse = 0;
            this.setCenterX(StartingClass.Jet.getCenterX() - 30);
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public void run() {
        while (this.getCenterY() <= 5) {
            System.out.println("Boss moving down at " + getCenterY());
            setCenterY(getCenterY() + 7);
            vitesse = 0;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        vitesse = -(StartingClass.game.getLevel());
        while (currentHealth > 1) {
            System.out.println("Boss Health " + currentHealth);
            if (Missiles.size() <= 1) {
                this.shootMal();
            }

            if (this.getCenterX() >= 1200) {
                vitesse = -(StartingClass.game.getLevel());
            } else if (this.getCenterX() <= 10) {
                vitesse = (StartingClass.game.getLevel());
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        destroy();

    }

    public void shootMal() {
        removeBossProjectiles();
        Missile p = new Missile (this.getCenterX() + 60, this.getCenterY() + 200, true);
       Missiles.add(p);

    }

    public void moveRight() {
        vitesse = 2;

    }

    public void moveLeft() {
        vitesse = -2;
    }

    public void removeShoot() {
        for (int i = 0; i < Missiles.size(); i++) {
            if (Missiles.get(i).getY() <= 700) {
                Missiles.remove(i);
            }

        }

    }

    public void destroy() {
        synchronized (this) {

            setImage(toolkit.getImage("src/res/explod.gif"));
            if (StartingClass.game.audio) {
            MediaPlayer.playSound("/res/sound/Explosion.wav");
            }
            if (currentHealth == 0) {
                this.setCenterY(this.getCenterY() - 2);
            }
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EnemyLeader.class.getName()).log(Level.SEVERE, null, ex);
            }
            currentHealth--;
            move.stop();

        }

    }

    public void removeBossProjectiles() {
        for (int i = 0; i < this.getMissiles().size(); i++) {
            if (this.getMissiles().get(i).getY() > 700) {
                this.getMissiles().remove(i);
            }
        }

    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public Thread getMove() {
        return move;
    }

    public void setMove(Thread move) {
        this.move = move;
    }

    public void startMove() {
        if (move.isAlive() == false) {
            move.start();
        }

    }

    public void stopMove() {
        move.stop();
    }

   public void drawBossHealth(Graphics g, ImageObserver im) {
    g.drawImage(healtBarEmpty, 1060, 600, im);
     int distance = 0;   
     for (int i = 0; i < currentHealth; i++) {
            g.drawImage(toolkit.getImage("src/res/health_" + i + ".gif"), 1065 + distance, 600, im);
          distance = distance + 5;
       }
    }
}
