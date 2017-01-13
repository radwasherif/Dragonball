package dragonball.model.attack;

import java.io.Serializable;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public abstract class Attack implements Serializable{
	private String name;
	private int damage;

	public Attack(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}

	// getter and setter methods
	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}

	public abstract int getAppliedDamage(BattleOpponent attacker);

	//responsible mainly for decreasing the health points of the defender 
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) throws NotEnoughKiException {
		int appliedDamage = getAppliedDamage(attacker);
		Fighter attackFighter = (Fighter) attacker;
		Fighter defendFighter = (Fighter) defender;

		if (attackFighter instanceof Saiyan) {
			Saiyan s = (Saiyan) attackFighter;
			if (s.isTransformed()) {
				appliedDamage += appliedDamage * 0.25;
			}

		}
		if (defenderBlocking) {
			while (defendFighter.getStamina() > 0
					&& appliedDamage > 0) {
				defendFighter.setStamina(defendFighter.getStamina() - 1);
				appliedDamage -= 100;
			}
			if (appliedDamage > 0)
				defendFighter.setHealthPoints(defendFighter.getHealthPoints()
						- appliedDamage);
			if (defendFighter.getHealthPoints() < 0)
				defendFighter.setHealthPoints(0);

		} else {
			defendFighter.setHealthPoints(defendFighter.getHealthPoints()
					- appliedDamage);
		}
		//defenderBlocking = false; 
	System.out.println("stamina in onUse of defender: "+((Fighter) defender).getStamina());
	}
}
