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
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.HantoPlayerStatistics;

/**
 * The class that contains the logic for the jump move.
 * 
 * @author bnurbekov & srramadan
 *
 */
public class JumpMove extends Move {

	/**
	 * The constructor for the jump move.
	 * 
	 * @param color the color of the piece
	 * @param pieceType the type of the piece
	 * @param from from coordinate
	 * @param to to coordinate
	 */
	public JumpMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.JUMP);
	}
	
	/**
	 * {@inheritDoc}
	 * @throws HantoMoveException 
	 */
	@Override
	public boolean validate(HantoPlayerStatistics currentPlayer, HantoBoard board) throws HantoException {
		HantoCoordinate vector = board.getVector(this.getFrom(), this.getTo());
		HantoCoordinate unitVector = board.getUnitVector(vector);
			
		return super.validate(currentPlayer, board)
				&& board.pieceMatchesAtCell(this.getColor(), this.getPieceType(), this.getFrom())
				&& isStraightLine(unitVector)
				&& hasPiecesAlongPath(unitVector, board)
				&& canPieceBeMovedWithoutBreakingTheStructure(board);
	}

	private boolean isStraightLine(HantoCoordinate unitVector) {
		return unitVector.getX() ==0 
				|| unitVector.getY() == 0 
				|| unitVector.equals(new HantoBoardCoordinate(-1, 1)) 
				|| unitVector.equals(new HantoBoardCoordinate(1, -1));
	}
	
	private boolean hasPiecesAlongPath(HantoCoordinate unitVector, HantoBoard board) {
		HantoCoordinate currentCell = from;
		
		boolean rtn = true;
		int pathCounter = 0;

		while(!currentCell.equals(to)) {
			if (board.isCellEmpty(currentCell)) {
				rtn = false;
				break;
			}

			
			currentCell = new HantoBoardCoordinate(currentCell.getX() + unitVector.getX(), currentCell.getY() + unitVector.getY());
			pathCounter++;
		}
		
		if(pathCounter < 2) rtn = false;
		
		return rtn;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(HantoBoard board) {
		board.movePiece(this.getFrom(), this.getTo());
	}

}
