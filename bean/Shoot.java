
package bean;

import java.util.logging.Level;

import java.util.logging.Logger;

public class Shoot implements Runnable {

    private EnemyJet ae;
    public Thread shoot;

    public Shoot(EnemyJet ae) {
        this.ae = ae;
        this.shoot = new Thread(this,"Shoot Thread");
    }

    @Override
    public void run() {
        while (ae.getMissiles().size() != 4) {
            ae.shoot();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Shoot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
