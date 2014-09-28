package hanto.studentramnur.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.HantoTestGame;
import hanto.studentramnur.common.piece.HantoPieceFactory;

public class GammaHantoTestGame extends GammaHantoGame implements HantoTestGame {
	
	public GammaHantoTestGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
	}

	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		for(PieceLocationPair pair: initialPieces)  {
			HantoCoordinate coor = new HantoBoardCoordinate(pair.location);
			HantoPiece piece = HantoPieceFactory.getInstance().createPiece(pair.player, pair.pieceType);
			
			if(piece.getColor() == HantoPlayerColor.BLUE) bluePlayer.decrementPieceCount(piece.getType());
			else redPlayer.decrementPieceCount(piece.getType());
			
			board.addPiece(coor, piece);
		}
	}

	@Override
	public void setTurnNumber(int turnNumber) {
		this.bluePlayer.setMovesMade(turnNumber - 1);
		this.redPlayer.setMovesMade(turnNumber - 1);
	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		this.currentPlayer = player == HantoPlayerColor.BLUE ? this.bluePlayer : this.redPlayer;
	}
}
