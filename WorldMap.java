import java.util.*;

class WorldMap{
   private Map[][] maps;
   private String[][] worldMap;
   private int xPos; private int yPos;
   
   public WorldMap(int y, int x){
      maps = new Map[y][x];
      worldMap = new String[y][x];
      xPos = 0;
      yPos = 0;
   }
   
   public void populateMaps(){
      //populate 'maps' here
      maps[0][0] = new Map("ElvenWood", 5, 5, 0, 4);
      maps[0][1] = new Map("Acacia Village", 6, 4, 0, 2);
      maps[1][0] = new Map("The Pits", 10, 10, 0, 9);
      maps[1][1] = new Map("Crystaline Caverns", 15, 15, 14, 0);
      //populate worldMap with first letters of map names
      for(int i = 0; i < worldMap.length; i++){
         for(int j = 0; j < worldMap[i].length; j++){
            worldMap[i][j] = "[" + maps[i][j].getName().toUpperCase().charAt(0) + "]";   
         }
      }
   }
   
   public void display(){
      for(int i = 0; i < worldMap.length; i++){
         for(int j = 0; j < worldMap[i].length; j++){
            System.out.print(worldMap[i][j]);
            if(i == 0 && j == worldMap[i].length-1)
               System.out.print("\tWorld Map");
         }
         System.out.println();
      }
   }
   
   public void worldMove(WorldMap wMap, Player player){
      
      Scanner user = new Scanner(System.in);
      String input = "";
      
      while(!input.contains("QUIT") ){
         String currentLoc = maps[yPos][xPos].getName();
         worldMap[yPos][xPos] = "[P]";
         display();
         System.out.println("You see the entrance to " + currentLoc + ". Would you like to enter, move North, South, East or West?");
         input = user.next().toUpperCase().substring(0, 2);
         switch(input){
            case "EN":
               System.out.println("You decide to enter " + currentLoc + ".");
               maps[yPos][xPos].move(wMap, player, maps[yPos][xPos]);
               input = "QUIT";
               break;
            case "NO":
               if(yPos != 0){
                  worldMap[yPos][xPos] = "[" + currentLoc.charAt(0) + "]";
                  yPos--;
               }
               else
                  System.out.println("Cannot move farther North");
               break;
            case "SO":
               if(yPos != worldMap.length-1){
                  worldMap[yPos][xPos] = "[" + currentLoc.charAt(0) + "]";
                  yPos++;
               }
               else
                  System.out.println("Cannot move farther South");
               break;
            case "EA":
               if(xPos != worldMap[yPos].length-1){
                  worldMap[yPos][xPos] = "[" + currentLoc.charAt(0) + "]";
                  xPos++;
               }
               else
                  System.out.println("Cannot move farther East");
               break;
            case "WE":
               if(xPos != 0){
                  worldMap[yPos][xPos] = "[" + currentLoc.charAt(0) + "]";
                  xPos--;
               }
               else
                  System.out.println("Cannot move farther West");
               break;
            case "QU":
               System.out.println("Are you sure you want to quit?");
               input = user.next().toUpperCase().substring(0, 2);
               if(input.equals("YE") )
                  input = "QUIT";
               break;
            default:
               System.out.println("Invalid input.");
         }
      }
   }
}