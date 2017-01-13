package dragonball.model.exceptions;

public class NotEnoughSenzuBeansException extends NotEnoughResourcesException {
	public NotEnoughSenzuBeansException() {
		super("You don't have enough SenzuBeans to be used");
	}

}
