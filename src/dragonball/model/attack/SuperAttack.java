package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class SuperAttack extends Attack {

	public SuperAttack(String name, int damage) {
		super(name, damage);

	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		Fighter f = (Fighter) attacker;
		int appliedDamage = 0;

		
			appliedDamage = getDamage() + f.getBlastDamage();
		

		return appliedDamage;

	}

	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) throws NotEnoughKiException {
	  System.out.println("super attack on use method");
		Fighter f = (Fighter) attacker;
		if (!f.getClass().equals(Saiyan.class)) {
		
				f.setKi(f.getKi() - 1);

			

		}
		else {
			Saiyan s = (Saiyan) f;
			if (!s.isTransformed())
				{
					f.setKi(f.getKi() - 1);
				}
		}
		super.onUse(attacker, defender, defenderBlocking);

	}
}
