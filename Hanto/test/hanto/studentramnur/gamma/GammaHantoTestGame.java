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
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.HantoTestGame;
import hanto.studentramnur.common.PieceLocationPair;
import hanto.studentramnur.common.piece.HantoPieceFactory;

/**
 * The class that tests Gamma Hanto Game.
 * 
 * @author Shadi
 * @author Batyr
 */
public class GammaHantoTestGame extends GammaHantoGame implements HantoTestGame {

	/**
	 * Constructor for GammaHantoTestGame.
	 * @param movesFirst HantoPlayerColor
	 */
	public GammaHantoTestGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
	}

	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		for(PieceLocationPair pair: initialPieces)  {
			HantoCoordinate coor = new HantoBoardCoordinate(pair.getLocation());
			HantoPiece piece = HantoPieceFactory.getInstance().createPiece(pair.getPlayer(), pair.getPieceType());

			if(piece.getColor() == HantoPlayerColor.BLUE) {
				bluePlayer.decrementPieceCount(piece.getType());
			} else {
				redPlayer.decrementPieceCount(piece.getType());
			}

			board.addPiece(coor, piece);
		}
	}

	@Override
	public void setTurnNumber(int turnNumber) {
		// This implementation saves turns individually made by the players.
		// To set the turn we have to evenly split the turns.  Thus the positive rounding for blue player's turn count.
		bluePlayer.setMovesMade((int)(turnNumber/2.0+0.5));
		redPlayer.setMovesMade(turnNumber/2);
	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		currentPlayer = (player == HantoPlayerColor.BLUE) ? bluePlayer : redPlayer;
	}
}
