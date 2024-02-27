import java.util.*;

class PlayerMenu{
   //variables and constructors TBD
   
   public static void open(Player player, Map map){
      Random rand = new Random();
      Scanner user = new Scanner(System.in);
      Enemy enemy;
      int chance = -1;
      int input = -1;
      
      while(input != 0){
         System.out.println("Battle, Rest, View Inventory, Inspect Yourself, Close Menu(1, 2, 3, 4 0)");
         input = user.nextInt();
         
         switch(input){
            case 1:
               System.out.println("You search for a battle.");
               chance = rand.nextInt(10);
               if(chance <= 3){
                  enemy = EnemySpawner.spawn();
                  Battle.battle(player, enemy);
               }
               else
                  System.out.println("You found nothing.");
               break;
            case 2:
               System.out.println("You rest and recover your health.");
               player.setHealth(player.getMaxHealth() );
               break;
            case 3:
               System.out.println("TBI");
               break;
            case 4:
               System.out.println(player);
               break;
            case 0:
               break;
            default:
               System.out.println("Invalid input.");
         }
      }
   }
}