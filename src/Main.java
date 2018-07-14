import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws ParseException {
        ReizigerOracleDaoImpl rdao = new ReizigerOracleDaoImpl();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

//        System.out.println("-- Reizigers findall() --");
//        for (Reiziger reiziger : rdao.findAll()) {
//            System.out.println(reiziger);
//        }
//
//        System.out.println("-- Reizigers findById() --");
//        System.out.println(rdao.findById(1));

//        System.out.println("-- Reizigers findByGBDatum() --");
//        System.out.println(rdao.findByGBdatum(new Date(df.parse("10-05-1994").getTime())));

//        System.out.println("-- Reizigers save() --");
//        Reiziger rz = new Reiziger();
//        rz.setVoorletters("F");
//        rz.setTussenveogsel("van");
//        rz.setAchternaam("Walsem");
//        df = new SimpleDateFormat("dd-MM-yyyy");
//        rz.setGeboortedatum(new Date(df.parse("10-05-1994").getTime()));
//        Reiziger rz2 = rdao.save(rz);
//        System.out.println(rz);
//
//
//        System.out.println("-- Reizigers Update");
//        rz2.setVoorletters("D");
//        System.out.println(rdao.update(rz2));

//            System.out.println("-- Reiziger Delete() --");
//            System.out.println(rdao.delete(rdao.findById(16)));

//        System.out.println("-- OVChipkaarten Findall() --");
        OVChipkaartDaoImpl odao = new OVChipkaartDaoImpl();
//        for (OVChipkaart kaart : odao.findAll()) {
//            System.out.println(kaart);
//        }

//        System.out.println("-- OVChipkaarten findByReiziger() --");
//        System.out.println(odao.findByReiziger(2));

//        System.out.println("-- OVChipkaarten save() --");
//        OVChipkaart kaart = new OVChipkaart();
//        kaart.setKaartNummer(56874);
//        kaart.setKlasse(1);
//        kaart.setSaldo(50);
//        kaart.setReizigerId(25);
//        kaart.setGeldigTot(new Date(df.parse("25-08-2020").getTime()));
//        System.out.println(odao.save(kaart));

//        System.out.println("-- Adressen findall() --");
//        AdresDaoImpl adao = new AdresDaoImpl();
//        for (Adres adres : adao.findAll()) {
//            System.out.println(adres);
//        }
    }
}
