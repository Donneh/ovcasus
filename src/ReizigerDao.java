import java.sql.Date;
import java.util.List;

public interface ReizigerDao {


    List<Reiziger> findAll();
    List<Reiziger> findByGBdatum(Date GBdatum);
    Reiziger save(Reiziger reiziger);
    Reiziger update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    void closeConnection();
}
