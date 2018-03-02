import java.lang.Math;
import java.util.Random;

public class Battle {

  private final int D1_SIZE = 6;
  
  private Character p1;
  private Character p2;
  private boolean debug = true; // For debugging - this will be false during actual gameplay

  public Battle() {
      
  }
  
  public Battle(Character playerOne, Character playerTwo) {
  
    System.out.println("Loading characters...");
    
    if (Math.random() > .5) {
        p1 = playerOne;
        p2 = playerTwo;    
    } else {
        p2 = playerOne;
        p1 = playerTwo;        
    }
    
    System.out.println("\n\n");
    System.out.println("Player 1 is " + p1.getName());
    System.out.println("Player 2 is " + p2.getName());
    System.out.println("\nMay the odds be forever in your favour.");
   
  }
  
  public Character[] superSmash() {
   
    Character[] players = new Character[2];
    
    int attack;
    int defend;
    int diff;
    
    while (true) {
        System.out.println();
        System.out.println("Exchanging information...");
        System.out.println();
        
        p1.loadOppInfo(p2.getMyInfo());
        System.out.println();
        p2.loadOppInfo(p1.getMyInfo());
        System.out.println();
        
        // Conduct a special check and see if specialEffect() will be called on each player
        callSpecial(p1);
        callSpecial(p2);
    
        // Have p1 attack p2 and see if p2 can defend
        attack = rollDice(Math.round(p1.attack()), D1_SIZE);
        defend = rollDice(Math.round(p2.defend()), D1_SIZE);
        
        // Result of attack/defend
        diff = attack - defend;        
        
        if (debug) {
            System.out.println("Player 1 attacks with " + attack + " and player 2 defends with " + defend + " for a difference of " + diff);
        }

        // If p1's attack was successful, damage p2
        if (attack >= 0) {
            
            p2.setCurrentHealth(p2.getCurrentHealth() - diff);
            
        }
        
        // Check to see if p2 died :(
        if (p2.getCurrentHealth() <= 0) {
            
            players[0] = p1;
            players[1] = p2;
            return players;
            
        }
        
        // Have p2 attack p1 and see if p1 can defend
        attack = rollDice(Math.round(p2.attack()), D1_SIZE);
        defend = rollDice(Math.round(p1.defend()), D1_SIZE);

        // Results of attack/defend
        diff = attack - defend;

        if (debug) {
            System.out.println("Player 2 attacks with " + attack + " and player 1 defends with " + defend + " for a difference of " + diff);
        }
        
        // if p2's attack was successful, damage p1
        if (attack >= 0) {
            
            p1.setCurrentHealth(p1.getCurrentHealth() - diff);
            
        }   
        
        // Check to see if p1 died :(
        if (p1.getCurrentHealth() <= 0) {
            players[0] = p2;
            players[1] = p1;
            return players;        
        }
    }

  
  }
  
  public int rollDice(double dice, int sides) {
      
      int total = 0;
      
      for (int i = 0; i < dice; i++) {
          total += rollDie(D1_SIZE);
      }
      
      if (debug) {
          System.out.println("DEBUG: rollDice returned " + total);
      }
      
      return total;
      
  }
  
  public int rollDie(int sides) {
      Random random = new Random();
      return random.nextInt(sides) + 1;
  }
  
  public void callSpecial(Character player) {
      
      if (debug) {
          System.out.println("DEBUG: Attempting to get special of " + player.getName());
      }
      
      int special = player.getSpecial();
      double specialCheck =Math.random() * 10 + 1;
      double modifier = 1.3;
      
      if ((modifier * specialCheck) >= special) {
          
        if (debug) {
            System.out.println("DEBUG: specialEffect() has been called for  " + player.getName());   
        }   
              
          player.specialEffect();
          
          if (player.getSpecial() - 1 > 0) {
            player.setSpecial(player.getSpecial()-1);
          }
          
        if (debug) {
            System.out.println("DEBUG: the special of " + player.getName() + " is now " + player.getSpecial());
        }
          
      }
      
      
  }
  
  
}
