package dragonball.model.character;

import dragonball.model.character.fighter.Fighter;
import dragonball.model.exceptions.NotEnoughKiException;

public class Trainer extends NonFighter {
	private int addedDamage;

	public Trainer (String name, int addedDamage) {
		super(name); 
		this.addedDamage = addedDamage; 
	}

	@Override
	public void helpFighter(Fighter c) throws NotEnoughKiException {
		super.helpFighter(c);
		c.setBlastDamage(c.getBlastDamage()+addedDamage);
		c.setPhysicalDamage(c.getPhysicalDamage()+addedDamage); 
	}

	public int getAddedDamage() {
		return addedDamage;
	}

	
}
