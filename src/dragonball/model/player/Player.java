package dragonball.model.player;

import java.io.Serializable;
import java.util.ArrayList;

import dragonball.controller.GameController;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.DragonWish;
import dragonball.model.dragon.DragonWishType;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.game.Game;

public class Player implements Serializable{
	private PlayerListener listener;
	private String name;
	private ArrayList<PlayableFighter> fighters;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int dragonBalls;
	private PlayableFighter activeFighter;
	private int exploredMaps;

	public Player(String name) {
		this.name = name;
		fighters = new ArrayList<PlayableFighter>();
		superAttacks = new ArrayList<SuperAttack>();
		ultimateAttacks = new ArrayList<UltimateAttack>();

	}

	public Player(String name, ArrayList<PlayableFighter> fighters,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans,
			int dragonBalls, PlayableFighter activeFighter, int exploredMaps) {
		this.name = name;
		this.fighters = fighters;
		this.superAttacks = superAttacks;

		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.dragonBalls = dragonBalls;
		this.activeFighter = activeFighter;
		this.exploredMaps = exploredMaps;
	}

	public int getMaxFighterLevel() {
		int maxLevel = 0;
		for (PlayableFighter fighter : fighters) {
			if (fighter.getLevel() > maxLevel)
				maxLevel = fighter.getLevel();
		}
		return maxLevel;
	}

	public void callDragon() {
		listener.onDragonCalled();
	}

	public void chooseWish(DragonWish wish) {
		DragonWishType type = wish.getType();
		if (type == DragonWishType.SENZU_BEANS)
			senzuBeans += wish.getSenzuBeans();
		else if (type == DragonWishType.ABILITY_POINTS)
			activeFighter.setAbilityPoints(activeFighter.getAbilityPoints()
					+ wish.getAbilityPoints());
		else if (type == DragonWishType.SUPER_ATTACK)
			superAttacks.add(wish.getSuperAttack());
		else if (type == DragonWishType.ULTIMATE_ATTACK)
			ultimateAttacks.add(wish.getUltimateAttack());

		if (listener != null)
			listener.onWishChosen(wish);
	}

	public void createFighter(char race, String name) throws NotEnoughKiException {
		PlayableFighter f;
		switch (race) {
		case 'E':
			f = new Earthling(name);
			break;
		case 'F':
			f = new Frieza(name);
			break;
		case 'M':
			f = new Majin(name);
			break;
		case 'N':
			f = new Namekian(name);
			break;
		default:
			f = new Saiyan(name);
			break; // Saiyan

		}
		if (fighters.isEmpty())
			activeFighter = f;
		fighters.add(f);

	}

	public void upgradeFighter(PlayableFighter fighter, char fighterAttribute)
			throws NotEnoughAbilityPointsException {
		if (fighter.getAbilityPoints() > 0) {
			fighter.setAbilityPoints(fighter.getAbilityPoints() - 1);
			switch (fighterAttribute) {
			case 'H':
				fighter.setMaxHealthPoints(fighter.getMaxHealthPoints() + 50);
				break;
			case 'B':
				fighter.setBlastDamage(fighter.getBlastDamage() + 50);
				break;
			case 'P':
				fighter.setPhysicalDamage(fighter.getPhysicalDamage() + 50);
				break;
			case 'K': {
				fighter.setMaxKi(fighter.getMaxKi() + 1);
			  if (fighter== activeFighter) {
			    updateWorldInfoPanel(fighterAttribute); 
			  }
			  break;
			}
				
			case 'S': {
			  fighter.setMaxStamina(fighter.getMaxStamina() + 1);
			  if (fighter == activeFighter) {
			    updateWorldInfoPanel(fighterAttribute); 
			  }
        break;
			}
				
			}
		} else {
			throw new NotEnoughAbilityPointsException();
		}
	} 
	
	public void updateWorldInfoPanel (char fighterAttribute) {
	    Game g = (Game) listener; 
	    GameController controller = (GameController) g.getListener(); 
	    if (fighterAttribute == 'K') {
	      controller.getGameView().getWorldView().getInfo().addMaxKi();
	    } else if (fighterAttribute== 'S') {
	      controller.getGameView().getWorldView().getInfo().addMaxStamina();
	    }
	}

	public void assignAttack(PlayableFighter fighter, SuperAttack newAttack,
			SuperAttack oldAttack) throws DuplicateAttackException, MaximumAttacksLearnedException {

		if (oldAttack == null)
		{
			if (fighter.getSuperAttacks().size() < 4) 
			{
				if (fighter.getSuperAttacks().contains(newAttack))
					throw new DuplicateAttackException(newAttack);
				fighter.getSuperAttacks().add(newAttack);
			} 
			else 
			{
				throw new MaximumAttacksLearnedException();
			}
		} 
		else 
		{
			if (fighter.getSuperAttacks().contains(newAttack))
				throw new DuplicateAttackException(newAttack);

			fighter.getSuperAttacks().remove(oldAttack);
			fighter.getSuperAttacks().add(newAttack);

		}
	}

	public void assignAttack(PlayableFighter fighter, UltimateAttack newAttack,
			UltimateAttack oldAttack) throws DuplicateAttackException, MaximumAttacksLearnedException, NotASaiyanException {
		if (!(fighter instanceof Saiyan) && newAttack instanceof SuperSaiyan ) //to check if fighter is Saiyan - Radwa 
			throw new NotASaiyanException("You're not a Saiyan. You cannot use Super Saiyan attack."); 
		if (oldAttack == null)
		{
			if (fighter.getUltimateAttacks().size() < 2)
			{
				if(fighter.getUltimateAttacks().contains(newAttack)) 
				{
					throw new DuplicateAttackException(newAttack); 
				}
				fighter.getUltimateAttacks().add(newAttack);
			} 

			else 
			{ System.out.println(fighter.getUltimateAttacks().get(0).getName());
			 System.out.println(fighter.getUltimateAttacks().get(1).getName());
			 System.out.println(newAttack.getName());
				throw new MaximumAttacksLearnedException(); 	
				
			}
		}
		else 
		{
			if(fighter.getUltimateAttacks().contains(newAttack))
				throw new DuplicateAttackException(newAttack); 
			fighter.getUltimateAttacks().remove(oldAttack);
			fighter.getUltimateAttacks().add(newAttack);
			
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<PlayableFighter> getFighters() {
		return fighters;
	}

	public void setFighters(ArrayList<PlayableFighter> fighters) {
		this.fighters = fighters;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public void setSuperAttacks(ArrayList<SuperAttack> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public void setUltimateAttacks(ArrayList<UltimateAttack> ultimateAttacks) {
		this.ultimateAttacks = ultimateAttacks;
	}

	public int getSenzuBeans() {
		return this.senzuBeans;
	}

	public void setSenzuBeans(int senzuBeans) {
		this.senzuBeans = senzuBeans;
	}

	public int getDragonBalls() {
		return this.dragonBalls;
	}

	public void setDragonBalls(int dragonBalls) {
		this.dragonBalls = dragonBalls;
	}

	public PlayableFighter getActiveFighter() {
		return this.activeFighter;
	}

	public void setActiveFighter(PlayableFighter activeFighter) {
		this.activeFighter = activeFighter;
	}

	public int getExploredMaps() {
		return exploredMaps;
	}

	public void setExploredMaps(int exploredMaps) {
		this.exploredMaps = exploredMaps;
	}

	public PlayerListener getListener() {
		return listener;
	}

	public void setListener(PlayerListener listener) {
		this.listener = listener;
	}

}
