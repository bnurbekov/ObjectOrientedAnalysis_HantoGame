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
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoard;
import hanto.studentramnur.common.HantoPlayer;

/**
 * Fly move class that is responsible for the flight capabilities of the pieces.
 * 
 * @author Shadi
 * @author Batyr
 */
public class FlyMove extends Move {
	/**
	 * Constructor for FlyMove.
	 * @param color HantoPlayerColor
	 * @param pieceType HantoPieceType
	 * @param from HantoCoordinate
	 * @param to HantoCoordinate
	 */
	public FlyMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.FLY);
	}

	/**
	 * {@inheritDoc}
	 * @throws HantoMoveException 
	 */
	@Override
	public boolean validate(HantoPlayer currentPlayer, HantoBoard board) throws HantoException {
		return super.validate(currentPlayer, board) 
				&& board.pieceMatchesAtCell(this.getColor(), this.getPieceType(), this.getFrom())
				&& (board.getCellDistance(this.getFrom(), this.getTo()) <= 5)
				&& canPieceBeMovedWithoutBreakingTheStructure(board);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(HantoBoard board) {
		board.movePiece(this.getFrom(), this.getTo());
	}
}
