
package bean;


public class Player {

    private String nom;
    private double totalScore = 0;
    public static int tentative = 3;

    public Player() {
    }

   
    public Player(String nom) {
        this.nom = nom;
        this.totalScore = 0;
        tentative = 3;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public static int getTentative() {
        return tentative;
    }

    public static void setTentative(int tentative) {
        Player.tentative = tentative;
    }

}




