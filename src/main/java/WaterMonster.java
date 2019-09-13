import java.sql.Timestamp;
import java.util.List;
import java.util.Timer;
import org.sql2o.*;
public class WaterMonster extends Monster {
    private int waterLevel;
    public Timestamp lastWater;
    public static final int MAX_WATER_LEVEL=8;
    public static final String DATABASE_TYPE ="water";
  public WaterMonster(String name,int personId){
      this.name = name;
      this.personId = personId;
      playLevel = MAX_PLAY_LEVEL / 2;
      sleepLevel = MAX_SLEEP_LEVEL / 2;
      foodLevel = MAX_FOOD_LEVEL / 2;
      waterLevel =MAX_WATER_LEVEL/2;
      timer = new Timer();
      type= DATABASE_TYPE;
  }
    public static List<WaterMonster> all(){
        String sql ="SELECT * FROM monsters WHERE type ='water';";// the 'type = water' makes sure that the specific class water return the specific type of the monster
        try(Connection con=DB.sql2o.open()){

            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(WaterMonster.class);
        }
    }

    public static WaterMonster find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM monsters where id=:id";
            WaterMonster monster = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(WaterMonster.class);
            return monster;
        }
    }
    public int getWaterLevel(){
      return waterLevel;
    }
    // implementing the functionality of water() increasing the water level and the exception incase waterlevel exceeeds Maximum water level
    public int water(){
      if(waterLevel >=MAX_WATER_LEVEL){
          throw new UnsupportedOperationException("You cannot water the monster anymore");
      }try(Connection con =DB.sql2o.open()){
          String sql ="UPDATE monsters SET lastWater  = now() WHERE id =:id";
          con.createQuery(sql)
                  .addParameter("id",id)
                    .executeUpdate();
        }
     return waterLevel++;
    }

    public Timestamp getLastWater(){
      return lastWater;
    }
//levels depleting
    @Override
    public void depleteLevels(){
      if(isAlive()){
          playLevel--;
          foodLevel--;
          waterLevel--;
          sleepLevel--;
      }
    }
    @Override
    public boolean isAlive(){
      if(foodLevel<=MIN_ALL_LEVELS || playLevel<=MIN_ALL_LEVELS|| sleepLevel<=MIN_ALL_LEVELS||waterLevel<=MIN_ALL_LEVELS){
          return false;

      }
      return true;
    }
}
