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
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoBoard;

/**
 * The abstract class that is responsible for the movement capabilities of pieces.
 * 
 * @author Shadi
 * @author Batyr
 */
public abstract class Move {
	protected HantoPlayerColor color;
	protected HantoPieceType pieceType;
	protected HantoCoordinate from;
	protected HantoCoordinate to;
	protected MoveResult result;
	protected MoveType moveType;

	/**
	 * Constructor for Move.
	 * 
	 * @param color HantoPlayerColor
	 * @param pieceType HantoPieceType
	 * @param from HantoCoordinate
	 * @param to HantoCoordinate
	 * @param moveType MoveType
	 */
	protected Move(HantoPlayerColor color, HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to, MoveType moveType) {
		this.color = color;
		this.pieceType = pieceType;
		this.from = from;
		this.to = to;
		result = MoveResult.OK;
		this.moveType = moveType;
	}

	/**
	 * Checks if the move is valid.
	 * 
	 * @param board HantoBoard
	 * @return boolean indication of whether the move is valid or not
	 */
	public boolean validate(HantoBoard board) {
		boolean isValid = true;

		if(this.isCellOccupied(board)) {
			isValid = false;
		}

		if(!board.isEmpty()) {
			if(!board.isAdjacentToExistingCells(to, null)) {
				isValid = false;
			}
		}

		return isValid;
	}

	/**
	 * Executes the move.
	 * 
	 * @param board HantoBoard
	 */
	public abstract void execute(HantoBoard board);

	/**
	 * Method isCellOccupied.
	 * 
	 * @param board HantoBoard
	 * @return boolean
	 */
	protected boolean isCellOccupied(HantoBoard board) {		
		return !board.isCellEmpty(getTo()); 
	}

	/**
	 * Determines whether the piece can be moved without breaking the structure.
	 * 
	 * @param board the board
	 * @return boolean indication of whether the piece can be moved without breaking the structure
	 */
	protected boolean canPieceBeMovedWithoutBreakingTheStructure(HantoBoard board) {
		return !board.isCellCritical(from);
	}

	/**
	 * Determines whether the piece can be squeezed to the new position through its neighbors.
	 * 
	 * @param board the board
	 * @return boolean indication of whether the piece can be squeezed to the new position through its neighbors.
	 */
	protected boolean canPieceSqueeze(HantoBoard board) {
		return (board.countCommonOccupiedNeighbors(from, to) != 2);
	}

	/**
	 * getMoveType() method
	 * 
	 * @return moveType
	 */
	public MoveType getMoveType() {
		return moveType;
	}
	
	/**
	 * getColor() method
	 * 
	 * @return color
	 */
	public HantoPlayerColor getColor() {
		return color;
	}

	/**
	 * getPieceType() method
	 * 
	 * @return pieceType
	 */
	public HantoPieceType getPieceType() {
		return pieceType;
	}

	/**
	 * getFrom() method
	 * 
	 * @return from
	 */
	public HantoCoordinate getFrom() {
		return from;
	}

	/**
	 * getTo() method
	 * 
	 * @return to
	 */
	public HantoCoordinate getTo() {
		return to;
	}

	/**
	 * getResult() method
	 * 
	 * @return result
	 */
	public MoveResult getResult() {
		return result;
	}
}
