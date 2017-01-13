package dragonball.model.cell;

public class CollectibleCell extends Cell {
	private Collectible collectible;

    public  CollectibleCell(Collectible collectible) {
		this.collectible = collectible; 
	}	

	@Override
	public String toString() {
		if (collectible==Collectible.SENZU_BEAN)
			return "[s]"; 
		else return "[d]"; 
	}
	public Collectible getCollectible() {
		return collectible;
	}

	@Override
	public void onStep() {
	  
		getListener().onCollectibleFound(collectible); 
		
	}
}
