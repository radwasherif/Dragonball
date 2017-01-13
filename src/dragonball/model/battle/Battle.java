package dragonball.model.battle;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.player.Player;

public class Battle implements Serializable{
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener listener;

	public Battle(BattleOpponent me, BattleOpponent foe) throws NotEnoughKiException {
		this.me = me;
		this.foe = foe;
		this.attacker = me;
		((PlayableFighter) me).setHealthPoints(((PlayableFighter) me)
				.getMaxHealthPoints());
		((PlayableFighter) me).setStamina(((PlayableFighter) me)
				.getMaxStamina());
		((PlayableFighter) me).setKi(0);
		((NonPlayableFighter) foe).setHealthPoints(((NonPlayableFighter) foe)
				.getMaxHealthPoints());
		((NonPlayableFighter) foe).setStamina(((NonPlayableFighter) foe)
				.getMaxStamina());
		((NonPlayableFighter) foe).setKi(0);

		if (foe.getClass().equals(Saiyan.class)) {
			Saiyan s = (Saiyan) foe;
			s.setTransformed(false);
		}

		if (me.getClass().equals(Saiyan.class)) {
			Saiyan s = (Saiyan) me;
			s.setTransformed(false);
		}
		

	}

	public ArrayList<Attack> getAssignedAttacks() {
		ArrayList<Attack> attacks = new ArrayList<Attack>();
		attacks.add(new PhysicalAttack());
		if (attacker != null) {
			Fighter f = (Fighter) attacker;
			attacks.addAll(f.getSuperAttacks());
			attacks.addAll(f.getUltimateAttacks());

		}
		return attacks;
	}

	public void attack(Attack attack) throws NotASaiyanException, NotEnoughKiException, ClassNotFoundException, MissingFieldException, IOException {
		
		if(!( attacker instanceof Saiyan) && attack instanceof SuperSaiyan)
			throw new NotASaiyanException();
			
		
		if (attacker.equals(me))
			attack.onUse(attacker, foe, foeBlocking);
		else {
			attack.onUse(attacker, me, meBlocking);
		} 
		BattleEvent e = null; 
		 if (attacker==me)
		   e = new BattleEvent(this, BattleEventType.ATTACK_ME, attack);
		 else 
		   e = new BattleEvent(this, BattleEventType.ATTACK_FOE, attack); 
		if (listener != null)
			listener.onBattleEvent(e);
		//System.out.println("in Battle ki fighter: "+((Fighter)attacker).getKi());
		endTurn();
	}

	public void block() throws NotEnoughKiException, ClassNotFoundException, MissingFieldException, IOException {
		if (attacker == me)
			meBlocking = true;
		else
			foeBlocking = true;
		BattleEvent e = null; 
		if (attacker == me) {
		  e = new BattleEvent(this, BattleEventType.BLOCK_ME); 
		} else {
		  e = new BattleEvent(this, BattleEventType.BLOCK_FOE); 
		  
		}
		if (listener != null) {
			listener.onBattleEvent(e);
		  //System.out.println(isMyTurn());
		}
		
		endTurn();

	}

	public void use(Player player, Collectible collectible)
			throws NotEnoughSenzuBeansException, NotEnoughKiException, ClassNotFoundException, MissingFieldException, IOException {

		if (collectible.equals(Collectible.SENZU_BEAN)) {
			if (player.getSenzuBeans() > 0) {
				(player.getActiveFighter()).setStamina(player
						.getActiveFighter().getMaxStamina());
				(player.getActiveFighter()).setHealthPoints((player
						.getActiveFighter().getMaxHealthPoints()));
				player.setSenzuBeans(player.getSenzuBeans() - 1);

				endTurn();
				if (listener != null)
					listener.onBattleEvent(new BattleEvent(this,
							BattleEventType.USE));
				
			} else {
				throw new NotEnoughSenzuBeansException();
			}

		}
	}

	public BattleOpponent getDefender() {
		if (attacker.equals(me))
			return foe;
		return me;
	}

	public void play() throws ClassNotFoundException, MissingFieldException, IOException {
	  if (attacker!= foe) {
	    System.out.println("you called play on your turn love, the engine");
	    return;  
	  }
	   
		Random r = new Random();
		int t = r.nextInt(4);
		if (t == 0)
      try {
        block();
        //System.out.println("4 - foe blocked from engine");
      } catch (NotEnoughKiException e) {
        play(); 
      }
    else {
			ArrayList<Attack> attacks = getAssignedAttacks();
			t = r.nextInt(attacks.size());
			try {
        attack(attacks.get(t));
        //System.out.println("4 - foe attacked from engine");
      } catch (NotASaiyanException | NotEnoughKiException e) {
        play(); 
      }
		}
	}

	public void start() throws ClassNotFoundException, MissingFieldException, IOException {
		attacker = me;
		if (listener != null)
			listener.onBattleEvent(new BattleEvent(this,
					BattleEventType.STARTED));
	}

	public void endTurn() throws NotEnoughKiException, ClassNotFoundException, MissingFieldException, IOException {
	  
		Fighter m = (Fighter) me;
		Fighter f = (Fighter) foe;
		//System.out.println("my HP: " + m.getHealthPoints() + "foe HP: " + f.getHealthPoints());
		if (m.getHealthPoints() <= 0) {
			BattleEvent e = new BattleEvent(this, BattleEventType.ENDED, foe);
			e.setLoser(me);
			if (listener != null)
				listener.onBattleEvent(e);
			return;
		}

		if (f.getHealthPoints() <= 0) {
			BattleEvent e = new BattleEvent(this, BattleEventType.ENDED, me);
			e.setLoser(foe);
			if (listener != null)
				listener.onBattleEvent(e);
			return;
		}

		// TODO: reset blocking variables
		if (attacker == me)
			foeBlocking = false;
		else
			meBlocking = false;

		switchTurn();
		if (attacker == me){
			me.onAttackerTurn();
			foe.onDefenderTurn();
		}

		else {
			foe.onAttackerTurn();
			me.onDefenderTurn();
		}
		if (listener != null)
			listener.onBattleEvent(new BattleEvent(this,
					BattleEventType.NEW_TURN));
		
		

	}
  public boolean isMyTurn () {
    return attacker == me; 
  }
	public void switchTurn() {
	  System.out.println("turn switched");
		if (attacker == me)
			attacker = foe;
		else
			attacker = me;
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleListener getListener() {
		return listener;
	}

	public void setListener(BattleListener listener) {
		this.listener = listener;
	}

	public BattleOpponent getFoe() {
		return foe;
	}

	public BattleOpponent getAttacker() {
		return attacker;
	}

	public boolean isMeBlocking() {
		return meBlocking;
	}

	public boolean isFoeBlocking() {
		return foeBlocking;
	}

}
