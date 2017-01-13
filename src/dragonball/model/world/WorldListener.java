package dragonball.model.world;

import java.io.IOException;

import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotEnoughKiException;

public interface WorldListener  {
     void onFoeEncountered(NonPlayableFighter foe) throws NotEnoughKiException, ClassNotFoundException, MissingFieldException, IOException; 
     void onCollectibleFound(Collectible collectible);
    void onPlayerPositionReset(); 
}
