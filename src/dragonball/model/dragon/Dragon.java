package dragonball.model.dragon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Dragon implements Serializable {
	private String name;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int abilityPoints;
	public Dragon(String name, ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans,
			int abilityPoints) {
		
		this.name = name;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.abilityPoints = abilityPoints;
	} 
	public DragonWish [] getWishes () {
		DragonWish wishes [] = new DragonWish [4]; 
		Random r = new Random(); 
		wishes[0] = new DragonWish(this, DragonWishType.SENZU_BEANS, senzuBeans); 
		wishes[1] = new DragonWish(this, DragonWishType.ABILITY_POINTS, abilityPoints); 
		int index; 
		if (!superAttacks.isEmpty()) {
			index = r.nextInt(superAttacks.size()); 
			wishes[2] = new DragonWish(this, DragonWishType.SUPER_ATTACK, superAttacks.get(index)); 
		} 
		if (!ultimateAttacks.isEmpty()) {
			index = r.nextInt(ultimateAttacks.size()); 
			wishes[3] = new DragonWish(this, DragonWishType.ULTIMATE_ATTACK, ultimateAttacks.get(index)); 
		} 
		
		return wishes; 
	}
	public String getName() {
		return name;
	}
	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}
	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}
	public int getSenzuBeans() {
		return senzuBeans;
	}
	public int getAbilityPoints() {
		return abilityPoints;
	}
	
}
