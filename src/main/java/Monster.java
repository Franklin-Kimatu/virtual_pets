import org.sql2o.*;

import java.sql.Timestamp;
import java.util.*;
import java.text.*;

public abstract  class Monster {

     String name;
     int personId;
     int id;
     int foodLevel;
     int sleepLevel;
     int playLevel;
     Timestamp birthday;
     Timestamp lastate;
     Timestamp lastslept;
     Timestamp lastplayed;
     Timer timer;
     public String type;

    public static final int  MAX_FOOD_LEVEL =3;
    public static final int MAX_SLEEP_LEVEL =8;
    public static final int MAX_PLAY_LEVEL=12;
    public static final int MIN_ALL_LEVELS =0;


//    public Monster(String name, int personId){
//        this.name = name;
//        this.personId= personId;
//        this.playLevel =MAX_PLAY_LEVEL/2;
//        this.sleepLevel =MAX_SLEEP_LEVEL/2;
//        this.foodLevel =MAX_FOOD_LEVEL/2;
//        timer = new Timer();
//    }
    @Override
    public boolean equals(Object otherMonster){
        if(!(otherMonster instanceof Monster)){
            return false;
        }else{
            Monster newMonster =(Monster)otherMonster;
            return this.getName().equals(newMonster.getName()) &&
                    this.getPersonId() == newMonster.getPersonId();
        }
    }

    public void save(){
        try(Connection con =DB.sql2o.open()){
            String sql ="INSERT INTO Monsters (name,personid,birthday,type) VALUES (:name,:personId,now(),:type)";
            this.id =(int)con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("personId",this.personId)
                    .addParameter("type",this.type)//this makes sure that the type of that monster is saved in the database when the data is saved.
                    .executeUpdate()
                    .getKey();
        }
    }
    //this ascertains that the instances in the database are being added
//    public static List<Monster> all(){
//        String sql ="SELECT * FROM Monsters";
//        try(Connection con=DB.sql2o.open()){
//            return con.createQuery(sql).executeAndFetch(Monster.class);
//        }
//    }

//    public static Monster find(int id) {
//        try(Connection con = DB.sql2o.open()) {
//            String sql = "SELECT * FROM Monsters where id=:id";
//            Monster Monster = con.createQuery(sql)
//                    .addParameter("id", id)
//                    .executeAndFetchFirst(Monster.class);
//            return Monster;
//        }
//    }



    public String getName(){
        return name;
    }
    public int getPersonId(){
        return personId;
    }
    public int getId(){
        return id;
    }
    public int getPlayLevel(){
        return playLevel;
    }
    public int getSleepLevel(){
        return sleepLevel;
    }
    public int getFoodLevel(){
        return foodLevel;
    }
    //functionality for checking if Monster is alive
    public boolean isAlive(){
        if(foodLevel <= MIN_ALL_LEVELS || sleepLevel<= MIN_ALL_LEVELS || sleepLevel <= MIN_ALL_LEVELS){
            return false;
        }
        return true;
    }
    // functionality for depleting values when the program runs
    public void depleteLevels() {
        if (isAlive()) {
            playLevel--;
            sleepLevel--;
            foodLevel--;
        }
    }
    // increases live when a user plays with the pet
    public void play(){
        if(playLevel >= MAX_PLAY_LEVEL){
            throw new UnsupportedOperationException("You cannot play with the pet anymore:");
        } try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE Monsters SET lastplayed =now() WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
        playLevel++;
    }
    //increases life when a user makes the pet sleep
    public void sleep(){
        if(sleepLevel >= MAX_SLEEP_LEVEL){
            throw new UnsupportedOperationException("You cannot make your Monster sleep anymore!");
        }
        try(Connection con =DB.sql2o.open()){
            String sql ="UPDATE Monsters SET lastslept = now() WHERE id =:id";
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
        sleepLevel++;
    }

    // throwing the exception  that is to be tested
    //we include a custom error message.
    // adding logic that throws exception for playing too much
    public void feed(){
        if(foodLevel >= MAX_FOOD_LEVEL){
            throw new UnsupportedOperationException("You cannot feed your Monster anymore!");
        }try(Connection con =DB.sql2o.open()){
            String sql ="UPDATE Monsters SET lastate = now() WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }

        foodLevel++;
    }

    // this starts the timer for the program(functionality)
    public void startTimer(){
        Monster currentMonster = this;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(!currentMonster.isAlive()){
                    cancel();

                }
                depleteLevels();
            }
        };
        this.timer.schedule(timerTask,0,10000);
    }
    // functionality for birthday timestamp
    public Timestamp getBirthday(){
        return birthday;
    }
    //functionality for getting last slept time
    public Timestamp getLastSlept(){
        return lastslept;
    }
    public Timestamp getLastAte(){
        return lastate;
    }
    public Timestamp getLastPlayed(){
        return lastplayed;
    }

}
