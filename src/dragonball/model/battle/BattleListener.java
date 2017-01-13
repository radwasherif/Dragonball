package dragonball.model.battle;

import java.io.IOException;

import dragonball.model.exceptions.MissingFieldException;

public interface BattleListener {
	void onBattleEvent(BattleEvent e) throws ClassNotFoundException, MissingFieldException, IOException; 
}
