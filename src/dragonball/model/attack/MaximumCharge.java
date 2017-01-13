package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.exceptions.NotEnoughKiException;

public class MaximumCharge extends SuperAttack {

	public MaximumCharge() {
		super("Maximum Charge", 0);

	}

	public int getAppliedDamage(BattleOpponent attacker) {


		return 0;
	}

	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) throws NotEnoughKiException {

		Fighter f = (Fighter) attacker;
		f.setKi(f.getKi() + 3);
		if (f.getKi() > f.getMaxKi())
			f.setKi(f.getMaxKi());
	}

}
