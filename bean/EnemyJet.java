
package bean;

import java.awt.Graphics;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import jetGame.StartingClass;
import service.MediaPlayer;

  
public class EnemyJet extends Enemy implements Runnable {

    private List<Missile> Missiles;
    private Thread moveEnemyJet;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Shoot shoot;
    Random random = new Random();
    private int lv;

    public EnemyJet() {
        Random ranX = new Random();
        lv = random.nextInt((3 - 1) + 1) + 1;
        setCenterX(ranX.nextInt(1200));
        setCenterY(-80);
        moveEnemyJet = new Thread(this, "EnemyJete");
        if (StartingClass.game.getLevel() == 1) {
            setSpeedY(-StartingClass.game.getLevel() - 2);
        } else if (lv == 2) {
            setSpeedY(-StartingClass.game.getLevel() - 3);
        } else {
            setSpeedY(-StartingClass.game.getLevel() - 4);
        }
        setImage(toolkit.getImage("src/res/ennemiLvl" + lv + ".png"));


        Missiles = new ArrayList<>();
        if ( StartingClass.game.getLevel() == 2 || StartingClass.game.getLevel() == 3 || StartingClass.game.getLevel() == 4) {
            shoot = new Shoot(this);
        }
        
        

    }

    public void update() {

        if (isCrash() == false) {


            setCenterY(getCenterY() - getSpeedY());

            if (getSpeedY() > 0) {
                if (this.getCenterX() < 1270) {
                    this.setCenterX(this.getCenterX() + getSpeedY());
                }
            } else if (getSpeedY() < 0) {
                if (this.getCenterX() > 20) {
                    this.setCenterX(this.getCenterX() + getSpeedY());
                }
            }
            if (StartingClass.game.getLevel() == 3 || StartingClass.game.getLevel() == 4) {
                follow();
            }
            if (lv == 1) {
                getR().setBounds(getCenterX(), getCenterY(), 69, 60);
            } else if (lv == 2) {
                getR().setBounds(getCenterX(), getCenterY(), 69, 60);
            } else {
                getR().setBounds(getCenterX(), getCenterY(), 90, 69);
            }


        }

    }

    public void destroy() {
        synchronized (moveEnemyJet) {

            this.setCrash(true);
            if (StartingClass.game.audio) {
            MediaPlayer.playSound("/res/sound/Explosion.wav");
            }
            Image im = toolkit.getImage("src/res/explode.gif");

            this.setImage(im);
            try {
                moveEnemyJet.wait(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(EnemyJet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Attack.EnemyJets.remove(this);
            if (StartingClass.game.getLevel() == 3 || StartingClass.game.getLevel() == 4) {
                System.out.println("lvl "+StartingClass.game.getLevel()+" isAlive "+shoot.shoot.isAlive());
                    shoot.shoot.stop();
                
            }
            moveEnemyJet.stop();

            System.out.println("Enemy Killed");
        }
    }

    public void startAvion() {
        this.update();
        moveEnemyJet.start();
    }

    public void updateAll() {
        for (int i = 0; i < Missiles.size(); i++) {
            Missiles.get(i).updateMissileEnnemi();
        }
    }

    public void drawAll(Graphics g, ImageObserver imageObserver) {
        for (int i = 0; i < Missiles.size(); i++) {
            g.drawImage(Missiles.get(i).getBullet(), Missiles.get(i).getX(), Missiles.get(i).getY(), imageObserver);
        }
    }

    public void shoot() {

        Missile p = new Missile (getCenterX() + 30, getCenterY() + 60, true);
        Missiles.add(p);

    }

    public List<Missile> getProjectiles() {
        return Missiles;
    }

    public void setProjectiles(List<Missile> Missiles) {
        this.Missiles = Missiles;
    }

    public Shoot getShoot() {
        return shoot;
    }

    public void setShoot(Shoot shoot) {
        this.shoot = shoot;
    }

    @Override
    public void run() {
        while (this.getMoveEnemyJet().isAlive()) {
            if (this.getCenterY() < 700) {
                update();
                for (int i = 0; i < StartingClass.Jet.getMissiles().size();i++) {
                    if (StartingClass.Jet.getMissiles().get(i).checkCollision(this.getR()) == true) {
                        if (lv == 1) {
                            StartingClass.game.score += 100;
                        } else if (lv == 2) {
                            StartingClass.game.score += 50;
                        } else {
                            StartingClass.game.score += 150;
                        }

                        StartingClass.Jet.getMissiles().remove(i);
                        destroy();
                    }
                }
                if (this.getR().intersects(StartingClass.Jet.getR()) == true) {
                    if (StartingClass.Jet.getshield() == 0) {
                        StartingClass.Jet.destroy();
                        destroy();
                    } else {
                        StartingClass.Jet.setBouclier(StartingClass.Jet.getshield() - 1);
                        destroy();
                    }

                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else {
                Attack.EnemyJets.remove(this);
                this.setCrash(true);
                moveEnemyJet.stop();
                if (StartingClass.game.getLevel() == 3 || StartingClass.game.getLevel() == 4) {
                    
                        shoot.shoot.stop();
                    
                }
            }
        }
    }

    public Thread getMoveEnemyJet() {
        return moveEnemyJet;
    }

    public void setEnnemiJet(Thread moveEnemyJet) {
        this.moveEnemyJet = moveEnemyJet;
    }

}
