package dragonball.model.exceptions;

public class MapIndexOutOfBoundsException extends IndexOutOfBoundsException {
	private int row;
	private int column;

	public MapIndexOutOfBoundsException(int row, int column) {
		super("This move can not be done as the player is in row "+ row +" and column "+column );
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}
