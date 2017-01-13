package dragonball.model.cell;

import java.io.IOException;

import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotEnoughKiException;

public interface CellListener {
	void onFoeEncountered(NonPlayableFighter foe) throws NotEnoughKiException, ClassNotFoundException, MissingFieldException, IOException; 
	void onCollectibleFound(Collectible collectible);
}
