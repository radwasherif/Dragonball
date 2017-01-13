package dragonball.view;

import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;

public class Constants {
   static final int WIDTH = 1370; 
   static final int HEIGHT = 700; 
   
   
  public static String getFighterRace(PlayableFighter fighter) {
    if (fighter instanceof Saiyan ) {
      return "saiyan"; 
    } else if (fighter instanceof Majin) 
      return "majin"; 
    else if (fighter instanceof Namekian) 
      return "namekian"; 
    else if (fighter instanceof Frieza) 
      return "frieza"; 
    else 
     return "earthling"; 
    
  }
  
   
}
