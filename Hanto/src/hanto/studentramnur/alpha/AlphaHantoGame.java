package hanto.studentramnur.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.Butterfly;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.HantoPieceFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class AlphaHantoGame implements HantoGame {
	private HantoPlayerColor currentPlayerColor;
	private HashMap<HantoCoordinate, HantoPiece> board; 
	
	public AlphaHantoGame() {
		currentPlayerColor = HantoPlayerColor.BLUE;
		board = new HashMap<HantoCoordinate, HantoPiece>();
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		if (pieceType != HantoPieceType.BUTTERFLY) throw new HantoException("Only butterflies are allowed for this game.");
		if (from != null) throw new HantoException("The only move allowed is to add a Butterfly.");
		if (to == null) throw new HantoException("Invalid move.");
		else to = new HantoBoardCoordinate(to);

		HantoPiece piece = HantoPieceFactory.getInstance().createPiece(currentPlayerColor, pieceType);
		MoveResult result = MoveResult.OK;
		
		if (board.isEmpty()) {
			if ((to.getX() != 0) && (to.getY() != 0)) {
				throw new HantoException("The first move should always be placed at (0, 0).");
			}
			
			board.put(to, piece);
			result = MoveResult.OK;
			this.currentPlayerColor = HantoPlayerColor.RED;
		} else if(isAdjacentToOrigin(to)) {
			board.put(to, piece);
			System.out.println("Adding piece type: " + piece.getType());
			result = MoveResult.DRAW;
		} else {
			throw new HantoException("Move is invalid.");
		}
		
		return result;
	}
	
	private boolean isAdjacentToOrigin(HantoCoordinate cellToCheck) {
		return getCellDistance(cellToCheck, new HantoBoardCoordinate(0, 0)) == 1;
	}
	
	private int getCellDistance(HantoCoordinate firstCell, HantoCoordinate secondCell) {
		int xDifference = firstCell.getX() - secondCell.getX();
		int yDifference = firstCell.getY() - secondCell.getY();
		int absXDifference = Math.abs(xDifference);
		int absYDifference = Math.abs(yDifference);
		
		int absSumOfDifferences = Math.abs(xDifference + yDifference);
		
		int resultingDifference = Math.max(Math.max(absXDifference, absYDifference), absSumOfDifferences);
		
		return resultingDifference;
	}


	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		board.put(where, new Butterfly(HantoPlayerColor.BLUE));
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
