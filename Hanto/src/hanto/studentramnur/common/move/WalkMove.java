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
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoard;

/**
 * @author Batyr
 * @author Shadi
 */
public class WalkMove extends Move {

	/**
	 * 
	 * @param color
	 * @param pieceType
	 * @param from
	 * @param to
	 */
	public WalkMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.WALK);
	}

	/**
	 * 
	 * @param board
	 */
	@Override
	public boolean validate(HantoBoard board) {		
		return super.validate(board)
				&& board.isCellAdjacentTo(this.getFrom(), this.getTo())
				&& board.pieceMatchesAtCell(this.getColor(), this.getPieceType(), this.getFrom())
				&& canPieceSqueeze(board)
				&& canPieceBeMovedWithoutBreakingTheStructure(board);
	}

	/**
	 * 
	 * @param board
	 */
	@Override
	public void execute(HantoBoard board) {
		board.movePiece(this.getFrom(), this.getTo());
	}
}
