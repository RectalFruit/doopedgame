import java.util.*;

class Map{
   private String[][] map;
   private String name;
   int xPos; int yPos;
   int xExit; int yExit;
   int xEntrance; int yEntrance;
   
   
   //constructors. Might add new constructor with an exit and entrance. for now they are the same.
   public Map(String name, int row, int col, int xEx, int yEx){
      this.name = name;
      map = new String[row][col];
      xExit = xEx;
      yExit = yEx;
      xEntrance = xEx;
      yEntrance = yEx;
      xPos = xEx;
      yPos = yEx;
      
      for(int i = 0; i < map.length; i++){
         for(int j = 0; j < map[i].length; j++){
            map[i][j] = "[ ]";
         }
      }
      map[yEntrance][xEntrance] = "[E]";
      map[yExit][xExit] = "[E]";
   }
   
   public void display(){
      for(int i = 0; i < map.length; i++){
         for(int j = 0; j < map[i].length; j++){
            System.out.print(map[i][j]);
            if(i == 0 && j == map[i].length-1)
               System.out.print("\t" + getName());
         }
         System.out.println();
      }
   }
   
   public void move(WorldMap worldMap, Player player, Map dMap){
      Scanner user = new Scanner(System.in);
      char input = 'x';
      int count = 0;
      while(count < 1){
         display();
         count++;
      }
      
     while(input != 'Q'){
        map[yExit][xExit] = "[E]";
        System.out.println("Which direction would you like to move?(North, South, East, West, Menu)");
        input = user.next().toUpperCase().charAt(0);
           
        switch(input){
           case 'N':
              if(yPos != 0){
                 map[yPos][xPos] = "[ ]";
                 yPos -= 1;
                 map[yPos][xPos] = "[P]";
              }  
              else
                 System.out.println("You cannot move farther North");
              break;
           case 'S':
              if(yPos != map.length-1){
                 map[yPos][xPos] = "[ ]";
                 yPos += 1;
                 map[yPos][xPos] = "[P]";
              }
              else
                 System.out.println("You cannot move farther South");
              break;
           case 'E':
              if(xPos != map[yPos].length-1){
                 map[yPos][xPos] = "[ ]";
                 xPos += 1;
                 map[yPos][xPos] = "[P]";
              }
              else
                 System.out.println("You cannot move farther East");
              break;
           case 'W':
              if(xPos != 0){
                 map[yPos][xPos] = "[ ]";
                 xPos -= 1;
                 map[yPos][xPos] = "[P]";
              }
              else
                System.out.println("You cannot move farther West");
              break;
           case 'M':
              PlayerMenu.open(player, dMap);
              System.out.println();
              break;
           case 'Q':
              input = 'X';
           default:
              System.out.println("Invalid input.");
         }
         
         map[yExit][xExit] = "[E]";
         display();
         chanceEncounter(player);
          
         if(xPos == xExit && yPos == yExit){
             System.out.println("You are at an exit. Would you like to leave?");
             input = user.next().toUpperCase().charAt(0);
             switch(input){
               case 'Y':
                  System.out.println("You exit the area.");
                  input = 'Q';
                  worldMap.worldMove(worldMap, player);
                  break;
               case 'N':
                  System.out.println("You decide to stay.");
                  break;
               case 'Q':
               default:
                  System.out.println("Invalid input.");
                  input = 'X';
             }
         }      
      }
   }
   
   public void chanceEncounter(Player player){
      Random rand = new Random();
      Enemy enemy;
      int chance = rand.nextInt(10);
      if(chance <= 1){
         enemy = EnemySpawner.spawn();
         Battle.battle(player, enemy);
      }   
   } 
 
   //getters, setters
   public String getName() {return name;}
}
   