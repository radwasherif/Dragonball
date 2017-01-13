package dragonball.model.character;

import dragonball.model.character.fighter.Fighter;
import dragonball.model.exceptions.NotEnoughKiException;

public abstract class NonFighter extends Character implements Spectator{
	public NonFighter (String name) {
		super(name); 
	} 
	
	public void helpFighter(Fighter c) throws NotEnoughKiException {
		c.setKi(c.getKi()+1);
		c.setStamina(c.getStamina()+1);
	}
	
	public void cheerFighter(Fighter c) throws NotEnoughKiException {
		if(c.getKi() > c.getStamina()) {
			c.setStamina(c.getStamina()+2);
		} else {
			c.setKi(c.getKi()+3);
		}
	}
}
