import java.util.*;

class Battle{
   private static boolean fleeing = false;
   //constructors TBD
   
   //methods
   public static void battle(Player player, Enemy enemy){
      Scanner user = new Scanner(System.in);
      int input = -1;
      int enemyInput = -1;
      System.out.println(player.getName() + " has engaged " + enemy.getName() + " in battle!");
      
      while(enemy.isAlive() && player.isAlive() && !fleeing){
         enemyInput = enemyChoice(enemy);
         
         System.out.println("Attack, Defend, Inspect, Run(1, 2, 3, 4)");
s         
         switch(input){
            case 1:
               if(enemyInput == 2){
                  enemy.defend();
               }
               player.attack(enemy);
               break;
            case 2:
               player.defend();
               break;
            case 3:
               System.out.println(enemy);
               break;
            case 4:
               flee();
               break;
            case -99:
            default:
               System.out.println("Invalid action.");
         }
         
         if(enemy.isAlive() ){
            switch(enemyInput){
               case 1:
                  enemy.attack(player);
                  break;
               case 2:
                  //enemy.defend();
                  break;
               case 3:
                  //enemyFlee();
                  break;
            }
         }
         
         if(fleeing)
            System.out.println("You manage to escape.");
         else
            enemy.checkAlive(player);
      }
      fleeing = false;
   }
   
   public static void flee(){
      Random rand = new Random();
      int chance = rand.nextInt(100);
      if(chance >= 19){
         System.out.println("You are preparing to flee!");
         fleeing = true;
      }
      else{
         System.out.println("Cannot flee.");
         fleeing = false;
      }
   }
   
   public static int enemyChoice(Enemy enemy){
      Random rand = new Random();
      int chance = rand.nextInt(100);
      if(chance >=29){
         System.out.println(enemy.getName() + " is preparing to attack!");
         return 1;
      }
      else if(chance >= 9){
         System.out.println(enemy.getName() + " braces themself for damage.");
         return 2;
      }
      else if(chance >= 0){
         System.out.println(enemy.getName() + " is preparing to flee!");
         return 3;
      }
      return 1;
   }
}