// recordrepository



package fact.it.projectolympics.repository;

import fact.it.projectolympics.model.Atleet;




import fact.it.projectolympics.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public class RecordRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Record getRecordZoekRecord(String zoekRecord) {
        Record record = null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from record where sport like ?", "%" + zoekRecord + "%");
        if (rowSet.next()) {
            record = new Record();
            record.setRecordid(rowSet.getInt("recordid"));
            record.setSport(rowSet.getString("sport"));
            record.setDatum(rowSet.getDate("datum"));
            record.setRecord(rowSet.getString("record"));
            record.setPlaats(rowSet.getString("plaats"));
            record.setVolgnummer(rowSet.getInt("volgnummer"));
            record.setGeslacht(rowSet.getString("geslacht"));
            record.setAtleetid(rowSet.getInt("atleetid"));
            record.setNaam(rowSet.getString("naam"));

        }
        return record;
    }

    public ArrayList<Record> getJaartal(String jaartal) {
        ArrayList<Record> Records = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from record where datum like ?", jaartal + "%");

        while (rowSet.next()) {
            Record record = new Record();
            record.setRecordid(rowSet.getInt("recordid"));
            record.setSport(rowSet.getString("sport"));
            record.setDatum(rowSet.getDate("datum"));
            record.setRecord(rowSet.getString("record"));
            record.setPlaats(rowSet.getString("plaats"));
            record.setVolgnummer(rowSet.getInt("volgnummer"));
            record.setGeslacht(rowSet.getString("geslacht"));
            record.setAtleetid(rowSet.getInt("atleetid"));
            record.setNaam(rowSet.getString("naam"));
            Records.add(record);

        }
        return Records;


    }


    public ArrayList<Record> getAlleRecords() {
        ArrayList<Record> Records = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM record ORDER BY plaats");
        while (rowSet.next()) {
            Record record = new Record();
            record.setRecordid(rowSet.getInt("recordid"));
            record.setSport(rowSet.getString("sport"));
            record.setDatum(rowSet.getDate("datum"));
            record.setRecord(rowSet.getString("record"));
            record.setPlaats(rowSet.getString("plaats"));
            record.setVolgnummer(rowSet.getInt("volgnummer"));
            record.setGeslacht(rowSet.getString("geslacht"));
            record.setAtleetid(rowSet.getInt("atleetid"));
            record.setNaam(rowSet.getString("naam"));
            Records.add(record);

        }
        return Records;
    }

    public ArrayList<Record> getPlaats(String plaats) {
        ArrayList<Record> Records = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from record where plaats = ?", plaats);

        while (rowSet.next()) {
            Record record = new Record();
            record.setRecordid(rowSet.getInt("recordid"));
            record.setSport(rowSet.getString("sport"));
            record.setDatum(rowSet.getDate("datum"));
            record.setRecord(rowSet.getString("record"));
            record.setPlaats(rowSet.getString("plaats"));
            record.setVolgnummer(rowSet.getInt("volgnummer"));
            record.setGeslacht(rowSet.getString("geslacht"));
            record.setAtleetid(rowSet.getInt("atleetid"));
            record.setNaam(rowSet.getString("naam"));
            Records.add(record);

        }
        return Records;
    }

    public ArrayList<String> getAlleSteden() {
        ArrayList<String> steden = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select distinct plaats from record  order by plaats");

        while (rowSet.next()) {
            String stad = rowSet.getString("plaats");
            steden.add(stad);
        }
        return steden;
    }


    public ArrayList<Record> getAtleetid(int atleetid) {
        ArrayList<Record> Records = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from record where atleetid = ?", atleetid);

        while (rowSet.next()) {
            Record record = new Record();
            record.setRecordid(rowSet.getInt("recordid"));
            record.setSport(rowSet.getString("sport"));
            record.setDatum(rowSet.getDate("datum"));
            record.setRecord(rowSet.getString("record"));
            record.setPlaats(rowSet.getString("plaats"));
            record.setVolgnummer(rowSet.getInt("volgnummer"));
            record.setGeslacht(rowSet.getString("geslacht"));
            record.setAtleetid(rowSet.getInt("atleetid"));
            record.setNaam(rowSet.getString("naam"));
            Records.add(record);

        }
        return Records;
    }

    public Record getRecord(int nummer) {
        Record record = null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM record where recordid = ?", nummer);


        if (rowSet.next()) {
            record = new Record();
            record.setRecordid(rowSet.getInt("recordid"));
            record.setSport(rowSet.getString("sport"));
            record.setDatum(rowSet.getDate("datum"));
            record.setRecord(rowSet.getString("record"));
            record.setPlaats(rowSet.getString("plaats"));
            record.setVolgnummer(rowSet.getInt("volgnummer"));
            record.setGeslacht(rowSet.getString("geslacht"));
            record.setAtleetid(rowSet.getInt("atleetid"));
            record.setNaam(rowSet.getString("naam"));

        }
        return record;
    }


}

