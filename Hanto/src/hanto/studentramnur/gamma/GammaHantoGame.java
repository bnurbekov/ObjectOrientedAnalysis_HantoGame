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
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.AbstractHantoGame;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.move.HantoMoveFactory;

/**
 * Class for gamma Hanto game.
 */
public class GammaHantoGame extends AbstractHantoGame {

	/**
	 * Constructor for the gamma Hanto game.
	 */
	public GammaHantoGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
		
		// Set up player pieces
		redPlayer.setPieceCount(HantoPieceType.BUTTERFLY, 1);
		redPlayer.setPieceCount(HantoPieceType.SPARROW, 5);

		bluePlayer.setPieceCount(HantoPieceType.BUTTERFLY, 1);
		bluePlayer.setPieceCount(HantoPieceType.SPARROW, 5);
	}

	@Override
	protected boolean isGameOver() {
		return super.isGameOver() || (bluePlayer.getMovesMade() + redPlayer.getMovesMade() >= 40);
	}
	
	@Override
	protected void preMoveSetUp(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to) throws HantoException {
		from =  (from == null) ? null : new HantoBoardCoordinate(from);
		to =  (to == null) ? null : new HantoBoardCoordinate(to);
		
		this.currentMove = HantoMoveFactory.getInstance().createMove(HantoGameID.GAMMA_HANTO, currentPlayer.getColor(), pieceType, from, to);
	}
}
