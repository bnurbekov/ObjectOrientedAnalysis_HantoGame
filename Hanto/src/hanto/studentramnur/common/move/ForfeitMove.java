package hanto.studentramnur.common.move;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoBoard;

public class ForfeitMove extends Move {

	public ForfeitMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.FORFEIT);
	}

	@Override
	public boolean validate(HantoBoard board) {
		return true;
	}

	@Override
	public void execute(HantoBoard board) {
		this.result = this.color == HantoPlayerColor.BLUE ? MoveResult.RED_WINS : MoveResult.BLUE_WINS;
	}
}
