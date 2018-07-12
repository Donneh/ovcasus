import java.util.List;

public interface OVChipkaartDao {

    OVChipkaart save(OVChipkaart ovChipkaart);
    OVChipkaart update(OVChipkaart ovChipkaart);
    boolean delete(OVChipkaart ovChipkaart);
    List<OVChipkaart> findByReiziger(int reizigerId);
    List<OVChipkaart> findAll();
}
