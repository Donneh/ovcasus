import java.util.List;

public interface AdresDao {
    Adres save(Adres adres);
    Adres update(Adres adres);
    boolean delete(Adres adres);
    List<Adres> findAll();
    List<Adres> findByReiziger(int reizigerId);
}
