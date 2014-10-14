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
import hanto.studentramnur.common.HantoPlayerStatistics;

/**
 * Class that implements the walk move.
 * 
 * @author Batyr
 * @author Shadi
 */
public class WalkMove extends Move {

	/**
	 * The constructor for WalkMove class.
	 * 
	 * @param color the color of the piece that is being moved
	 * @param pieceType the type of the piece that is being moved
	 * @param from from coordinate
	 * @param to to coordinate
	 */
	public WalkMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.WALK);
	}

	/**
	 * {@inheritDoc}
	 * @throws HantoMoveException 
	 */
	@Override
	public boolean validate(HantoPlayerStatistics currentPlayer, HantoBoard board) throws HantoException {		
		return super.validate(currentPlayer, board)
				&& board.isCellAdjacentTo(this.getFrom(), this.getTo())
				&& board.pieceMatchesAtCell(this.getColor(), this.getPieceType(), this.getFrom())
				&& canPieceSqueeze(board)
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
