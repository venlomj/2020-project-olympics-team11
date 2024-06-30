
package fact.it.projectolympics.model;

public class Highscore {
    private int highscoreid, score;
    private String naam;

    public Highscore() {
    }

    public int getHighscoreid() {
        return highscoreid;
    }

    public void setHighscoreid(int highscoreid) {
        this.highscoreid = highscoreid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
