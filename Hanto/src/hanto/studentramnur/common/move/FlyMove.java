package hanto.studentramnur.common.move;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoard;


public class FlyMove extends Move {
	public FlyMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.FLY);
	}

	@Override
	public boolean validate(HantoBoard board) {
		return super.validate(board) 
				&& board.pieceMatchesAtCell(this.getColor(), this.getPieceType(), this.getFrom())
				&& pieceCanBeMovedWithoutBreakingTheStructure(board);
	}

	@Override
	public void execute(HantoBoard board) {
		board.movePiece(this.getFrom(), this.getTo());
	}
}
