
package bean;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import jetGame.StartingClass;
import static jetGame.StartingClass.game;
import service.MediaPlayer;


public class Jet {

    private int centerX = 600;
    private int centerY = 485;

    public static int life;
    public static int touch = 0;

    private int speedX = 0;
    private int speedY = 0;

    private int height;
    private int width;

    public static int type;

    public static ArrayList<Missile> Missiles = new ArrayList<Missile>();

    private boolean movingUp = false;
    private boolean movingDown = false;

    private int shield = 0;
    private PowerUp powerUpON;

    private Image image;
    private Image shieldImg;
    private Image drawingimage;
    private Image imageMoveUp;
    private Image imageMoveDown;
    private Image imageMoveLeft;
    private Image imageMoveRight;

    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    private Rectangle r;

    private static int globalSpeed;

    public Jet(String name) {

        r = new Rectangle(0, 0, 0, 0);
        image = toolkit.getImage("src/res/" + name + ".png");
        drawingimage = toolkit.getImage("src/res/" + name + ".png");
        shieldImg = toolkit.getImage("src/res/shieldJet.png");
        if (name.equals("MiG-51S")) {
            life = 3;
            type = 1;
            height = 48;
            width = 87;
            globalSpeed = 4;
            imageMoveDown = toolkit.getImage("src/res/MiG-51S.png");
            imageMoveUp = toolkit.getImage("src/res/mini-plan1-onMove.png");
            imageMoveLeft = toolkit.getImage("src/res/moveLeft.png");
            imageMoveRight = toolkit.getImage("src/res/moveRight.png");
        } else if (name.equals("F_A-28A-mini")) {
            life = 4;
            type = 2;
            height = 60;
            width = 89;
            globalSpeed = 6;
            imageMoveDown = image;
            imageMoveUp = image;
            imageMoveLeft = image;
            imageMoveRight = image;
        } else if (name.equals("Su-51K-mini")) {
            life = 6;
            type = 4;
            height = 80;
            width = 96;
            globalSpeed = 12;
            imageMoveDown = image;
            imageMoveUp = image;
            imageMoveLeft = image;
            imageMoveRight = image;
        } else {
            life = 5;
            type = 3;
            height = 48;
            width = 87;
            globalSpeed = 9;
            imageMoveDown = image;
            imageMoveUp = image;
            imageMoveLeft = image;
            imageMoveRight = image;
        }

    }

    public void update() {

        if (speedX < 0) {
            if (centerX > 20) {
                centerX += speedX;
            }
        } else if (speedX > 0) {
            if (centerX < 1270) {
                centerX += speedX;
            }
        } else if (speedY < 0) {
            if (centerY > 20) {
                centerY += speedY;

            }

        } else if (speedY > 0) {
            if (centerY < 485) {
                centerY += speedY;
            }

        }
        r.setRect(centerX, centerY, height, width);

    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getShieldImg() {
        return shieldImg;
    }

    public void setBouclierImg(Image shieldImg) {
        this.shieldImg = shieldImg;
    }

    public void shoot() {
        if (type == 4) {
            Missile p1 = new Missile(centerX + 65, centerY + 30, false);
            Missile p2 = new Missile(centerX + 10, centerY + 30, false);
            Missiles.add(p1);
            Missiles.add(p2);
        } else if (type == 1 || type == 2) {
            if (powerUpON != null) {
                if (powerUpON.getType() == 2) {
                    Missile p1 = new Missile(centerX + 15, centerY + 20, false);
                    Missile p2 = new Missile(centerX + 35, centerY + 20, false);
                   
                    Missiles.add(p1);
                    Missiles.add(p2);
                } else if (powerUpON.getType() == 3) {
                    Missile p1 = new Missile  (centerX, centerY - (width / 2), false);
                    Missile p2 = new Missile (centerX + (height / 2), centerY - 10, false);
                    Missile p3 = new Missile (centerX + height, centerY - (width / 2), false);
                    
                    Missiles.add(p1);
                    Missiles.add(p2);
                    Missiles.add(p3);
                } else {
                    Missile p = new Missile(centerX + 24, centerY - 10, false);
                    Missiles.add(p);
                }

            } else {
                Missile p = new Missile(centerX + 24, centerY - 10, false);
                Missiles.add(p);
            }

        } else if (type == 3) {
            if (powerUpON != null) {
                if (powerUpON.getType() == 2) {
                    Missile p1 = new Missile (centerX + 15, centerY + 20, false);
                    Missile p2 = new Missile (centerX + 35, centerY + 20, false);
                    Missiles.add(p1);
                    Missiles.add(p2);
                } else if (powerUpON.getType() == 3) {
                    Missile p1 = new Missile (centerX, centerY - (width / 2), false);
                    Missile p2 = new Missile (centerX + (height / 2), centerY - 10, false);
                    Missile p3 = new Missile (centerX + height, centerY - (width / 2), false);
                    
                    Missiles.add(p1);
                    Missiles.add(p2);
                    Missiles.add(p3);
                } else {
                    Missile p1 = new Missile (centerX + 24, centerY - 40, false);
                    Missile p2 = new Missile (centerX + 24, centerY - 10, false);
                    Missiles.add(p1);
                    Missiles.add(p2);
                }

            } else {
                Missile p1 = new Missile (centerX + 24, centerY - 40, false);
                Missile p2 = new Missile (centerX + 24, centerY - 10, false);
                Missiles.add(p1);
                Missiles.add(p2);
            }

        }
    }

    public void destroy() {
        toucher();
        if (StartingClass.game.audio) {
            MediaPlayer.playSound("/res/sound/explosion_fire(1).wav");
        }
        drawingimage = toolkit.getImage("src/res/explode.gif");
    }

    public void up() {
        speedY = -globalSpeed;
    }

    public void down() {
        speedY = globalSpeed;
    }

    public void moveRight() {
        speedX = globalSpeed;

    }

    public void moveLeft() {
        speedX = -globalSpeed;
    }

    public void stopUP() {
        setMovingUp(false);
        stop();
    }

    public void stopDown() {
        setMovingDown(false);
        stop();
    }

    public void stop() {
        if (isMovingDown() == false && isMovingUp() == false) {
            speedX = 0;
            speedY = 0;
        }
        if (isMovingDown() == false && isMovingUp() == true) {
            up();
        }

        if (isMovingDown() == true && isMovingUp() == false) {
            down();
        }

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

    public static int getlife() {
        return life;
    }

    public static void setlife(int life) {
       Jet.life = life;
    }

    public int getspeedX() {
        return speedX;
    }

    public void setspeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getspeedY() {
        return speedY;
    }

    public void setspeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public ArrayList<Missile> getMissiles() {
        return Missiles ;
    }

    public Rectangle getR() {
        return r;
    }

    public void setR(Rectangle r) {
        this.r = r;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Image getImageMoveUp() {
        return imageMoveUp;
    }

    public void setImageMoveUp(Image imageMoveUp) {
        this.imageMoveUp = imageMoveUp;
    }

    public Image getImageMoveDown() {
        return imageMoveDown;
    }

    public void setImageMoveDown(Image imageMoveDown) {
        this.imageMoveDown = imageMoveDown;
    }

    public Image getImageMoveLeft() {
        return imageMoveLeft;
    }

    public void setImageMoveLeft(Image imageMoveLeft) {
        this.imageMoveLeft = imageMoveLeft;
    }

    public Image getImageMoveRight() {
        return imageMoveRight;
    }

    public void setImageMoveRight(Image imageMoveRight) {
        this.imageMoveRight = imageMoveRight;
    }

    public static int getGlobalSpeed() {
        return globalSpeed;
    }

    public Image getDrawingimage() {
        return drawingimage;
    }

    public void setDrawingimage(Image drawingimage) {
        this.drawingimage = drawingimage;
    }

    public static void setGlobalSpeed(int globalSpeed) {
        Jet.globalSpeed = globalSpeed;
    }


    public void toucher() {
        touch++;
        if (touch== 3) {
            life = life - 1;
            touch = 0;
        }
    }

    public int getshield() {
        return shield;
    }

    public void setBouclier(int shiedl) {
        this.shield = shield;
    }

    public PowerUp getPowerUpON() {
        return powerUpON;
    }

    public void setPowerUpON(PowerUp powerUpON) {
        this.powerUpON = powerUpON;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
