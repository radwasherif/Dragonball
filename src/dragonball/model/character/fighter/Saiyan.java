package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.exceptions.NotEnoughKiException;

public class Saiyan extends PlayableFighter {
	private boolean transformed; 
	public Saiyan(String string) throws NotEnoughKiException {
		super(string, 1000, 150, 100, 5, 3,
				null, null);
	}
	
   
	

	public Saiyan (String name, int level, int xp, int targetXp, int maxHealthPoints,
			int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks) throws NotEnoughKiException {
		super (name, level, xp, targetXp, maxHealthPoints,blastDamage, physicalDamage, abilityPoints, maxKi, maxStamina, superAttacks, ultimateAttacks); 
	}
	
	
	public boolean isTransformed() {return transformed; }
	public void setTransformed(boolean transformed) {this.transformed = transformed; }




	@Override
	public void onAttackerTurn() throws NotEnoughKiException {
		setStamina(getStamina() + 1); 
	    if (getStamina() > getMaxStamina()) 
	    	setStamina(getMaxStamina()); 	
	    
	    if(isTransformed()) {
	    	setKi(getKi()-1); 
	    	if(getKi() <= 0) {
	    		setKi(0); 
	    		setTransformed(false); 
	    		setStamina(0); 
	    	}
	    }
	}




	@Override
	public void onDefenderTurn() throws NotEnoughKiException {
		setStamina(getStamina() + 1); 
	    if (getStamina() > getMaxStamina()) 
	    	setStamina(getMaxStamina()); 
	    
	    
	    if(isTransformed()) {
	    	setKi(getKi()-1); 
	    	if(getKi() <= 0) {
	    		setKi(0); 
	    		setTransformed(false); 
	    		setStamina(0); 
	    	}
	    }
	}
	
    
}
