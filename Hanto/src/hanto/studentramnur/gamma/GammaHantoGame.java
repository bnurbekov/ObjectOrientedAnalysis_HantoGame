/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoBoard;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.HantoPieceFactory;
import hanto.studentramnur.common.HantoPlayer;

/**
 * Class for gamma Hanto game.
 */
public class GammaHantoGame implements HantoGame {
	private HantoPlayer currentPlayer;
	private HantoPlayer redPlayer;
	private HantoPlayer bluePlayer;
	private HantoBoard board;

	private MoveResult prevResult;

	/**
	 * Constructor for the gamma Hanto game.
	 */
	public GammaHantoGame(HantoPlayerColor movesFirst) {
		board = new HantoBoard();

		redPlayer = new HantoPlayer(HantoPlayerColor.RED);
		bluePlayer = new HantoPlayer(HantoPlayerColor.BLUE);

		redPlayer.setPieceCount(HantoPieceType.BUTTERFLY, 1);
		redPlayer.setPieceCount(HantoPieceType.SPARROW, 5);

		bluePlayer.setPieceCount(HantoPieceType.BUTTERFLY, 1);
		bluePlayer.setPieceCount(HantoPieceType.SPARROW, 5);

		currentPlayer = movesFirst == HantoPlayerColor.BLUE ? bluePlayer : redPlayer;
		prevResult = MoveResult.OK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {

		from = (from != null) ? new HantoBoardCoordinate(from) : null;
		to = new HantoBoardCoordinate(to);
		MoveResult result = MoveResult.OK;

		if(this.isGameOver()) throw new HantoException("Game is already over.");
		if(this.isNewGame()) validateFirstMove(from, to);
		else {
			this.validateCoordinates(from, to);
			this.validateButterflyPlacement(pieceType);

			if(this.isPlayerAdding(from)) {
				this.addPiece(pieceType, from, to);
			} else { // player is moving
				this.movePiece(pieceType, from, to);
			}
		}


		result = this.finishMove();
		return result;
	}

	private MoveResult finishMove() {
		currentPlayer.incrementMovesMade();

		MoveResult result = this.getGameResult();
		if(result == MoveResult.OK) changePlayer();

		prevResult = result;
		return result;
	}

	private void validateButterflyPlacement(HantoPieceType pieceType) throws HantoException {
		if(!currentPlayer.hasPlacedButterfly() && pieceType != HantoPieceType.BUTTERFLY && currentPlayer.getMovesMade() >= 3) {
			throw new HantoException("Player must place a butterfly.");
		}
	}

	private void movePiece(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		if(!(board.isAdjacentToExistingCells(to, null) && board.cellIsAdjacentTo(from, to))) throw new HantoException("Move is invalid.");

		board.movePiece(from, to);
	}

	private void addPiece(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to) throws HantoException {
		if(currentPlayer.getPieceCount(pieceType) == 0) 
			throw new HantoException("Player does not have that piece to add.");

		if(!board.isAdjacentToExistingCells(to, currentPlayer.getColor())) {
			throw new HantoException("Move is invalid.");
		}

		HantoPiece piece = HantoPieceFactory.getInstance().createPiece(currentPlayer.getColor(), pieceType);

		board.addPiece(to, piece);
		currentPlayer.decrementPieceCount(pieceType);
	}

	private boolean isPlayerAdding(HantoCoordinate from) {
		return from == null;
	}

	private void validateCoordinates(HantoCoordinate from, HantoCoordinate to) throws HantoException {
		if(to == null || !board.isCellEmpty(to)) throw new HantoException("Move is invalid.");
	}

	private void validateFirstMove(HantoCoordinate from, HantoCoordinate to) throws HantoException {
		if(!(board.cellIsOrigin(to) && from == null)) throw new HantoException("The first move should always be placed at (0, 0)");
	}

	private boolean isGameOver() {
		return prevResult != MoveResult.OK || (bluePlayer.getMovesMade() + redPlayer.getMovesMade() >= 40);
	}

	/**
	 * Gets the game result.
	 * 
	 * @return the game result
	 */
	private MoveResult getGameResult() {
		if(board.isButterflySurrounded(HantoPlayerColor.RED) && board.isButterflySurrounded(HantoPlayerColor.BLUE))
		{
			return MoveResult.DRAW;
		}
		else if(board.isButterflySurrounded(HantoPlayerColor.RED)) {
			return MoveResult.BLUE_WINS;
		}
		else if(board.isButterflySurrounded(HantoPlayerColor.BLUE)) {
			return MoveResult.RED_WINS;
		}
		else if(!bluePlayer.hasPieces() && !redPlayer.hasPieces()) {
			return MoveResult.DRAW;
		}
		else {
			return MoveResult.OK;
		}
	}

	/**
	 * Determines if the game has just started.
	 * 
	 * @return indication of whether the game is new or not
	 */
	private boolean isNewGame() {
		return board.isEmpty();
	}

	/**
	 * Changes the current player to the player with the opposite color.
	 */
	private void changePlayer() {
		if (currentPlayer == bluePlayer) {
			currentPlayer = redPlayer;
		} else {
			currentPlayer = bluePlayer;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		where = new HantoBoardCoordinate(where);
		return board.getPiece(where);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrintableBoard() {
		return board.getPrintableBoard();
	}
}
