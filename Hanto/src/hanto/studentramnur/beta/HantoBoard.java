package hanto.studentramnur.beta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoardCoordinate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HantoBoard {

	private HashMap<HantoCoordinate, HantoPiece> boardCoorToPieces;
	private HashMap<HantoPiece, HantoCoordinate> boardPiecesToCoor;
	
	private HantoPiece redButterfly, blueButterfly;
	
	public HantoBoard() {
		boardCoorToPieces = new HashMap<HantoCoordinate, HantoPiece>();
		boardPiecesToCoor = new HashMap<HantoPiece, HantoCoordinate>();
	}
	
	public boolean isEmpty() {
		return boardCoorToPieces.isEmpty();
	}
	
	public void addPiece(HantoCoordinate toCell, HantoPiece piece) throws HantoMoveException {
		if(toCell == null) throw new HantoMoveException("To coordinate cannot be null.");
		if(isCellEmpty(toCell)) throw new HantoMoveException("To coordinate cannot be occupied.");
		
		if(piece.getType() == HantoPieceType.BUTTERFLY) {
			if(piece.getColor() == HantoPlayerColor.BLUE) blueButterfly = piece;
			else redButterfly = piece;
		}
		
		boardCoorToPieces.put(toCell, piece);
		boardPiecesToCoor.put(piece, toCell);
	}
	
	public boolean cellIsOrigin(HantoCoordinate cell) {
		return (cell.getX() == 0) || (cell.getY() == 0);
	}
	
	public boolean isCellEmpty(HantoCoordinate toCell) {
		return boardCoorToPieces.containsKey(toCell);
	}
	
	public boolean hasPiece(HantoPiece piece) {
		return boardPiecesToCoor.containsKey(piece);
	}

	public boolean isAdjacentToExistingCell(HantoCoordinate cellToCheck) {
		boolean isAdjacentToExistingCells = false;
		
		Iterator<Entry<HantoCoordinate, HantoPiece>> iterator = boardCoorToPieces.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry<HantoCoordinate, HantoPiece> pair = (Map.Entry<HantoCoordinate, HantoPiece>)iterator.next();
			HantoCoordinate key = pair.getKey();

			if (cellIsAdjacentTo(cellToCheck, key)) {
				isAdjacentToExistingCells = true;
				break;
			}
		}
		
		return isAdjacentToExistingCells;
	}
	
	public boolean cellIsAdjacentTo(HantoCoordinate cell, HantoCoordinate toCell) {
		int cellDifference = getCellDistance(cell, toCell);
		return cellDifference == 1;
	}
	
	public int getCellDistance(HantoCoordinate fromCell, HantoCoordinate toCell) {
		int xDifference = fromCell.getX() - toCell.getX();
		int yDifference = fromCell.getY() - toCell.getY();
		int absXDifference = Math.abs(xDifference);
		int absYDifference = Math.abs(yDifference);
		
		int absSumOfDifferences = Math.abs(xDifference + yDifference);
		
		int resultingDifference = Math.max(Math.max(absXDifference, absYDifference), absSumOfDifferences);
		
		return resultingDifference;
	}
	
	public boolean isCellSurrounded(HantoCoordinate cell) {
		boolean cellSurrounded = true;
		
		for(HantoCoordinate surroundingCell: getSurroundingCells(cell)) {
			if(isCellEmpty(surroundingCell)) cellSurrounded = false;
		}
		return cellSurrounded;
	}

	private Collection<HantoCoordinate> getSurroundingCells(HantoCoordinate cell) {
		Collection<HantoCoordinate> surroundingCells = new ArrayList<HantoCoordinate>();
		
		surroundingCells.add(new HantoBoardCoordinate(cell.getX() + 1, cell.getY()));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX() + 1, cell.getY() - 1));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX(), cell.getY() - 1));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX() - 1, cell.getY()));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX() - 1, cell.getY() + 1));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX(), cell.getY() + 1));
		
		return surroundingCells;
	}

	public boolean isBlueButterflySurrounded() {
		HantoCoordinate blueButterflyCoor = boardPiecesToCoor.get(blueButterfly);
		return isCellSurrounded(blueButterflyCoor);
	}

	public boolean isRedButterflySurrounded() {
		HantoCoordinate redButterflyCoor = boardPiecesToCoor.get(redButterfly);
		return isCellSurrounded(redButterflyCoor);
	}

	public HantoPiece getPiece(HantoCoordinate where) {
		return boardCoorToPieces.get(where);
	}

	public String getPrintableBoard() {
		Iterator<Entry<HantoCoordinate, HantoPiece>> iterator = boardCoorToPieces.entrySet().iterator();
		StringBuilder output = new StringBuilder();
		
		while (iterator.hasNext()) {
			Map.Entry<HantoCoordinate, HantoPiece> pair = (Map.Entry<HantoCoordinate, HantoPiece>)iterator.next();
			HantoCoordinate key = pair.getKey();
			HantoPiece value = pair.getValue();
			
			String appendString = "X: " + Integer.toString(key.getX()) + ", Y:" + Integer.toString(key.getY()) + ": " + value.getColor().toString() + " - " + value.getType().toString() + "\n";
			output.append(appendString);
		}
		
		return output.toString();
	}
}
