package hanto.studentramnur.beta;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoPieceFactory;

public class BetaHantoGame implements HantoGame {
	private HantoPlayerColor currentPlayerColor;
	private HashMap<HantoCoordinate, HantoPiece> board; 
	
	public BetaHantoGame() {
		currentPlayerColor = HantoPlayerColor.BLUE;
		board = new HashMap<HantoCoordinate, HantoPiece>();
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		if (pieceType != HantoPieceType.BUTTERFLY) 
			throw new HantoException("Only butterflies are allowed for this game!");
			
		HantoPiece piece = HantoPieceFactory.getInstance().createPiece(currentPlayerColor, pieceType);
		MoveResult result = MoveResult.OK;
		
		if (from != null) {
			//TODO: Some logic
		}
		else {
			if (board.isEmpty()) {
				if ((to.getX() != 0) || (to.getY() != 0)) {
					throw new HantoException("The first move should always be placed at (0, 0)");
				}
				
				board.put(to, piece);
				result = MoveResult.OK;
			}
			else {
				if (!board.containsKey(to) && isAdjacentToExistingCells(to)) {
					board.put(to, piece);
					result = MoveResult.DRAW;
				}
				else {
					throw new HantoException("Move is invalid.");
				}
			}
		}
		
		changePlayerColor();
		
		return result;
	}
	
	private boolean isAdjacentToExistingCells(HantoCoordinate cellToCheck) {
		boolean isAdjacentToExistingCells = false;
		
		Iterator<Entry<HantoCoordinate, HantoPiece>> iterator = board.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry<HantoCoordinate, HantoPiece> pair = (Map.Entry<HantoCoordinate, HantoPiece>)iterator.next();
			HantoCoordinate key = pair.getKey();

			if (isAdjacentToCell(cellToCheck, key)) {
				isAdjacentToExistingCells = true;
				break;
			}
		}
		
		return isAdjacentToExistingCells;
	}
	
	//We should probably place this method into separate class
	private boolean isAdjacentToCell(HantoCoordinate cellToCheck, HantoCoordinate existingCell) {
		int cellDifference = getAbsCellDifference(cellToCheck, existingCell);
		
		return cellDifference == 1;
	}
	
	private int getAbsCellDifference(HantoCoordinate firstCell, HantoCoordinate secondCell) {
		int absXDifference = Math.abs(firstCell.getX() - secondCell.getX());
		int absYDifference = Math.abs(firstCell.getY() - secondCell.getY());
		
		int sumOfDifferences = absXDifference + absYDifference;
		
		int resultingDifference = Math.max(Math.max(absXDifference, absYDifference), sumOfDifferences);
		
		return resultingDifference;
	}
	
	private void changePlayerColor() {
		if (this.currentPlayerColor == HantoPlayerColor.BLUE) {
			this.currentPlayerColor = HantoPlayerColor.RED;
		}
		else {
			this.currentPlayerColor = HantoPlayerColor.BLUE;
		}
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return board.get(where);
	}

	@Override
	public String getPrintableBoard() {
		Iterator<Entry<HantoCoordinate, HantoPiece>> iterator = board.entrySet().iterator();
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
