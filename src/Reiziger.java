import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Reiziger {

    private String naam;
    private Date geboortedatum;
    private List<OVChipkaart> kaarten = new ArrayList<OVChipkaart>();

    public Reiziger(String naam, Date geboortedatum) {
        this.naam = naam;
        this.geboortedatum = geboortedatum;
    }

    /**
     * returned de naam van de reiziger.
     * @return naam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Set de naam van de reiziger.
     * @param naam
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * Returned de geboortedatum van de reiziger.
     * @return geboortedatum
     */
    public Date getGBdatum() {
        return geboortedatum;
    }

    /**
     * Set de geboortedatum van de reiziger.
     * @param gbdatum
     */
    public void setGBdatum(Date gbdatum) {
        this.geboortedatum = gbdatum;
    }

    public List<OVChipkaart> getKaarten() {
        return this.kaarten;
    }

    public void setKaarten(List<OVChipkaart> kaarten) {
        this.kaarten = kaarten;
    }

    /**
     * Kijk of obj hetzelfde is als de reiziger.
     * @param obj
     * @return boolean
     */
    public boolean equals(Object obj) {
        if (obj instanceof Reiziger) {
            Reiziger reiziger = (Reiziger) obj;
            return this.naam.equals(reiziger.naam) && this.geboortedatum == reiziger.geboortedatum;
        }

        return false;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Reiziger");
        output.append("Naam: " + this.getNaam() + " ");
        output.append("Geboortedatum: " + this.getGBdatum() + " ");
        output.append("Kaarten: ");
        for (OVChipkaart kaart : getKaarten()) {
            output.append(kaart);
        }
        return output.toString();
    }

}
