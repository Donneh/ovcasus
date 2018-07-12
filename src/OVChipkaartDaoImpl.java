import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDaoImpl extends  OracleBaseDao implements OVChipkaartDao {



    public OVChipkaart save(OVChipkaart ovChipkaart) {
        try {
            String query =
                    "INSERT INTO OV_CHIPKAART " +
                    "(KAARTNUMMER, GELDIGTOT, KLASSE, SALDO, REIZIGERID)" +
                    " VALUES " +
                    "(?, ?, ?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, ovChipkaart.getKaartNummer());
            stmt.setDate(2, ovChipkaart.getGeldigTot());
            stmt.setInt(3, ovChipkaart.getKlasse());
            stmt.setFloat(4, ovChipkaart.getSaldo());
            stmt.setInt(5, ovChipkaart.getReizigerId());

            ResultSet rs = stmt.executeQuery();
            if (rs.rowInserted()) {
                return buildOVChipkaartObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public OVChipkaart update(OVChipkaart ovChipkaart) {
        try {
            String query =
                    "UPDATE OV_CHIPKAART SET " +
                    "GELDIGTOT = ?, KLASSE = ?, SALDO = ? " +
                    "WHERE KAARTNUMMER = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setDate(1, ovChipkaart.getGeldigTot());
            stmt.setInt(2, ovChipkaart.getKlasse());
            stmt.setFloat(3, ovChipkaart.getSaldo());
            stmt.setInt(4, ovChipkaart.getKaartNummer());

            ResultSet rs = stmt.executeQuery();

            if (rs.rowUpdated()) {
                return findByKaartnummer(ovChipkaart.getKaartNummer());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(OVChipkaart ovChipkaart) {
        try {
            String query = "DELETE FROM OV_CHIPKAART WHERE KAARTNUMMER = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, ovChipkaart.getKaartNummer());
            ResultSet rs = stmt.executeQuery();

            return rs.rowDeleted();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<OVChipkaart> findByReiziger(int reizigerId) {
        try {
            String query = "SELECT KAARTNUMMER, GELDIGTOT, KLASSE, SALDO FROM OV_CHIPKAART WHERE REIZIGERID = ?";
            PreparedStatement stmt =  getConnection().prepareStatement(query);
            stmt.setInt(1, reizigerId);

            ResultSet rs = stmt.executeQuery();
            List<OVChipkaart> ovchipkaarten = new ArrayList<>();
            while(rs.next()) {
                ovchipkaarten.add(buildOVChipkaartObject(rs));
            }

            return ovchipkaarten;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    public List<OVChipkaart> findAll() {
        try {
            String query = "SELECT KAARTNUMMER, GELDIGTOT, KLASSE, SALDO, REIZIGERID FROM OV_CHIPKAART";
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<OVChipkaart> ovchipkaarten = new ArrayList<>();
            while (rs.next()) {
                ovchipkaarten.add(buildOVChipkaartObject(rs));
            }

            return ovchipkaarten;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public OVChipkaart findByKaartnummer(int kaartnummer) {
        try {
            String query = "SELECT KAARTNUMMER, GELDIGTOT, KLASSE, SALDO, REIZIGERID FROM OV_CHIPKAART WHERE KAARTNUMMER = ?";
            PreparedStatement stmt =  getConnection().prepareStatement(query);
            stmt.setInt(1, kaartnummer);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                return  buildOVChipkaartObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    private OVChipkaart buildOVChipkaartObject(ResultSet rs) throws SQLException {
        OVChipkaart ov = new OVChipkaart();
        ov.setKaartNummer(rs.getInt("KAARTNUMMER"));
        ov.setKlasse(rs.getInt("KLASSE"));
        ov.setGeldigTot(rs.getDate("GELDIGTOT"));
        ov.setSaldo(rs.getFloat("SALDO"));
        int id = rs.getInt("REIZIGERID");
        ov.setReizigerId(id);
        ReizigerOracleDaoImpl reiziger = new ReizigerOracleDaoImpl();
        Reiziger eigenaar = reiziger.findById(id);
        ov.setEigenaar(eigenaar);
        return ov;
    }
}