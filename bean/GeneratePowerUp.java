
package bean;

import java.awt.Graphics;

import java.awt.image.ImageObserver;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jetGame.StartingClass;
import service.MediaPlayer;



public class GeneratePowerUp implements Runnable {

    public static List<PowerUp> powerUps = new ArrayList<>();

    
    private Thread generate;

    public GeneratePowerUp() {
        generate = new Thread(this);
    }

    public void drawPowerUp(Graphics g, ImageObserver io) {
        for (int i = 0; i < powerUps.size(); i++) {
            g.drawImage(powerUps.get(i).getImage(), powerUps.get(i).getX(), powerUps.get(i).getY(), io);
        }
    }

    @Override
    public void run() {
        try {
            sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GeneratePowerUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (powerUps.isEmpty() && StartingClass.game.getLevel() >=2) {
            PowerUp p = new PowerUp();
            p.getMove().start();
            powerUps.add(p);
            System.out.println("Power Up added");
            if (StartingClass.game.audio) {
            MediaPlayer.playSound("/res/sound/power_up.wav");
            }
            try {
                sleep(60000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GeneratePowerUp.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }

    public void startGeneration() {
        generate.start();
    }

    public void stopGeneration() {
        generate.stop();
    }

}
