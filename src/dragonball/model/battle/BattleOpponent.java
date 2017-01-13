package dragonball.model.battle;

import dragonball.model.exceptions.NotEnoughKiException;

public interface BattleOpponent {
    void onAttackerTurn() throws NotEnoughKiException; 
    void onDefenderTurn() throws NotEnoughKiException; 
}
