import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao {

    private ArrayList<Reiziger> reizigers =  new ArrayList<Reiziger>();

    /**
     * Returned alle reizigers.
     * @return ArrayList<Reiziger>
     */
    public List<Reiziger> findAll()  {
        try {
            Statement stmt = getConnection().createStatement();
            String query = "SELECT REIZIGERID, VOORLETTERS, TUSSENVOEGSEL, ACHTERNAAM, GEBOORTEDATUM FROM REIZIGER";
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Reiziger> reizigers =  new ArrayList<Reiziger>();
            while(rs.next()) {
                reizigers.add(buildReizigerObjecT(rs));
            }
            return reizigers;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Reiziger findById(int id) {
        try {
            String query = "SELECT REIZIGERID, VOORLETTERS, TUSSENVOEGSEL, ACHTERNAAM, GEBOORTEDATUM " +
                    "FROM REIZIGER " +
                    "WHERE REIZIGERID = ?";

            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String naam = rs.getString("VOORLETTERS") + " " + rs.getString("TUSSENVOEGSEL") + " " + rs.getString("ACHTERNAAM");
                Reiziger reiziger = new Reiziger(naam, rs.getDate("GEBOORTEDATUM"));

                return reiziger;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Returned alle reizigers met specifieke geboortedatum.
     * @param GBdatum
     * @return ArrayList<Reiziger>
     */
    public List<Reiziger> findByGBdatum(String GBdatum) {
        ArrayList<Reiziger> result = new ArrayList<Reiziger>();
        for (Reiziger reiziger : reizigers) {
            if (reiziger.getGBdatum().toString().equals(GBdatum)) {
                result.add(reiziger);
            }
        }
        return result;
    }

    /**
     * Slaat een reiziger op.
     * @param reiziger
     * @return
     */
    public Reiziger save(Reiziger reiziger) {
        if(!reizigers.contains(reiziger)) {
            reizigers.add(reiziger);
        }

        return reiziger;
    }

    /**
     * Past een opgeslagen reiziger aan.
     * @param origineel, updatedReiziger
     * @return Reiziger
     */
    public Reiziger update(Reiziger origineel, Reiziger updatedReiziger) {
        if(reizigers.contains(origineel)) {
            reizigers.remove(origineel);
            reizigers.add(updatedReiziger);
        }

        return updatedReiziger;
    }

    /**
     * Verwijder een reiziger.
     * @param reiziger
     * @return boolean
     */
    public boolean delete(Reiziger reiziger) {
        return reizigers.remove(reiziger);
    }

    private Reiziger buildReizigerObjecT(ResultSet rs) throws  SQLException {
        StringBuilder naam = new StringBuilder(rs.getString("VOORLETTERS") + " ");
        naam.append(rs.getString("TUSSENVOEGSEL") + " ");
        naam.append(rs.getString("ACHTERNAAM") + " ");
        Reiziger reiziger = new Reiziger(naam.toString(), rs.getDate("GEBOORTEDATUM"));

        OVChipkaartDao ovdao = new OVChipkaartDaoImpl();
        reiziger.setKaarten(ovdao.findByReiziger(rs.getInt("REIZIGERID")));
        return reiziger;
    }
}
