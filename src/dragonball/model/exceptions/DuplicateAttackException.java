package dragonball.model.exceptions;

import dragonball.model.attack.Attack;

public class DuplicateAttackException extends InvalidAssignAttackException {
	public Attack getAttack() {
		return attack;
	}
	private Attack attack; 
	public DuplicateAttackException (Attack attack) {
		super(); 
		this.attack = attack; 
		
	}
}
