package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.exceptions.NotEnoughKiException;

public class Namekian extends PlayableFighter {

	public Namekian(String string) throws NotEnoughKiException {
		super(string, 1350, 50, 50, 3, 5, new ArrayList<SuperAttack>(),
				new ArrayList<UltimateAttack>());
	}

	public Namekian(String name, int level, int xp, int targetXp,
			int maxHealthPoints, int blastDamage, int physicalDamage,
			int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) throws NotEnoughKiException {
		super(name, level, xp, targetXp, maxHealthPoints, blastDamage,
				physicalDamage, abilityPoints, maxKi, maxStamina, superAttacks,
				ultimateAttacks);

	}

	@Override
	public void onAttackerTurn() {
		setStamina(getStamina() + 1);
		if (getStamina() > getMaxStamina())
			setStamina(getMaxStamina());

		setHealthPoints(getHealthPoints() + 50);
		if (getHealthPoints() > getMaxHealthPoints())
			setHealthPoints(getMaxHealthPoints());

	}

	@Override
	public void onDefenderTurn() {
		setStamina(getStamina() + 1);
		if (getStamina() > getMaxStamina())
			setStamina(getMaxStamina());

		setHealthPoints(getHealthPoints() + 50);
		if (getHealthPoints() > getMaxHealthPoints())
			setHealthPoints(getMaxHealthPoints());
	}

}
