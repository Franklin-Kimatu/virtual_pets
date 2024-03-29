import org.junit.rules.ExternalResource;
import org.sql2o.*;


public class DatabaseRule extends ExternalResource {
    @Override
    protected void before(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/virtual_pets_test", "franklin","12345");

    }
    @Override
    protected void after (){
        try(Connection con =DB.sql2o.open()){
            String deletePersonQuery ="DELETE FROM persons*;";
            String deleteMonsterQuery ="DELETE FROM monsters*";;
            con.createQuery(deletePersonQuery).executeUpdate();
            con.createQuery(deleteMonsterQuery).executeUpdate();
        }
    }
}
