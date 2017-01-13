package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class SuperSaiyan extends UltimateAttack {

	public SuperSaiyan() {
	   super("Super Saiyan", 0);
		// TODO Auto-generated constructor stub
	} 
	
	public int getAppliedDamage (BattleOpponent attacker) {
		
		return 0; 
	}

	
	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) throws NotEnoughKiException {
		Fighter f = (Fighter) attacker; 
		if(f.getClass().equals(Saiyan.class) ) 
		{
			Saiyan s = (Saiyan) f; 
			if(s.getKi()>=3)
			s.setTransformed(true);
			else
				s.setKi(s.getKi() -3);
		}
		
	}
}
