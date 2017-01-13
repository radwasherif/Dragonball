package dragonball.model.world;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.cell.Cell;
import dragonball.model.cell.CellListener;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.CollectibleCell;
import dragonball.model.cell.EmptyCell;
import dragonball.model.cell.FoeCell;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotEnoughKiException;

public class World implements CellListener, Serializable {
	private Cell[][] map;
	private int playerColumn;
	private int playerRow;
	private WorldListener listener;

	public World() {
		map = new Cell[10][10];
	}

	public void generateMap(ArrayList<NonPlayableFighter> weakFoes, ArrayList<NonPlayableFighter>strongFoes)
	{
		Random random = new Random();
		
		for (int i = 0; i < 10; i++) 
	    {
			for (int j = 0; j < 10; j++)
			{
				map[i][j] = null;
			}
		}
		
	    map[0][0] = new FoeCell(strongFoes.get(random.nextInt(strongFoes.size())));
	    
	    map[9][9] = new EmptyCell();
	    playerColumn = playerRow = 9;
	    
	    
	    for (int i = 0; i < 15; i++)
	    {
			int x = 0, y = 0;
			while((x == 0 && y == 0) || (x == 9 && y == 9) || map[x][y] != null)
			{
				x = random.nextInt(10);
				y = random.nextInt(10);
			}
			map[x][y] = new FoeCell(weakFoes.get(random.nextInt(weakFoes.size())));
	    }
	    
	    int numberOfSenzuBeans = random.nextInt(3)+3;
	    for (int i = 0; i < numberOfSenzuBeans; i++)
	    {
	    	int x = 0, y = 0;
			while((x == 0 && y == 0) || (x == 9 && y == 9) || map[x][y] != null)
			{
				x = random.nextInt(10);
				y = random.nextInt(10);
			}
			map[x][y] = new CollectibleCell(Collectible.SENZU_BEAN);
		}
	    
	    int x = 0, y = 0;
		while((x == 0 && y == 0) || (x == 9 && y == 9) || map[x][y] != null)
		{
			x = random.nextInt(10);
			y = random.nextInt(10);
		}
	    map[x][y] = new CollectibleCell(Collectible.DRAGON_BALL);
	    
	    for (int i = 0; i < 10; i++) 
	    {
			for (int j = 0; j < 10; j++)
			{
				if(map[i][j] == null)
					map[i][j] = new EmptyCell();
				map[i][j].setListener(this);
			}
		}
	    
	}
	
	public String toString()
	{
		String result = "";
		for (int i = 0; i < 10; i++) 
		{
			for (int j = 0; j < 10; j++) 
			{
				if(i == playerRow && j == playerColumn)
					result += "[x]";
				else
					result += map[i][j].toString() + " ";
			}
			if(i != 9)
				result += "\n";
		}
		
		return result;
	}
	

	public Cell[][] getMap() {
		return map;
	}

	public int getPlayerColumn() {
		return playerColumn;
	}

	public int getPlayerRow() {
		return playerRow;
	}

	public WorldListener getListener() {
		return listener;
	}

	public void setListener(WorldListener listener) {
		this.listener = listener;
	}

	
	@Override
	public void onFoeEncountered(NonPlayableFighter foe) throws NotEnoughKiException, ClassNotFoundException, MissingFieldException, IOException {
		map[playerRow][playerColumn] = new EmptyCell();
		listener.onFoeEncountered(foe);

	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		map[playerRow][playerColumn] = new EmptyCell();
		listener.onCollectibleFound(collectible);
	}

	public void resetPlayerPosition() {
	  listener.onPlayerPositionReset(); 
		playerRow = 9;
		playerColumn = 9;
	}

	public void moveUp() throws MapIndexOutOfBoundsException, NotEnoughKiException, IOException, MissingFieldException, ClassNotFoundException{
	  //System.out.println("UP!");
		if (playerRow > 0)
		{
			playerRow--;
			map[playerRow][playerColumn].onStep();
		} else {
			throw new MapIndexOutOfBoundsException(playerRow, playerColumn); 
		}
	}

	public void moveDown() throws MapIndexOutOfBoundsException, NotEnoughKiException, ClassNotFoundException, MissingFieldException, IOException{
		if (playerRow < 9)
		{ 
			playerRow++;
			map[playerRow][playerColumn].onStep();
			System.out.println(map[playerRow] + " " + map[playerColumn]);
		} else {
			throw new MapIndexOutOfBoundsException(playerRow, playerColumn); 
		}
	}

	public void moveRight() throws MapIndexOutOfBoundsException, NotEnoughKiException, ClassNotFoundException, MissingFieldException, IOException{
		if (playerColumn < 9)
		{ //System.out.println("RIGHT!");
			playerColumn++;
			map[playerRow][playerColumn].onStep();
			System.out.println(map[playerRow] + " " + map[playerColumn]);
		} else {
			throw new MapIndexOutOfBoundsException(playerRow, playerColumn); 
		}
	}

	public void moveLeft() throws MapIndexOutOfBoundsException, NotEnoughKiException, ClassNotFoundException, MissingFieldException, IOException{
		if (playerColumn > 0)
		{//System.out.println("LEFT!");
			playerColumn--;
			map[playerRow][playerColumn].onStep();
			System.out.println(map[playerRow] + " " + map[playerColumn]);
		} else {
			throw new MapIndexOutOfBoundsException(playerRow, playerColumn); 
		}
	} 
	
	public void returnFoe (NonPlayableFighter foe) {
		map[playerRow][playerColumn] = new FoeCell(foe); 
	}
 }
