import java.util.List;

public interface ReizigerDao {


    List<Reiziger> findAll();
    List<Reiziger> findByGBdatum(String GBdatum);
    Reiziger save(Reiziger reiziger);
    Reiziger update(Reiziger origineel, Reiziger updatedReiziger);
    boolean delete(Reiziger reiziger);
    void closeConnection();
}
