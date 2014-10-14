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
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoBoard;
import hanto.studentramnur.common.HantoPlayerStatistics;

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
	public boolean validate(HantoPlayerStatistics currentPlayer, HantoBoard board) throws HantoException {
		Collection<Move> availableMoves = getAllAvailableMoves(currentPlayer, board);
		
		if (availableMoves.size() != 0) {
			throw new HantoPrematureResignationException();
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
