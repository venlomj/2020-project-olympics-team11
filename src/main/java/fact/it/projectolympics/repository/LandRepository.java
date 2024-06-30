package fact.it.projectolympics.repository;

import fact.it.projectolympics.model.Land;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class LandRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Land getEersteLand() {
        Land land = null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM land where landid = 1");
        if (rowSet.next()) {
            land = new Land();
            land.setLandid(rowSet.getInt("landid"));
            land.setNaam(rowSet.getString("naam"));
        }
        return land;
    }

    public Land getLand(int id) {
        Land land = null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM land where landid = ?", id);
        if (rowSet.next()) {
            land = new Land();
            land.setLandid(rowSet.getInt("landid"));
            land.setNaam(rowSet.getString("naam"));
        }
        return land;
    }

    public ArrayList<Land> getAlleLanden() {
        ArrayList<Land> landen = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM land order by naam");
        while (rowSet.next()) {
            Land land = new Land();
            land.setLandid(rowSet.getInt("landid"));
            land.setNaam(rowSet.getString("naam"));
            landen.add(land);
        }
        return landen;
    }
    public ArrayList<String> getAlleLandenString() {
        ArrayList<String> landen = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM land order by naam");

        while (rowSet.next()) {
            String land = rowSet.getString("naam");
            landen.add(land);
        }
        return landen;
    }

    public void insertLand(String naam) {
        jdbcTemplate.update("insert into land (naam) values (?)", naam);
    }

    public void deleteLand(int landid) {
        jdbcTemplate.update("delete from land where landid = ?", landid);
    }

    public void updateLand(int landid, String naam) {
        jdbcTemplate.update("update land set naam= ? where landid = ?", naam, landid);
    }
}


