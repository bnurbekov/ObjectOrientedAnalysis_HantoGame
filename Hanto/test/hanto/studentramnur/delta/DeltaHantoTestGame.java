/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentramnur.delta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.HantoTestGame;
import hanto.studentramnur.common.piece.HantoPieceFactory;

/**
 * The class that tests Delta Hanto Game.
 * 
 * @author Batyr and Shadi
 *
 */
public class DeltaHantoTestGame extends DeltaHantoGame implements HantoTestGame {
	
	public DeltaHantoTestGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
	}

	/**
	 * 
	 */
	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		for(PieceLocationPair pair: initialPieces)  {
			HantoCoordinate coor = new HantoBoardCoordinate(pair.location);
			HantoPiece piece = HantoPieceFactory.getInstance().createPiece(pair.player, pair.pieceType);
			
			if(piece.getColor() == HantoPlayerColor.BLUE) {
				bluePlayer.decrementPieceCount(piece.getType());
				bluePlayer.incrementMovesMade();
			} 
			else {
				redPlayer.decrementPieceCount(piece.getType());
				redPlayer.incrementMovesMade();
			}
			
			board.addPiece(coor, piece);
		}
	}

	/**
	 * 
	 */
	@Override
	public void setTurnNumber(int turnNumber) {
		bluePlayer.setMovesMade(turnNumber - 1);
		redPlayer.setMovesMade(turnNumber - 1);
	}

	/**
	 * 
	 */
	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		currentPlayer = player == HantoPlayerColor.BLUE ? bluePlayer : redPlayer;
	}
}
