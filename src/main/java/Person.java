
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.sql2o.*;

public class Person {
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Person person = (Person) o;
//        return Objects.equals(name, person.name) &&
//                Objects.equals(email, person.email);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
    @Override
    public boolean equals(Object otherPerson){
        if (!(otherPerson instanceof Person)){
            return false;
        }else{
            Person newPerson =(Person) otherPerson;
            return this.getName().equals(newPerson.getName()) &&
                    this.getEmail().equals(newPerson.getEmail());
        }
    }

        private String name;
        private String email;
        private int id;
    public Person( String name,String email){
        this.name =name;
        this.email= email;

    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public void save(){
        try(Connection con =DB.sql2o.open()){
            String sql ="INSERT INTO persons(name,email) VALUES (:name,:email)";
            // return  generated keys from the database;
            this.id = (int) con.createQuery(sql,true)

            .addParameter("name",this.name)
                    .addParameter("email",this.email)
                    .executeUpdate()
            .getKey();
        }
    }
    public static List<Person> all(){
        String sql ="SELECT * FROM persons";
        try(Connection con =DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Person.class);
        }
    }
    public int getId(){
        return id;
    }
    public static Person find(int id){
        try(Connection con =DB.sql2o.open()){
            String sql = "SELECT * FROM persons where id =:id";
            Person person = con.createQuery(sql)
            .addParameter("id",id)
                    .executeAndFetchFirst(Person.class);
            return person;
        }
    }
    // getMonsters gets the values of the with types of the monsters.
    // picks the type of the monsters inthe fire and water in the database
    public List<Object> getMonsters(){
        // returning the type of fire monsters
        List<Object> allMonsters= new ArrayList<Object>();
        try(Connection con  =DB.sql2o.open()){
            String sqlFire ="SELECT * FROM monsters WHERE personId =:id AND type ='fire';";
            List<FireMonster> fireMonsters = con.createQuery(sqlFire)
            .addParameter("id",this.id)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(FireMonster.class);
            allMonsters.addAll(fireMonsters);

            //returning the type of water monster in the database
            String sqlWater = "SELECT *FROM monsters WHERE personId =:id AND  type= 'water';";
            List<WaterMonster> waterMonsters = con.createQuery(sqlWater)
            .addParameter("id",this.id)
                    .throwOnMappingFailure(false)
                 .executeAndFetch(WaterMonster.class);
            allMonsters.addAll(waterMonsters);
        }
        return allMonsters;
    }

}
