package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.exceptions.NotEnoughKiException;

public class Earthling extends PlayableFighter {
	public Earthling(String string) throws NotEnoughKiException {
		super(string, 1250, 50, 50, 4, 4, new ArrayList<SuperAttack>(), new ArrayList<UltimateAttack>()); 

	}
	public Earthling (String name, int level, int xp, int targetXp, int maxHealthPoints,
			int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks) throws NotEnoughKiException {
		super(name, level, xp, targetXp, maxHealthPoints, blastDamage, physicalDamage, abilityPoints, maxKi, maxStamina, superAttacks, ultimateAttacks); 
		
	}
	 public Earthling  (ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks) throws NotEnoughKiException {
		 super("Earthling", 1250, 50, 50, 4, 4, superAttacks, ultimateAttacks); 
	 }
	@Override
	public void onAttackerTurn() throws NotEnoughKiException {
	    setStamina(getStamina() + 1); 
	    if (getStamina() > getMaxStamina()) 
	    	setStamina(getMaxStamina()); 
		
	    setKi(getKi() +1); 
	    if(getKi() > getMaxKi()) 
	    	setKi(getMaxKi()); 
	}
	@Override
	public void onDefenderTurn() {
		setStamina(getStamina() + 1); 
	    if (getStamina() > getMaxStamina()) 
	    	setStamina(getMaxStamina()); 
		
	}

	
}
