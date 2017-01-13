package dragonball.model.cell;

import java.io.IOException;
import java.io.Serializable;

import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotEnoughKiException;

public abstract class Cell implements Serializable {
	private CellListener listener; 
	public abstract String toString (); 
	public abstract void onStep() throws NotEnoughKiException, ClassNotFoundException, MissingFieldException, IOException;
	public CellListener getListener() {
		return listener;
	}
	public void setListener(CellListener listener) {
		this.listener = listener;
	} 
}
