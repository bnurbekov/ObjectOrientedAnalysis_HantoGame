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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.AbstractHantoGame;

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
}
