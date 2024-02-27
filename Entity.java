import java.util.*;

class Entity{
   //instance variables, MORE TBD
   private String name;
   private int level;
   private int health;
   private int maxHealth;
   private int damage;
   private int defense;
   private boolean alive;
   private boolean defending = false;
   
   
   //base constructor
   public Entity(String nm, int lvl, int hp, int dam, int def){
      name = nm;
      level = lvl;
      health = hp;
      maxHealth = health;
      damage = dam;
      defense = def;
      alive = true;
   }
   
   //methods
   public String toString(){
      return "You see a level " + level + " " + name + ". Their health is " + health + "/" + maxHealth;
   }
   
   public void receiveHealth(int hp){
      health += hp;
      if(health > maxHealth)
         health = maxHealth;
   }
   
   public void attack(Entity defender){
      if(defender.isDefending() ){
         System.out.println(getName() + " attacks " + defender.getName() + " for " + (getDamage()-defender.getDefense() ) + " damage!");
      }
      else{
         System.out.println(getName() + " attacks " + defender.getName() + " for " + getDamage() + " damage!");
      }
      defender.takeDamage(getDamage());
   }
   
   public void defend(){
      setDefending(true);
   }
   
   public void takeDamage(int dam){
      if(isDefending() ){
         health -= (dam - defense);
         setDefending(false);
      } 
      else
         health -= dam;
      if(health <= 0){
         setAlive(false);
         System.out.println(name + " has died!");
      }
   }
   
   //getters, setters
   public String getName() {return name;}
   public int getLevel() {return level;}
   public void setLevel(int lvl) {level = lvl;}
   public int getHealth() {return health;}
   public void setHealth(int hp) {health = hp;}
   public int getMaxHealth() {return maxHealth;}
   public void setMaxHealth(int hp) {maxHealth = hp;}
   public int getDamage() {return damage;}
   public void setDamage(int dam) {damage = dam;}
   public int getDefense() {return defense;}
   public void setDefense(int def) {defense = def;}
   public boolean isAlive(){return alive;}
   public void setAlive(boolean set) {alive = set;}
   public boolean isDefending() {return defending;}
   public void setDefending(boolean x) {defending = x;} 
}

class Player extends Entity{
   private int experience = 0; //total experience of PC.
   
   public Player(String nm, int lvl, int hp, int dam, int def){
      super(nm, lvl, hp, dam, def);
   }
   
   //methods
   public String toString(){
      return "Name:       " + getName() + "\nLevel:      " + getLevel() + "\nHealth:     " + getHealth() + "/" + getMaxHealth() +
             "\nDamage:     " + getDamage() + "\nDefense:    " + getDefense() + "\nExperience: " + experience;  
   }
   
   public void receiveExp(int exp){
      experience += exp;
      levelUp(experience);
   }
   
   public void levelUp(int exp){
      int lvl = getLevel();
      
      if(exp >= lvl*100){
         System.out.print("\nYou have gained a level!\nNew level: " + (lvl+1) + "\nDamage increase by 1! \nDefense increase by 1!" +
                          "\nHealth increased by 50!\n");
         setLevel(getLevel()+1);
         setDamage(getDamage()+1);
         setDefense(getDefense()+1);
         setMaxHealth(getMaxHealth()+50);
         setHealth(getMaxHealth());
      }
   }
   
   //getters, setters
   public int getExp() {return experience;}
   public void setExp(int exp) {experience = exp;} //probably not needed, keeping just in case
}

class Enemy extends Entity{
   private int experience;
   
   public Enemy(String nm, int lvl, int hp, int dam, int def){
      super(nm, lvl, hp, dam, def);
      experience = 100*lvl;
   }
   
   public void giveExp(Player player){
      System.out.println(player.getName() + " receives " + experience + " experience.");
      player.receiveExp(experience);
   }
   
   public void checkAlive(Player player){
      if(!isAlive())
         giveExp(player);
   }
}

class EnemySpawner{
   private static Enemy enemy;
   public static Enemy spawn(){
      Random rand = new Random();
      int chance = rand.nextInt(100);
      
      if(chance >= 39)
         enemy = new Enemy("Bandit", 1, 50, 2, 1);
      else if(chance >= 19)
         enemy = new Enemy("Orc", 2, 100, 4, 2);4
         
      else if(chance >= 15)
         enemy = new Enemy("Wretched Mass", 3, 150, 5, 5);
      else
         enemy = new Enemy("Niggler", 5, 250, 8, 10);1
      return enemy;1
      
   }
}
