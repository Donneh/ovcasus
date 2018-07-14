import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao {


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
                reizigers.add(buildreizigerobject(rs));
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
                return buildreizigerobject(rs);
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
    public List<Reiziger> findByGBdatum(Date GBdatum) {
        try {
            String query = "SELECT REIZIGERID, VOORLETTERS, TUSSENVOEGSEL, ACHTERNAAM, GEBOORTEDATUM " +
                    "FROM REIZIGER WHERE GEBOORTEDATUM = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setDate(1, GBdatum);

            ResultSet rs = stmt.executeQuery();

            ArrayList<Reiziger> reizigers =  new ArrayList<Reiziger>();
            while (rs.next()) {
                reizigers.add(buildreizigerobject(rs));
            }
            return reizigers;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Slaat een reiziger op.
     * @param reiziger
     * @return
     */
    public Reiziger save(Reiziger reiziger) {
        try {
            String query = "INSERT INTO REIZIGER" +
                    "(VOORLETTERS, TUSSENVOEGSEL, ACHTERNAAM, GEBOORTEDATUM) VALUES " +
                    "(?, ?, ?, ?)";

            String generatedColumns[] = { "REIZIGERID" };
            PreparedStatement stmt = getConnection().prepareStatement(query, generatedColumns);

            stmt.setString(1, reiziger.getVoorletters());
            stmt.setString(2, reiziger.getTussenveogsel());
            stmt.setString(3, reiziger.getAchternaam());
            stmt.setDate(4, reiziger.getGeboortedatum());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                return findById(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Past een opgeslagen reiziger aan.
     * @param reiziger
     * @return Reiziger
     */
    public Reiziger update(Reiziger reiziger) {
        try {
            String query =
                    "UPDATE REIZIGER SET " +
                    "VOORLETTERS = ?, TUSSENVOEGSEL = ?, ACHTERNAAM = ?, GEBOORTEDATUM = ? " +
                    "WHERE REIZIGERID = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, reiziger.getVoorletters());
            stmt.setString(2, reiziger.getTussenveogsel());
            stmt.setString(3, reiziger.getAchternaam());
            stmt.setDate(4, reiziger.getGeboortedatum());
            stmt.setInt(5, reiziger.getId());

            ResultSet rs = stmt.executeQuery();

            if (rs.rowUpdated()) {
                return findById(reiziger.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Verwijder een reiziger.
     * @param reiziger
     * @return boolean
     */
    public boolean delete(Reiziger reiziger) {
        try {
            String query = "DELETE FROM REIZIGER WHERE REIZIGERID = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, reiziger.getId());
            int rs = stmt.executeUpdate();

            return rs > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Reiziger buildreizigerobject(ResultSet rs) throws  SQLException {
        int id = rs.getInt("REIZIGERID");
        Reiziger reiziger = new Reiziger();
        reiziger.setId(id);
        reiziger.setVoorletters(rs.getString("VOORLETTERS"));
        reiziger.setTussenveogsel(rs.getString("TUSSENVOEGSEL"));
        reiziger.setAchternaam(rs.getString("ACHTERNAAM"));
        reiziger.setGeboortedatum(rs.getDate("GEBOORTEDATUM"));

        OVChipkaartDaoImpl ovdao = new OVChipkaartDaoImpl();
        reiziger.setKaarten(ovdao.findByReiziger(id));
        AdresDaoImpl adao = new AdresDaoImpl();
        reiziger.setAdressen(adao.findByReiziger(id));

        return reiziger;
    }
}
