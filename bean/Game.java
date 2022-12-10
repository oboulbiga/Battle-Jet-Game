
package bean;


public class Game {

    private Player player = new Player();
    private String Jet;
    private Integer Level;
    private EnemyLeader boss;
    public static boolean audio=false;
    public static int score = 0;

    public Game() {
    }

    public Game(String player, String Jet, Integer Level, boolean audio) {
        this.player.setNom(player);
        this.Jet = Jet;
        this.Level = Level;
        this.audio = audio;
        
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getJet() {
        return Jet;
    }

    public void setJet(String Jet) {
        this.Jet = Jet;
    }

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer Level) {
        this.Level = Level;
    }

    public EnemyLeader getBoss() {
        return boss;
    }

    public void setBoss(EnemyLeader boss) {
        this.boss = boss;
    }

    public static boolean isAudio() {
        return audio;
    }

    public static void setAudio(boolean audio) {
        Game.audio = audio;
    }

    
}
