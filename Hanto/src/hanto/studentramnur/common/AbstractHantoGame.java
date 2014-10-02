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
	protected HantoPlayer currentPlayer;
	protected HantoPlayer redPlayer;
	protected HantoPlayer bluePlayer;
	protected HantoBoard board;

	private MoveResult prevResult;
	
	protected Move currentMove;

	/**
	 * Constructor for the abstract Hanto Game.
	 * 
	 * @param movesFirst specifies the player that moves first.
	 */
	protected AbstractHantoGame(HantoPlayerColor movesFirst) {
		board = new HantoBoard();

		redPlayer = new HantoPlayer(HantoPlayerColor.RED);
		bluePlayer = new HantoPlayer(HantoPlayerColor.BLUE);
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
	 * This method is responsible for pre-move setup logic.
	 * 
	 * @param pieceType
	 * @param from
	 * @param to
	 * @throws HantoException
	 */
	protected abstract void preMoveSetUp(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to) throws HantoException;
	
	protected void validateMove() throws HantoException {
		if(this.isGameOver()) {
			throw new HantoException("Game is already over.");
		}
		
		if(currentMove.getMoveType() == MoveType.ADD) {
			if(currentPlayer.getPieceCount(currentMove.getPieceType()) == 0) {
				throw new HantoException("Player does not have that piece to add.");
			}
		}
		
		if(!currentMove.validate(board)) {
			throw new HantoException("Invalid Move.");
		}
		
		validateButterflyPlacement();
	}
	
	private void validateButterflyPlacement() throws HantoException {
		HantoPieceType pieceType = currentMove.getPieceType();
		
		if(!currentPlayer.hasPlacedButterfly() && pieceType != HantoPieceType.BUTTERFLY && currentPlayer.getMovesMade() >= 3) {
			throw new HantoException("Player must place a butterfly.");
		}
	}
	
	protected void executeMove() {
		currentMove.execute(board);
	}
	
	protected MoveResult postMoveSetUp() {
		currentPlayer.incrementMovesMade();
		
		if(currentMove.getMoveType() == MoveType.ADD) {
			currentPlayer.decrementPieceCount(currentMove.getPieceType());
		}

		MoveResult result = this.getGameResult();
		if(result == MoveResult.OK) changePlayer();

		prevResult = result;
		return result;
	}
	
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
		else if(!bluePlayer.hasPieces() && !redPlayer.hasPieces()) {
			return MoveResult.DRAW;
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
	 * {@inheritDoc}
	 */
	@Override
	public String getPrintableBoard() {
		return board.getPrintableBoard();
	}
}
