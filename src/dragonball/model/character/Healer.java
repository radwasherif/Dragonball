package dragonball.model.character;

import dragonball.model.character.fighter.Fighter;
import dragonball.model.exceptions.NotEnoughKiException;

public class Healer extends NonFighter {
	private int possibleHPrecovery; 
	
	public Healer (String name, int possibleHPrecovery ) {
		super(name); 
		this.possibleHPrecovery = possibleHPrecovery; 
	}

	public void helpFighter(Fighter c) throws NotEnoughKiException {
		super.helpFighter(c);
		c.setHealthPoints(c.getHealthPoints()+ possibleHPrecovery);
	}
	 
	
	public int getPossibleHPrecovery() {
		return possibleHPrecovery;
	}
}
