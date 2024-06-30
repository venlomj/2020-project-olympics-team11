package fact.it.projectolympics.repository;

import fact.it.projectolympics.model.Highscore;

import fact.it.projectolympics.model.Land;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public class HighscoreRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    public Highscore scoreOplsaan(String naam, int score){
        Highscore highscore = null;

        jdbcTemplate.update("insert into highscore (naam, score) values (?,?)", naam, score);

        return highscore;
    }

    public ArrayList<Highscore> getAlleHighscores() {
        ArrayList<Highscore> highscores = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM highscore order by naam");
        while (rowSet.next()) {
            Highscore highscore = new Highscore();
            highscore.setHighscoreid(rowSet.getInt("highscoreid"));
            highscore.setNaam(rowSet.getString("naam"));
            highscore.setScore(rowSet.getInt("score"));
            highscores.add(highscore);
        }
        return highscores;
    }
}
