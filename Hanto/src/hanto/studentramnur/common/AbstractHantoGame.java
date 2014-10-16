/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.move.Move;
import hanto.studentramnur.common.move.MoveType;

/**
 * Class for gamma Hanto game.
 */
public abstract class AbstractHantoGame implements HantoGame {
	protected HantoPlayerStatistics currentPlayer;
	protected HantoPlayerStatistics redPlayer;
	protected HantoPlayerStatistics bluePlayer;
	protected HantoBoard board;

	private MoveResult prevResult;

	protected Move currentMove;

	/**
	 * Constructor for the gamma Hanto game.
	 * @param movesFirst HantoPlayerColor
	 */
	protected AbstractHantoGame(HantoPlayerColor movesFirst) {
		board = new HantoBoard();

		redPlayer = new HantoPlayerStatistics(HantoPlayerColor.RED);
		bluePlayer = new HantoPlayerStatistics(HantoPlayerColor.BLUE);
		currentPlayer = (movesFirst == HantoPlayerColor.BLUE) ? bluePlayer : redPlayer;

		prevResult = MoveResult.OK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		this.preMoveSetUp(pieceType, from, to);
		this.validateMove();
		this.executeMove();
		return this.postMoveSetUp();
	}

	/**
	 * Method preMoveSetUp.
	 * 
	 * @param pieceType HantoPieceType
	 * @param from HantoCoordinate
	 * @param to HantoCoordinate
	 * @throws HantoException
	 */
	protected abstract void preMoveSetUp(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to) throws HantoException;

	/**
	 * Validates move.
	 * 
	 * @throws HantoException
	 */
	protected void validateMove() throws HantoException {
		if(this.isGameOver()) {
			throw new HantoException("Game is already over.");
		}

		boolean isValid = currentMove.validate(currentPlayer, board);
		if(!isValid) {
			throw new HantoException("Invalid Move.");
		}
		
		validateButterflyPlacement();
	}

	/**
	 * Validates whether the player has placed butterfly the first four moves or not
	 * 
	 * @throws HantoException
	 */
	private void validateButterflyPlacement() throws HantoException {
		final HantoPieceType pieceType = currentMove.getPieceType();

		if(!currentPlayer.hasPlacedButterfly() && pieceType != HantoPieceType.BUTTERFLY && currentPlayer.getMovesMade() >= 2) {
			throw new HantoException("Player must place a butterfly.");
		}
	}
	
	/**
	 * Method executeMove.
	 */
	protected void executeMove() {
		currentMove.execute(board);
	}

	/**
	 * Contains all the necessary logic for postMoveSetUp.
	 * 
	 * @return MoveResult the result of the game
	 */
	protected MoveResult postMoveSetUp() {
		currentPlayer.incrementMovesMade();

		if(currentMove.getMoveType() == MoveType.ADD) {
			currentPlayer.decrementPieceCount(currentMove.getPieceType());
		}

		final MoveResult result = this.getGameResult();
		if(result == MoveResult.OK) changePlayer();

		prevResult = result;
		return result;
	}

	/**
	 * Determines if the game is over.
	 * 
	 * @return indication of whether the game is over or not
	 */
	protected boolean isGameOver() {
		return prevResult != MoveResult.OK;
	}

	/**
	 * Gets the game result.
	 * 
	 * @return the game result
	 */
	protected MoveResult getGameResult() {
		if(currentMove.getResult() != MoveResult.OK) { // If the game was forfeit (game result not ok from executing move)
			return currentMove.getResult();
		}

		if(board.isButterflySurrounded(HantoPlayerColor.RED) && board.isButterflySurrounded(HantoPlayerColor.BLUE)) {
			return MoveResult.DRAW;
		}
		else if(board.isButterflySurrounded(HantoPlayerColor.RED)) {
			return MoveResult.BLUE_WINS;
		}
		else if(board.isButterflySurrounded(HantoPlayerColor.BLUE)) {
			return MoveResult.RED_WINS;
		}
		else {
			return MoveResult.OK;
		}
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
	 * Gets the statistics for the player with specified color
	 * 
	 * @param playerColor player color
	 * @return returns the statistics for the the player with specified color
	 */
	public HantoPlayerStatistics getPlayerStats(HantoPlayerColor playerColor) {
		if (playerColor == HantoPlayerColor.BLUE) {
			return bluePlayer;
		}
		else { 
			return redPlayer;
		}
	}
	
	/**
	 * Gets the board.
	 * 
	 * @return the board
	 */
	public HantoBoard getBoard() {
		return board;
	}
	
	/**
	 * Gets the current move.
	 * 
	 * @return the current move
	 */
	public Move getCurrentMove() {
		return currentMove;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrintableBoard() {
		return board.getPrintableBoard();
	}
}
