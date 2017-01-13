package dragonball.model.character;

import dragonball.model.character.fighter.Fighter;
import dragonball.model.exceptions.NotEnoughKiException;

public interface Spectator {
	void helpFighter(Fighter c) throws NotEnoughKiException;

	void cheerFighter(Fighter c) throws NotEnoughKiException;
}
