package dragonball.model.exceptions;

public abstract class DragonBallException extends Exception {
    public DragonBallException() {
    	super(); 
    	
    } 
    
    public DragonBallException(String message) {
    	super(message); 
    }
}
