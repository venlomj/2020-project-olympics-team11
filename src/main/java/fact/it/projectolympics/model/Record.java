package fact.it.projectolympics.model;


import java.sql.Date;

public class Record extends Atleet {
    private int recordid, volgnummer, atleetid;
    private String naam, sport, geslacht, record, plaats;
    private Date datum;

    public Record(){}

    public int getRecordid() {
        return recordid;
    }

    public int getVolgnummer() {
        return volgnummer;
    }


    public int getAtleetid() {
        return atleetid;
    }


    public String getNaam() {
        return naam;
    }

    public String getSport() {
        return sport;
    }


    public String getGeslacht() {
        return geslacht;
    }

    public String getRecord() {
        return record;
    }

    public String getPlaats() {
        return plaats;
    }

    public Date getDatum() {
        return datum;
    }

    public void setRecordid(int recordid) {
        this.recordid = recordid;
    }

    public void setVolgnummer(int volgnummer) {
        this.volgnummer = volgnummer;
    }


    public void setAtleetid(int atleetid) {
        this.atleetid = atleetid;
    }


    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }


    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}



