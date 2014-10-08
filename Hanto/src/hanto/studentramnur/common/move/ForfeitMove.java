/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.common.move;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoBoard;
import hanto.studentramnur.common.HantoPlayer;
import hanto.studentramnur.common.PieceLocationPair;

/**
 * Forfeit move class that is responsible for the move that allows player to forfeit.
 *
 * @author Shadi
 * @author Batyr
 */
public class ForfeitMove extends Move {

	/**
	 * Constructor for ForfeitMove.
	 * @param color HantoPlayerColor
	 * @param pieceType HantoPieceType
	 * @param from HantoCoordinate
	 * @param to HantoCoordinate
	 */
	public ForfeitMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.FORFEIT);
	}

	/**
	 * {@inheritDoc}
	 * @throws HantoException 
	 */
	@Override
	public boolean validate(HantoPlayer currentPlayer, HantoBoard board) throws HantoException {
		Collection<HantoCoordinate> openCoor = board.getAllUnoccupiedAdjacentCells();
		
		for(PieceLocationPair piece: board.getPlayerPieces(this.color)) { //for each piece on the hanto board belonging to currentPlayer
			for(HantoCoordinate to: openCoor) { // every available move of this piece
				Move move = HantoMoveFactory.getInstance().createMove(HantoGameID.EPSILON_HANTO, color, pieceType, piece.getLocation(), to);
				if(move.validate(currentPlayer, board)) {
					throw new HantoPrematureResignationException();
				}
			}
		}
		
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(HantoBoard board) {
		result = (color == HantoPlayerColor.BLUE) ? MoveResult.RED_WINS : MoveResult.BLUE_WINS;
	}
}
