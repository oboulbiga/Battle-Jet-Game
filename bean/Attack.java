
package bean;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import jetGame.StartingClass;
import static jetGame.StartingClass.attack;



public class Attack implements Runnable {

    public static List<EnemyJet> EnemyJets = new ArrayList<>();
    private static int nbr = 0;

    public Attack() {

    }

    public void removeEnnemisOverLimitte() {
        System.out.println("Removing Ennemies");
        for (int i = 0; i < EnemyJets.size(); i++) {

            if (EnemyJets.get(i).getCenterY() >= 700) {
                EnemyJets.get(i).getMoveEnemyJet().stop();
                EnemyJets.remove(i);
            }

        }

    }

    public static void stopAllEnnemi() {
        for (int i = 0; i < EnemyJets.size(); i++) {
            if (EnemyJets.get(i).getMoveEnemyJet().isAlive()) {
                EnemyJets.get(i).getMoveEnemyJet().stop();
            }
        }
    }

    public void startShooting(EnemyJet a) {
        a.getShoot().shoot.start();
        
        try {
            a.getShoot().shoot.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(EnemyJet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void StopShoting(EnemyJet a) {
        if (StartingClass.game.getLevel() == 3 || StartingClass.game.getLevel() == 4) {
            a.getShoot().shoot.stop();
        }
    }

    @Override
    public void run() {

        while (StartingClass.Jet.life != 0) {
            if (EnemyJets.size() < StartingClass.game.getLevel()) {

                System.out.println("Ennemie " + attack.EnemyJets.size());

                System.out.println("Ennemie " + attack.EnemyJets.size());
                EnemyJet a = new EnemyJet();
                a.getMoveEnemyJet().start();
                EnemyJets.add(a);
                if (StartingClass.game.getLevel() == 4) {
                    startShooting(a);
                    if (StartingClass.enemyLeader.getMove().isAlive() == false) {
                        if (nbr == 0) {
                            nbr++;
                            StartingClass.enemyLeader.startMove();
                        }

                    }
                } else if (StartingClass.game.getLevel() == 3) {
                    startShooting(a);
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}

// Ounteni H.Oboulbigaaaaaaaaaß