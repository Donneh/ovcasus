import java.sql.Date;

public class OVChipkaart {
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private float saldo;
    private int reizigerId;
    private Reiziger eigenaar;

    public int getKaartNummer() {
        return kaartNummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setKaartNummer(int nummer) {
        this.kaartNummer = nummer;
    }
    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getReizigerId() {
        return reizigerId;
    }

    public void setReizigerId(int reizigerId) {
        this.reizigerId = reizigerId;
    }

    public Reiziger getEigenaar() {
        return this.eigenaar;
    }

    public void setEigenaar(Reiziger eigenaar) {
        this.eigenaar = eigenaar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("OVChipkaart");
        sb.append("Kaartnummer: ").append(getKaartNummer()).append(" ");
        sb.append("Geldig tot: ").append(getGeldigTot()).append(" ");
        sb.append("Klasse: ").append(getKlasse()).append(" ");
        sb.append("Saldo: ").append(getSaldo()).append(" ");
        sb.append("Reiziger: ").append(getEigenaar()).append(" ");

        return sb.toString();
    }

}
