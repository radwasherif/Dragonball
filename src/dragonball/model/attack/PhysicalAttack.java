package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.exceptions.NotEnoughKiException;


public class PhysicalAttack extends Attack{

	public PhysicalAttack() {
		super("Physical Attack", 50);
		
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		Fighter f = ((Fighter)attacker); 
		
		int damage = 50 + f.getPhysicalDamage(); 
		
		
        
		
		return damage; 
	}
	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) throws NotEnoughKiException {
		Fighter f = ((Fighter)attacker); 
		f.setKi(f.getKi()+1);
		if(f.getKi() > f.getMaxKi()) 
			f.setKi(f.getMaxKi());
		super.onUse(attacker, defender, defenderBlocking);
	}
	

}
