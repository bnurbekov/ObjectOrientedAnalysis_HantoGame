/**
 * 
 */
package hanto.studentramnur.common.move;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoard;
import hanto.studentramnur.common.HantoBoardCoordinate;

/**
 * @author srramadan
 *
 */
public class JumpMove extends Move {

	public JumpMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.JUMP);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validate(HantoBoard board) {
		HantoCoordinate vector = board.getVector(this.getFrom(), this.getTo());
		HantoCoordinate unitVector = board.getUnitVector(vector);
		
		return super.validate(board)
				&& board.pieceMatchesAtCell(this.getColor(), this.getPieceType(), this.getFrom())
				&& hasPiecesAlongPath(unitVector, board)
				&& canPieceBeMovedWithoutBreakingTheStructure(board);
	}

	private boolean hasPiecesAlongPath(HantoCoordinate unitVector, HantoBoard board) {
		HantoCoordinate currentCell = from;
		
		boolean rtn = true;
		int pathCounter = 0;
		
		do {
			currentCell = new HantoBoardCoordinate(currentCell.getX() + unitVector.getX(), currentCell.getX() + unitVector.getX());
			pathCounter++;
			
			if (board.isCellEmpty(currentCell)) {
				rtn = false;
				break;
			}
				
		} while(!currentCell.equals(to));
		
		if(pathCounter < 2) rtn = false;
		
		return rtn;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(HantoBoard board) {
		board.movePiece(this.getFrom(), this.getTo());
	}

}
