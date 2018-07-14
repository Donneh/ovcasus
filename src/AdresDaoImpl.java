import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdresDaoImpl extends OracleBaseDao implements AdresDao {

    private ReizigerOracleDaoImpl reizigerDAO = new ReizigerOracleDaoImpl();

    public Adres save(Adres adres) {
        try {
            String query =
                    "INSERT INTO ADRES " +
                    "(POSTCODE, HUISNUMMER, STRAAT, WOONPLAATS, REIZIGERID) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, adres.getPostcode());
            stmt.setString(2, adres.getHuisnummer());
            stmt.setString(3, adres.getStraat());
            stmt.setString(4, adres.getWoonplaats());
            stmt.setInt(5, adres.getId());

            ResultSet rs = stmt.executeQuery();

            if(rs.rowInserted()) {
                return buildAdresObject(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Adres update(Adres adres) {
        try {
            String query =
                    "UPDATE ADRES SET " +
                    "POSTCODE = ?, HUISNUMMER = ?, STRAAT = ?, WOONPLAATS = ? " +
                    "WHERE ADRESID = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, adres.getPostcode());
            stmt.setString(2, adres.getHuisnummer());
            stmt.setString(3, adres.getStraat());
            stmt.setString(4, adres.getWoonplaats());
            stmt.setInt(5, adres.getId());

            ResultSet rs = stmt.executeQuery();

            if (rs.rowUpdated()) {
                return findById(adres.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean delete(Adres adres) {
        try {
            String query = "DELETE FROM ADRES WHERE ADRESID = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, adres.getId());

            return stmt.executeQuery().rowDeleted();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Adres> findAll() {
        try {
            String query = "SELECT * FROM ADRES";
            Statement stmt = getConnection().createStatement();

            ResultSet rs = stmt.executeQuery(query);
            List<Adres> adressen = new ArrayList<>();

            while (rs.next()) {
                Adres adres = buildAdresObject(rs);
                adres.setReiziger(reizigerDAO.findById(rs.getInt("REIZIGERID")));
                adressen.add(adres);
            }

            return adressen;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Adres findById(int id) {
        try {
            String query = "SELECT * FROM ADRES WHERE ADRESID = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return buildAdresObject(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Adres> findByReiziger(int reizigerId) {
        try {
            String query = "SELECT * FROM ADRES WHERE REIZIGERID = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, reizigerId);

            ResultSet rs = stmt.executeQuery();
            List<Adres> adressen = new ArrayList<>();
            while (rs.next()) {
                adressen.add(buildAdresObject(rs));
            }

            return adressen;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Adres buildAdresObject(ResultSet rs) throws SQLException {
        Adres adres = new Adres();
        adres.setId(rs.getInt("ADRESID"));
        adres.setPostcode(rs.getString("POSTCODE"));
        adres.setHuisnummer(rs.getString("HUISNUMMER"));
        adres.setStraat(rs.getString("STRAAT"));
        adres.setWoonplaats(rs.getString("WOONPLAATS"));
        adres.setReizigerID(rs.getInt("REIZIGERID"));
        return adres;
    }
}
