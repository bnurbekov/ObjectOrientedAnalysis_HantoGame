/**
 * 
 */
package hanto.studentramnur.common.move;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoard;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.HantoPlayer;

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
	 * @throws HantoMoveException 
	 */
	@Override
	public boolean validate(HantoPlayer currentPlayer, HantoBoard board) throws HantoException {
		HantoCoordinate vector = board.getVector(this.getFrom(), this.getTo());
		HantoCoordinate unitVector = board.getUnitVector(vector);
		
		return super.validate(currentPlayer, board)
				&& board.pieceMatchesAtCell(this.getColor(), this.getPieceType(), this.getFrom())
				&& hasPiecesAlongPath(unitVector, board)
				&& canPieceBeMovedWithoutBreakingTheStructure(board);
	}

	private boolean hasPiecesAlongPath(HantoCoordinate unitVector, HantoBoard board) {
		HantoCoordinate currentCell = from;
		
		boolean rtn = true;
		int pathCounter = 0;
		
		while(!currentCell.equals(to)) {
			if (board.isCellEmpty(currentCell)) {
				rtn = false;
				break;
			}

			currentCell = new HantoBoardCoordinate(currentCell.getX() + unitVector.getX(), currentCell.getY() + unitVector.getY());
			pathCounter++;
		}
		
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
