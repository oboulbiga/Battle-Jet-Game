

package bean;

import java.awt.Image;

import java.awt.Toolkit;

public class Level {

    private int vitt_enemy;
    private int nbr_ennemmie;
    private boolean bosse;
    private Image Background;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    public Level(int vitt_enemy, int nbr_ennemmie, boolean bosse, String Background) {
        this.vitt_enemy = vitt_enemy;
        this.nbr_ennemmie = nbr_ennemmie;
        this.bosse = bosse;
        this.Background = toolkit.getImage("src/res/" + Background);
    }

    public Level(int choix) {
        switch (choix) {
            case 4:
                vitt_enemy=12;
                nbr_ennemmie=500;
                bosse=true;
                this.Background = toolkit.getImage("src/res/warshipsBackground-Récupéré");
                break;
            case 3:
                vitt_enemy=10;
                nbr_ennemmie=400;
                bosse=false;
                this.Background = toolkit.getImage("src/res/warshipsBackground-Récupéré");
                break;
            case 2:
                vitt_enemy=6;
                nbr_ennemmie=300;
                bosse=false;
                this.Background = toolkit.getImage("src/res/warshipsBackground-Récupéré");
                break;
            default:
                vitt_enemy=4;
                nbr_ennemmie=150;
                bosse=false;
                this.Background = toolkit.getImage("src/res/warshipsBackground-Récupéré");
                break;
        }

    }

    public Level() {
    }

    public int getVitt_enemy() {
        return vitt_enemy;
    }

    public void setVitt_enemy(int vitt_enemy) {
        this.vitt_enemy = vitt_enemy;
    }

    public int getNbr_ennemmie() {
        return nbr_ennemmie;
    }

    public void setNbr_ennemmie(int nbr_ennemmie) {
        this.nbr_ennemmie = nbr_ennemmie;
    }

    public boolean isBosse() {
        return bosse;
    }

    public void setBosse(boolean bosse) {
        this.bosse = bosse;
    }

    public Image getBackground() {
        return Background;
    }

    public void setBackground(Image Background) {
        this.Background = Background;
    }

}
