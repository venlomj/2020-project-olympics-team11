package fact.it.projectolympics.model;

public class Atleet {
    private int atleetid;
    private String naam;
    private int landid;
    private String geslacht;

    public Atleet(){
    }

    public int getAtleetid() {
        return atleetid;
    }

    public void setAtleetid(int atleetid) {
        this.atleetid = atleetid;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getLandid() {
        return landid;
    }

    public void setLandid(int landid) {
        this.landid = landid;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }
}
