package fact.it.projectolympics.repository;

import fact.it.projectolympics.model.Atleet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class AtleetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Atleet getAtleetNaam(String naam){
        Atleet atleet = null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM atleet where naam like ?", "%"+naam+"%");
        if (rowSet.next()){
            atleet = new Atleet();
            atleet.setAtleetid(rowSet.getInt("atleetid"));
            atleet.setNaam(rowSet.getString("naam"));
            atleet.setLandid(rowSet.getInt("landid"));
            atleet.setGeslacht(rowSet.getString("geslacht"));
        }
        return atleet;
    }

    public ArrayList<Atleet> getGeslacht(String geslacht){
        ArrayList<Atleet> atleten = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM atleet where geslacht = ?", geslacht);
        while (rowSet.next()){
            Atleet atleet = new Atleet();
            atleet.setAtleetid(rowSet.getInt("atleetid"));
            atleet.setNaam(rowSet.getString("naam"));
            atleet.setGeslacht(rowSet.getString("geslacht"));
            atleet.setLandid(rowSet.getInt("landid"));
            atleten.add(atleet);
        }
        return atleten;
    }

    public ArrayList<Atleet> getLand(int landid){
        ArrayList<Atleet> Atleten = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM atleet where landid = ?", landid);
        while (rowSet.next()){
            Atleet atleet = new Atleet();
            atleet.setAtleetid(rowSet.getInt("atleetid"));
            atleet.setNaam(rowSet.getString("naam"));
            atleet.setGeslacht(rowSet.getString("geslacht"));
            atleet.setLandid(rowSet.getInt("landid"));
            Atleten.add(atleet);
        }
        return Atleten;
    }

    public ArrayList<Atleet> getAlleAtleten(){
        ArrayList<Atleet> Atleten = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM atleet ORDER BY naam");
        while (rowSet.next()){
            Atleet atleet = new Atleet();
            atleet.setAtleetid(rowSet.getInt("atleetid"));
            atleet.setNaam(rowSet.getString("naam"));
            atleet.setGeslacht(rowSet.getString("geslacht"));
            atleet.setLandid(rowSet.getInt("landid"));
            Atleten.add(atleet);
        }
        return Atleten;
    }


    public Atleet getAtleet(int atleetid){
        Atleet atleet = null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM atleet where atleetid = ?", atleetid);

        if (rowSet.next()) {
            atleet = new Atleet();
            atleet.setAtleetid(rowSet.getInt("atleetid"));
            atleet.setNaam(rowSet.getString("naam"));
            atleet.setLandid(rowSet.getInt("landid"));
            atleet.setGeslacht(rowSet.getString("geslacht"));
        }
        return atleet;
    }


    public void insertAtleet(String naam, String geslacht, int landid){
        jdbcTemplate.update("insert into atleet (naam,geslacht,landid) values (?,?,?)",naam,geslacht,landid);
    }

    public void updateAtleet(String naam, String geslacht, int landid, int atleetid){
        jdbcTemplate.update("update atleet set naam=?, geslacht=?, landid=? where atleetid = ?", naam, geslacht, landid, atleetid);
    }

    public void deleteAtleet(int atleetid){
        jdbcTemplate.update("delete from atleet where atleetid=?", atleetid);
    }
}
