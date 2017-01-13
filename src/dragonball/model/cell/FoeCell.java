package dragonball.model.cell;

import java.io.IOException;

import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotEnoughKiException;

public class FoeCell extends Cell {
	private NonPlayableFighter foe;
    
	public FoeCell (NonPlayableFighter foe) {
		this.foe = foe; 
	}
	@Override
	public String toString() {
		if (foe.isStrong())
			return "[b]";
		return "[w]";
	}

	public NonPlayableFighter getFoe() {
		return foe;
	}
	@Override
	public void onStep() throws NotEnoughKiException, ClassNotFoundException, MissingFieldException, IOException {
	        if(getListener() != null)
	    	getListener().onFoeEncountered(foe);
		
	}

}
