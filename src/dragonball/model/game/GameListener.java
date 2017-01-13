package dragonball.model.game;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.BattleEvent;
import dragonball.model.cell.Collectible;
import dragonball.model.dragon.Dragon;

public interface GameListener  {
	void onDragonCalled(Dragon dragon); 
	void onCollectibleFound(Collectible collectible); 
	void onBattleEvent(BattleEvent e);
  void onPlayerPositionReset();
  void setAddedFeateaures(int gainedXP, int i, ArrayList<UltimateAttack> added,
      ArrayList<SuperAttack> addedS); 
}
