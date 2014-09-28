package hanto.studentramnur.common.move;

import hanto.common.HantoCoordinate;
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
		return true;
	}

	@Override
	public void execute(HantoBoard board) {
		// TODO Auto-generated method stub
	}
}
