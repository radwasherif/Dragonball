package dragonball.model.dragon;

import java.util.EventObject;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class DragonWish extends EventObject {
	private DragonWishType type;
	private int senzuBeans; // num of senzu granted by wish
	private int abilityPoints; // num of ability points granted by wish
	private SuperAttack superAttack;
	private UltimateAttack ultimateAttack;

	public DragonWish(Dragon dragon, DragonWishType type) {
		super(dragon);
		this.type = type;
	}

	public DragonWish(Dragon dragon, DragonWishType type, int senzuBeansOrAbilityPoints) {
		this(dragon, type);
		if (type == DragonWishType.ABILITY_POINTS)
			this.abilityPoints = senzuBeansOrAbilityPoints;
		else if (type == DragonWishType.SENZU_BEANS)
			this.senzuBeans = senzuBeansOrAbilityPoints;
	}

	public DragonWish(Dragon dragon, DragonWishType type, SuperAttack superAttack) {
		this(dragon, type);
		this.superAttack = superAttack;
	}

	public DragonWish(Dragon dragon, DragonWishType type, UltimateAttack ultimateAttack) {
		this(dragon, type);
		this.ultimateAttack = ultimateAttack;
	}

	public DragonWishType getType() {
		return type;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}

	public SuperAttack getSuperAttack() {
		return superAttack;
	}

	public UltimateAttack getUltimateAttack() {
		return ultimateAttack;
	}

}
