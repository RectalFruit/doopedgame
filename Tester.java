public class Tester{

//testing methods
   public static void entityTest(Player player, Enemy enemy){
      System.out.println(player);
      System.out.println(enemy);
      
      enemy.attack(player);
      
      System.out.println(player);
      enemy.setHealth(6);
      player.attack(enemy);
      
      enemy.checkAlive(player);
         
      System.out.println(player);
   }
   
   public static void mapTest(Map map, WorldMap worldMap, Player player){
      map.display();
      map.move(worldMap, player, map);
   }
   
   public static void worldMapTest(WorldMap worldMap, Player player){
      worldMap.populateMaps();
      worldMap.worldMove(worldMap, player);
   }
   
//MAIN METHOD   
   public static void main(String[] args){
      Player player = new Player("Adam", 1, 100, 6, 2);
      Enemy enemy = new Enemy("Bandit", 1, 50, 2, 2);
      Map map = new Map("ElvenWood", 5, 5, 0, 4);
      WorldMap worldMap = new WorldMap(2, 2);
      //entityTest(player, enemy);
      //mapTest(map, worldMap, player);  
      //worldMapTest(worldMap, player);
      //Battle.battle(player, enemy);
      //PlayerMenu.open(player);
   }
}