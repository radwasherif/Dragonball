package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.PlayableCharacter;
import dragonball.model.exceptions.NotEnoughKiException;

public abstract class PlayableFighter extends Fighter implements
		PlayableCharacter {
	private int xp;
	private int targetXp;
	private int abilityPoints;

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
		
		while (this.xp >= targetXp) {
			this.setLevel(this.getLevel() + 1);
			this.xp -= targetXp;
			targetXp += 20;
		}
		if (this.xp < 0)
			this.xp = 0;
	}

	public int getTargetXp() {
		return targetXp;
	}

	public void setTargetXp(int targetXp) {
		this.targetXp = targetXp;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}

	public void setAbilityPoints(int abilityPoints) {
		this.abilityPoints = abilityPoints;
	}

	public PlayableFighter(String name, int level, int xp, int targetXp,
			int maxHealthPoints, int blastDamage, int physicalDamage,
			int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) throws NotEnoughKiException {
		super(name, level, maxHealthPoints, blastDamage, physicalDamage, maxKi,
				maxStamina, superAttacks, ultimateAttacks);
		this.xp = xp;
		this.targetXp = targetXp;
		this.abilityPoints = abilityPoints;
		super.setHealthPoints(super.getMaxHealthPoints());
		super.setStamina(super.getMaxStamina());
		super.setKi(0);
	}

	public PlayableFighter(String name, int maxHealthPoints, int blastDamage,
			int physicalDamage, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) throws NotEnoughKiException {
		super(name, 1, maxHealthPoints, blastDamage, physicalDamage, maxKi,
				maxStamina, superAttacks, ultimateAttacks);
		super.setHealthPoints(super.getMaxHealthPoints());
		super.setStamina(super.getMaxStamina());
		super.setKi(0);
		xp = 0;
		targetXp = 10;
		abilityPoints = 0;

	}
}
