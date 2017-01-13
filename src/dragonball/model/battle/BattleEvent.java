package dragonball.model.battle;

import java.util.EventObject;

import dragonball.model.attack.Attack;
import dragonball.model.cell.Collectible;

public class BattleEvent extends EventObject {
	private BattleEventType type;
	private BattleOpponent currentOpponent;
	private BattleOpponent winner;
	private BattleOpponent loser; 
	private Attack attack;
	private Collectible collectible;

	

	public BattleEvent(Battle battle, BattleEventType type) {
		super(battle);
		this.type = type;
		this.currentOpponent = battle.getAttacker(); 
	}

	public BattleEvent(Battle battle, BattleEventType type,
			BattleOpponent winner) { // to set winner in case
		// of ended event
		this(battle, type);
		this.winner = winner;
	}

	// to set attack in case of attack event
	public BattleEvent(Battle battle, BattleEventType type, Attack attack) {
		this(battle, type);
		this.attack = attack;
	}

	public BattleEvent(Battle battle, BattleEventType type,
			Collectible collectible) {
		this(battle, type);
		this.collectible = collectible;
	} 
	
	
	//getters 
	public BattleEventType getType() {
		return type;
	}

	public BattleOpponent getCurrentOpponent() {
		return currentOpponent;
	}

	public BattleOpponent getWinner() {
		return winner;
	}

	public Attack getAttack() {
		return attack;
	}

	public Collectible getCollectible() {
		return collectible;
	}
	public BattleOpponent getLoser() {
		return loser;
	}

	public void setLoser(BattleOpponent loser) {
		this.loser = loser;
	}
}
