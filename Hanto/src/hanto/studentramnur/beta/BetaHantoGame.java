package hanto.studentramnur.beta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoPieceFactory;

public class BetaHantoGame implements HantoGame {
	private HantoPlayer currentPlayer;
	private HantoPlayer redPlayer;
	private HantoPlayer bluePlayer;
	private HantoBoard board;
	
	public BetaHantoGame() {
		
		board = new HantoBoard();
		
		redPlayer = new HantoPlayer(HantoPlayerColor.RED);
		bluePlayer = new HantoPlayer(HantoPlayerColor.BLUE);
		
		redPlayer.setPieceCount(HantoPieceType.BUTTERFLY, 1);
		redPlayer.setPieceCount(HantoPieceType.SPARROW, 5);
		
		bluePlayer.setPieceCount(HantoPieceType.BUTTERFLY, 1);
		bluePlayer.setPieceCount(HantoPieceType.SPARROW, 5);
		
		currentPlayer = bluePlayer;
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		if (pieceType != HantoPieceType.BUTTERFLY || pieceType != HantoPieceType.SPARROW)
			throw new HantoException("Only butterflies and sparrows are allowed for this game!");
		
		if (from != null)
			throw new HantoException("You can only add peices to this game!");
			
		HantoPiece piece = HantoPieceFactory.getInstance().createPiece(currentPlayer.getColor(), pieceType);
		MoveResult result = MoveResult.OK;

		if (this.isNewGame()) {
			if (!board.cellIsOrigin(to)) {
				throw new HantoException("The first move should always be placed at (0, 0)");
			} else if(currentPlayer.getColor() != HantoPlayerColor.BLUE) {
				throw new HantoException("The first player should be BLUE");
			}
		} else if (board.isCellEmpty(to) || !board.isAdjacentToExistingCell(to)) {
			throw new HantoException("Move is invalid.");
		}
		
		if(!currentPlayer.hasPlacedButterfly() && piece.getType() != HantoPieceType.BUTTERFLY && currentPlayer.getMovesMade() >= 4) {
			throw new HantoException("Player must place a butterfly.");
		}
		
		if(!currentPlayer.hasPieces()) {
			throw new HantoException("Player has no more pieces to add.");
		}
		
		board.addPiece(to, piece);
		
		result = getGameResult();
		
		if(result == MoveResult.OK) changePlayer();
		
		return result;
	}

	private MoveResult getGameResult() {
		if(board.isRedButterflySurrounded() && board.isBlueButterflySurrounded())
			return MoveResult.DRAW;
		else if(board.isRedButterflySurrounded())
			return MoveResult.BLUE_WINS;
		else if(board.isBlueButterflySurrounded())
			return MoveResult.RED_WINS;
		else if(!bluePlayer.hasPieces() && !redPlayer.hasPieces())
			return MoveResult.DRAW;
		else
			return MoveResult.OK;
	}

	private boolean isNewGame() {
		return board.isEmpty();
	}

	private void changePlayer() {
		if (currentPlayer == bluePlayer) {
			currentPlayer = redPlayer;
		} else {
			currentPlayer = bluePlayer;
		}
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return board.getPiece(where);
	}

	@Override
	public String getPrintableBoard() {
		return board.getPrintableBoard();
	}
}
