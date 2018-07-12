public class Main {

    public static void main(String[] args) {
        ReizigerOracleDaoImpl rzdao = new ReizigerOracleDaoImpl();
        for (Reiziger reiziger : rzdao.findAll()) {
            System.out.println(reiziger);
        }
    }
}
