package hanto.studentramnur.delta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.AbstractHantoGame;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.move.HantoMoveFactory;

public class DeltaHantoGame extends AbstractHantoGame {

	public DeltaHantoGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
		
		// Set up player pieces
		redPlayer.setPieceCount(HantoPieceType.BUTTERFLY, 1);
		redPlayer.setPieceCount(HantoPieceType.SPARROW, 4);
		redPlayer.setPieceCount(HantoPieceType.CRAB, 4);

		bluePlayer.setPieceCount(HantoPieceType.BUTTERFLY, 1);
		bluePlayer.setPieceCount(HantoPieceType.SPARROW, 4);
		bluePlayer.setPieceCount(HantoPieceType.CRAB, 4);
	}
	
	@Override
	protected void preMoveSetUp(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to) throws HantoException {
		from = (from == null) ? null : new HantoBoardCoordinate(from);
		to = (to == null) ? null : new HantoBoardCoordinate(to);
		
		this.currentMove = HantoMoveFactory.getInstance().createMove(HantoGameID.DELTA_HANTO, currentPlayer.getColor(), pieceType, from, to);
	}
}
