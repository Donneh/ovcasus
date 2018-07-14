import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Reiziger {

    private int id;
    private String voorletters;
    private String tussenveogsel;
    private String achternaam;
    private Date geboortedatum;
    private List<OVChipkaart> kaarten = new ArrayList<OVChipkaart>();
    private List<Adres> adressen = new ArrayList<>();

    public Reiziger(int id, String voorletters, String tussenveogsel, String achternaam, Date geboortedatum, List<OVChipkaart> kaarten) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenveogsel = tussenveogsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.kaarten = kaarten;
    }

    public Reiziger() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenveogsel() {
        return tussenveogsel;
    }

    public void setTussenveogsel(String tussenveogsel) {
        this.tussenveogsel = tussenveogsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public List<OVChipkaart> getKaarten() {
        return kaarten;
    }

    public void setKaarten(List<OVChipkaart> kaarten) {
        this.kaarten = kaarten;
    }

    public List<Adres> getAdressen() {
        return this.adressen;
    }

    public void setAdressen(List<Adres> adressen) {
        this.adressen = adressen;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("Reiziger - ");
        output.append("Id: ").append(getId()).append(" ");
        output.append("Voorletters: ").append(getVoorletters()).append(" ");
        output.append("Tussenvoegsel: ").append(getTussenveogsel()).append(" ");
        output.append("Achternaam: ").append(getAchternaam()).append(" ");
        output.append("Kaarten: ");
        for (OVChipkaart ov : getKaarten()) {
            output.append(ov);
            output.append(" ");
        }

        return output.toString();
    }
}