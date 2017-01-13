package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.exceptions.NotEnoughKiException;

public class Majin extends PlayableFighter {

	public Majin(String name) throws NotEnoughKiException {
		super(name, 1500, 50, 50, 3, 6,
				new ArrayList<SuperAttack>(), new ArrayList<UltimateAttack>());
		// TODO Auto-generated constructor stub
	}
	

	public  Majin (String name, int level, int xp, int targetXp, int maxHealthPoints,
			int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks) throws NotEnoughKiException {
		super(name, level, xp, targetXp, maxHealthPoints, blastDamage, physicalDamage, abilityPoints, maxKi, maxStamina ,
				superAttacks, ultimateAttacks);
		
	}
	
	public Majin(ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) throws NotEnoughKiException {
		super("Majin", 1500, 50, 50, 3, 6,
				superAttacks, ultimateAttacks);
		// TODO Auto-generated co1nstructor stub
	}


	@Override
	public void onAttackerTurn() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onDefenderTurn() {
		setStamina(getStamina() + 1); 
	    if (getStamina() > getMaxStamina()) 
	    	setStamina(getMaxStamina()); 
		
	}

}
