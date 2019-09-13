import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sql2o.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class PersonTest {
    @Rule
    public DatabaseRule database  = new DatabaseRule();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void person_instantiateCorrectly_true(){
        Person testPerson = new Person("Henry","[email protected]");
        assertEquals(true,testPerson instanceof Person);
    }
    @Test
    public void getName_personInstantiatesWithName_Henry(){
        Person testPerson = new Person("Henry","[email protected]");
        assertEquals("Henry",testPerson.getName());
    }
    @Test
    public void getEmail_personInstantiatesWithEmail_String() {
        Person testPerson = new Person("Henry", "[email protected] (Links to an external site.)");
        assertEquals("[email protected] (Links to an external site.)", testPerson.getEmail());

    }@Test
    public void equals_returnsTrueIfNameAndEmailAreSame_true(){
        Person firstPerson = new Person("Henry","[email protected]");
        Person otherPerson = new Person("Henry","[email protected]");
        assertTrue(firstPerson.equals(otherPerson));
    }
    @Test
    public void save_insertObjectsIntoDatabase_Person(){
        Person testPerson = new Person("Henry","[email protected");
        testPerson.save();
        assertTrue((Person.all().get(0).equals(testPerson)));
    }
    @Test
    public void all_returnsAllInstancesOfPerson_true(){
        Person firstPerson = new Person("Henry","henry@yahoo.com");
        firstPerson.save();
        Person secondPerson = new Person("Harriet","harriet@yahoo.com");
        secondPerson.save();
        assertEquals(true,Person.all().get(0).equals(firstPerson));
        assertEquals(true,Person.all().get(1).equals(secondPerson));
    }
    // save function should assign the object an id from the database.
    @Test
    public void save_assignsIdToObject(){
        Person testPerson = new Person("Henry","henry@yahoo.com");
        testPerson.save();
        Person savedPerson = Person.all().get(0);
        assertEquals(testPerson.getId(),savedPerson.getId());
    }
  // want to find person based on their id
  @Test
  public void find_returnsPersonWithSameId_secondPerson(){
      Person firstPerson = new Person("Henry","henry@yahoo.com");
      firstPerson.save();
      Person secondPerson = new Person("Harriet","harriet@gmaial.com");
      secondPerson.save();
      assertEquals(Person.find(secondPerson.getId()),secondPerson);
  }
  @Test
    public void getMonsters_retrievesAllMonstersFromDatabase_monsterList(){
        Person testPerson = new Person("Henry","Hnery@gmail.com");
        testPerson.save();
        FireMonster firstMonster = new FireMonster("Smokey", testPerson.getId());
        firstMonster.save();
        WaterMonster secondMonster = new WaterMonster("Drippy",testPerson.getId());
        secondMonster.save();
        Object[] monsters = new Object[]{
                firstMonster,secondMonster
        };
      assertTrue(testPerson.getMonsters().containsAll(Arrays.asList(monsters)));
  }

}