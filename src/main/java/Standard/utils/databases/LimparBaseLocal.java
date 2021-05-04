package Standard.utils.databases;

public class LimparBaseLocal {

    GenericDao genericDao = new GenericDao();

    public void limparBaseLocal() {

        String database = "h2";
        String ambiente = "local";
        String sqlName = "queryH2DeletarTabelaCrm";

        genericDao.drop(database, ambiente, sqlName);
    }
}
