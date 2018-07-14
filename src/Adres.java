public class Adres {

    private int id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    private int reizigerID;
    private Reiziger reiziger;

    public Adres(int id, String postcode, String huisnummer, String straat, String woonplaats, int reizigerID) {
        this.id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reizigerID = reizigerID;
    }

    public Adres() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public int getReizigerID() {
        return reizigerID;
    }

    public void setReizigerID(int reizigerID) {
        this.reizigerID = reizigerID;
    }

    public Reiziger getReiziger() { return reiziger; }

    public void setReiziger(Reiziger reiziger) { this.reiziger = reiziger; }

    public String toString() {
        StringBuilder output = new StringBuilder("Adres");
        output.append("ID: ").append(getId()).append(" ");
        output.append("Postcode: ").append(getPostcode()).append(" ");
        output.append("Huisnummer: ").append(getHuisnummer()).append(" ");
        output.append("Straat: ").append(getStraat()).append(" ");
        output.append("Reiziger: ").append(getReiziger()).append(" ");

        return output.toString();
    }
}
