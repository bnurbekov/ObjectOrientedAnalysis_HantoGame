package hanto.studentramnur.common.move;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoard;

/**
 * @author Batyr
 * @author Shadi
 */
public class WalkMove extends Move {
	
	/**
	 * 
	 * @param color
	 * @param pieceType
	 * @param from
	 * @param to
	 */
	public WalkMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.WALK);
	}

	/**
	 * 
	 * @param board
	 */
	@Override
	public boolean validate(HantoBoard board) {		
			return super.validate(board)
					&& board.cellIsAdjacentTo(this.getFrom(), this.getTo())
					&& board.pieceMatchesAtCell(this.getColor(), this.getPieceType(), this.getFrom())
					&& pieceCanSqueeze(board)
					&& pieceCanBeMovedWithoutBreakingTheStructure(board);
	}

	/**
	 * 
	 * @param board
	 */
	@Override
	public void execute(HantoBoard board) {
		board.movePiece(this.getFrom(), this.getTo());
	}
}
