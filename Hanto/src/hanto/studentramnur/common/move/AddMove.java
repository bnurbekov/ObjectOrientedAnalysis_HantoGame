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

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoard;
import hanto.studentramnur.common.HantoPlayerStatistics;
import hanto.studentramnur.common.piece.HantoPieceFactory;

/**
 * Add move class that is responsible for piece placement onto the board.
 * 
 * @author Shadi
 * @author Batyr
 */
public class AddMove extends Move {

	/**
	 * Constructor for AddMove.
	 * @param color HantoPlayerColor
	 * @param pieceType HantoPieceType
	 * @param from HantoCoordinate
	 * @param to HantoCoordinate
	 */
	public AddMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.ADD);
	}

	/**
	 * {@inheritDoc}
	 * @throws HantoException 
	 * @throws HantoMoveException 
	 */
	@Override
	public boolean validate(HantoPlayerStatistics currentPlayer, HantoBoard board) throws HantoException {
		boolean isValid = true;

		if(currentPlayer.getPieceCount(getPieceType()) == 0) {
				throw new HantoException("Player does not have that piece to add.");
		}
		
		if(board.isEmpty()) { // is it a new game?
			if(!board.isCellOrigin(to)) isValid = false; // is the piece added to origin?
		}

		if(board.getNumPiecesOnBoard() >= 2) { //if not first two moves
			if(!(board.isAdjacentToExistingCells(to, null, this.getColor())
					&& !board.isAdjacentToExistingCells(to, null, (this.getColor() == HantoPlayerColor.BLUE) ? HantoPlayerColor.RED : HantoPlayerColor.BLUE))) {
				isValid = false;
			}
		} else {
			isValid = super.validate(currentPlayer, board) && isValid;
		}

		return isValid;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(HantoBoard board) {
		final HantoPiece piece = HantoPieceFactory.getInstance().createPiece(this.getColor(), pieceType);

		board.addPiece(this.getTo(), piece);
	}
}
