package dragonball.model.player;

import java.io.Serializable;

import dragonball.model.dragon.DragonWish;

public interface PlayerListener extends Serializable {
   void onDragonCalled(); 
   void onWishChosen(DragonWish wish); 
}
