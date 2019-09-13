//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//
//import java.sql.Timestamp;
//import java.util.Date;
//import java.text.*;
//
//import static org.junit.Assert.*;
//
//public class MonsterTest {
//
////    @Before
////    public void setUp() throws Exception {
////    }
////
////    @After
////    public void tearDown() throws Exception {
//Monster testMonster = new Monster("Bubbles",1);
////    }
//    @Rule
//    public DatabaseRule database = new DatabaseRule();
//
//    @Test
//    public void Monster_instiantaitesCorrectly_true(){
//        Monster testMonster = new Monster("Bubbles",1);
//        assertEquals(true,testMonster instanceof Monster);
//    }
//    @Test
//    public  void Monster_instantiatesWithNAme_String(){
//        Monster testMonster = new Monster("Bubbles",1);
//        assertEquals("Bubbles",testMonster.getName());
//    }
//    @Test
//    public void Monster_instantiatesWithPersonId(){
//        Monster testMonster = new Monster("bubbles",1);
//        assertEquals(1,testMonster.getPersonId());
//    }
//    @Test
//    public void equals_returnsTrueIfNameAndPersonIdAreSame(){
//        Monster testMonster = new Monster("bubbles",1);
//        Monster anotherTestMonster = new Monster("bubbles",1);
//        assertTrue(testMonster.equals(anotherTestMonster));
//    }
//    @Test
//    public void save_returnTrueIfDescriptionAreTheSame(){
//        Monster testMonster = new Monster("bubbles",1);
//        testMonster.save();
//        assertTrue(Monster.all().get(0).equals(testMonster));
//    }
//    @Test
//    public void save_assignsIdMonster(){
//        Monster testMonster = new Monster("bubbles",1);
//        testMonster.save();
//        Monster savedMonster = Monster.all().get(0);
//        assertEquals(savedMonster.getId(),testMonster.getId());
//    }
//    // all() gets the number of the added or saved entries in the database
//    @Test
//    public void all_returnsAllInstancesOfMonster_true(){
//        Monster firstMonster = new Monster("bubbles",1);
//        firstMonster.save();
//        Monster secondMonster = new Monster("squd",1);
//        secondMonster.save();
//        assertEquals(true,Monster.all().get(0).equals(firstMonster));
//        assertEquals(true,Monster.all().get(1).equals(secondMonster));
//    }
//    // returns Monsters with same id
//
//    @Test
//    public void find_returnsMonsterWithSameId_secondMonster() {
//        Monster firstMonster = new Monster("Bubbles", 1);
//        firstMonster.save();
//        Monster secondMonster = new Monster("Spud", 3);
//        secondMonster.save();
//        assertEquals(Monster.find(secondMonster.getId()), secondMonster);
//    }
//    //associating  a person with many monsters
//    //this is where a person could have many monsters.
//    @Test
//    public void save_savesPersonIdIntoDB_true() {
//        Person testPerson = new Person("Henry", "henry@henry.com");
//        testPerson.save();
//        Monster testMonster = new Monster("Bubbles", testPerson.getId());
//        testMonster.save();
//        Monster savedMonster = Monster.find(testMonster.getId());
//        assertEquals(savedMonster.getPersonId(), testPerson.getId());
//    }
//    // at the start,each monster has a play level of half.
//    @Test
//    public void monster_instantiatesMonsterWithHalFullPlayLevel(){
//        Monster testMonster = new Monster("bubbles",1);
//        assertEquals(testMonster.getPlayLevel(),(Monster.MAX_PLAY_LEVEL/2));
//    }
//    // monster will have half sleep level
//    @Test
//    public void monster_instantiatesWithHalfFillSleepLevel(){
//        Monster testMonster = new Monster("Bubbles",1);
//        assertEquals(testMonster.getSleepLevel(),(Monster.MAX_SLEEP_LEVEL/2));
//    }
//    // monster will have a half food level at the start
//    @Test
//    public void monster_instantiatesMOnasterWithHalfFullFoodLevel(){
//        Monster testMonster = new Monster("Bubbles",1);
//        assertEquals(testMonster.getFoodLevel(),(Monster.MAX_FOOD_LEVEL/2));
//    }
//    //checking the status of the pet for minimum values
//    @Test
//    public void isAlive_confirmsMonsterIsAliveIfAllLevelsAboveMinimum_true(){
//        Monster testMonster = new Monster("Bubbles",1);
//        assertEquals(testMonster.isAlive(),true);
//    }
//    // depleting values decrease by 1 everytime the programs runs
//    @Test
//    public void depletesLevels_reducesAllLevels(){
//        Monster testMonster = new Monster("Bubbles",1);
//        testMonster.depleteLevels();
//        assertEquals(testMonster.getFoodLevel(),(Monster.MAX_FOOD_LEVEL/2)-1);
//        assertEquals(testMonster.getSleepLevel(), (Monster.MAX_SLEEP_LEVEL / 2) - 1);
//        assertEquals(testMonster.getPlayLevel(), (Monster.MAX_PLAY_LEVEL / 2) - 1);
//    }
//    //checking if monster is dead when level reach minimum using a loop method
//    @Test
//    public void isAlive_recognizesMOnsterIsDeadWhenLevelsReachMinimum_false(){
//        Monster testMonster = new Monster("Bubbles",1);
//        for(int i = Monster.MIN_ALL_LEVELS; i<=Monster.MAX_PLAY_LEVEL;i++){
//            testMonster.depleteLevels();
//        }
//        assertEquals(testMonster.isAlive(),false);
//    }
//    //  allow users increase their levels when they interact with their pets
//    // check if playlevel has increases beyond a certain value
//    @Test
//    public void play_increasesMonsterPlayLevel(){
//        Monster testMonster = new Monster("Bubbles",1);
//        testMonster.play();
//        assertTrue(testMonster.getPlayLevel() >(Monster.MAX_PLAY_LEVEL/2));
//    }
//    // checks if sleep increases past the MAX_SLEEP_LEVEL
//    @Test
//    public void sleep_increasesMonstersSleepLevel(){
//        Monster testMonster = new Monster("Bubbles",1);
//        testMonster.sleep();
//        assertTrue(testMonster.getSleepLevel() > (Monster.MAX_SLEEP_LEVEL/2));
//    }
//    // checks if fod level increases past a certain level
//    @Test
//    public void feed_increasesMonsterFoodLevel(){
//        Monster testMonster = new Monster("Bubbles",1);
//        testMonster.feed();
//        assertTrue(testMonster.getFoodLevel() > (Monster.MAX_FOOD_LEVEL/2));
//
//    }
//    // anticipating errors.
//    // if the monster if fed alot of food,or sleep alot, or play alot
//    @Test
//    public void monster_foodLevelCannotGoBeyondMaxValue(){
//        Monster testMonster = new Monster("Bubbles",1);
//        for( int i = Monster.MIN_ALL_LEVELS ; i <= (Monster.MAX_FOOD_LEVEL );i++) {
//            try {
//                testMonster.feed();
//            } catch (UnsupportedOperationException exception) {
//            }
//        }
//        assertTrue(testMonster.getFoodLevel() <= Monster.MAX_FOOD_LEVEL);
//    }
//    // testing exceptions.
//    @Test(expected = UnsupportedOperationException.class)
//    public void feed_throwsExceptionIfFoodLevelIsAtMaxValue(){
//        Monster testMonster = new Monster("Bubbles",1);
//        for(int i = Monster.MIN_ALL_LEVELS; i <=(Monster.MAX_FOOD_LEVEL);i++){
//            testMonster.feed();
//        }
//    }
//
//
//    @Test
//    public void monster_playLevelCannotGoBeyondMaxValue(){
//        Monster testMonster = new Monster("Bubbles",1);
//        for (int i = Monster.MIN_ALL_LEVELS;i<= (Monster.MAX_PLAY_LEVEL);i++){
//            try{
//                testMonster.play();
//            }catch (UnsupportedOperationException exception){}
//        }//we catch the exception in the tests.
//        assertTrue(testMonster.getPlayLevel() <= Monster.MAX_PLAY_LEVEL);
//    }
//
//    // asserting play method throws an exception
//    @Test(expected = UnsupportedOperationException.class)
//    public void play_throwsExceptionIfPlayLevelIsAtMaxValue(){
//        Monster testMonster = new Monster("Bubbles",1);
//        for(int i = Monster.MIN_ALL_LEVELS; i<= (Monster.MAX_PLAY_LEVEL);i++){
//            testMonster.play();
//        }
//    }
//
//    // throwing exceptions for sleep if sleeplevel exceed the max_sleep_level
//    @Test
//    public void monster_sleepLevelCannotExceedMaxSleepLevel(){
//        Monster testMonster = new Monster("Bubbles",1);
//        for(int i = Monster.MIN_ALL_LEVELS;i<= (Monster.MAX_SLEEP_LEVEL);i++){
//           try{
//               testMonster.sleep();
//           }catch (UnsupportedOperationException exception){}
//        }
//        assertTrue(testMonster.getSleepLevel() <= Monster.MAX_SLEEP_LEVEL);
//    }
//    @Test(expected = UnsupportedOperationException.class)
//    public void sleep_throwsExceptionIfSleepLevelIsAtMaxLevelValue(){
//        Monster testMonster = new Monster("Bubbles",1);
//        for(int i = Monster.MIN_ALL_LEVELS; i <= (Monster.MAX_SLEEP_LEVEL);i++){
//            testMonster.sleep();
//        }
//    }
//    // assert when we create a monster(birthday);
//    @Test
//    public void save_recordsTimeOfCreationInDatabase(){
//        Monster testMonster = new Monster("Bubbles",1);
//        testMonster.save();
//        Timestamp savedMonsterBirthday = Monster.find(testMonster.getId()).getBirthday();
//        Timestamp rightNow = new Timestamp(new Date().getTime());
//        assertEquals(rightNow.getDay(),savedMonsterBirthday.getDay());
//    }
//    // assertins for last time to sleep
//    @Test
//    public void sleep_recordsTimeLastSleptInDatabase(){
//        testMonster.save();
//        testMonster.sleep();
//        Timestamp savedMonsterLastSlept=  Monster.find(testMonster.getId()).getLastSlept();
//        Timestamp rightNow =new Timestamp(new Date().getTime());
//        assertEquals(DateFormat.getDateTimeInstance().format(rightNow),DateFormat.getDateTimeInstance().format(savedMonsterLastSlept));
//    }
//    // asserts the last time for feeding
//    @Test
//    public void feed_recordsTheLastTimeAteInDatabase(){
//        testMonster.save();
//        testMonster.feed();
//        Timestamp savedMonsterLastAte = Monster.find(testMonster.getId()).getLastAte();
//        Timestamp rightNow = new Timestamp(new Date().getTime());
//        assertEquals(DateFormat.getDateTimeInstance().format(rightNow),DateFormat.getDateTimeInstance().format(savedMonsterLastAte));
//    }
//    // asserts the last time tp play
//    @Test
//    public void play_recordsTimeLastPlayedInDatabse(){
//        testMonster.save();
//        testMonster.play();
//        Timestamp savedMonsterPlayed = Monster.find(testMonster.getId()).getLastPlayed();
//        Timestamp rightNow = new Timestamp(new Date().getTime());
//        assertEquals(DateFormat.getDateTimeInstance().format(rightNow),DateFormat.getDateTimeInstance().format(savedMonsterPlayed));
//    }
//
//    //intergrating basic timer in the application
//    // assetains that beginning the timer begins lowering monster levels.
//    @Test
//    public void timer_executeDepleteLevelsMethods(){
//        int firstPlayLevel = testMonster.getPlayLevel();
//        testMonster.startTimer();
//        try{
//            Thread.sleep(10000);
//        }catch (InterruptedException exception){}
//        int secondPlayLevel = testMonster.getPlayLevel();
//        assertTrue(firstPlayLevel>secondPlayLevel);
//    }
//    // test that the rimer j=haltd whe one of the monaster level reaches 0
//    @Test
//    public void timer_haltsAfterMonsterDies(){
//        testMonster.startTimer();
//        try{
//            Thread.sleep(10000);
//        }catch (InterruptedException exeception){}
//        assertFalse(testMonster.isAlive());
//        assertTrue(testMonster.getFoodLevel() >=0);
//    }
//}