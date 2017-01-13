package dragonball.model.exceptions;

public class NotEnoughAbilityPointsException extends NotEnoughResourcesException {
	public NotEnoughAbilityPointsException() {
		super("You don't have enough Ability Points to be used");
	}
}
