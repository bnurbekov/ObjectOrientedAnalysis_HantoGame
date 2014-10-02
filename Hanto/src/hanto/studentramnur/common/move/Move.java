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
 * @author Batyr and Shadi
 *
 */
public abstract class Move {
	protected HantoPlayerColor color;
	protected HantoPieceType pieceType;
	protected HantoCoordinate from;
	protected HantoCoordinate to;
	protected MoveResult result;
	protected MoveType moveType;
	
	protected Move(HantoPlayerColor color, HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to, MoveType moveType) {
		this.color = color;
		this.pieceType = pieceType;
		this.from = from;
		this.to = to;
		result = MoveResult.OK;
		this.moveType = moveType;
	}

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
	
	public abstract void execute(HantoBoard board);

	protected boolean isCellOccupied(HantoBoard board) {		
		return !board.isCellEmpty(this.getTo()); 
	}

	/**
	 * 
	 * @param board
	 * @return
	 */
	protected boolean pieceCanBeMovedWithoutBreakingTheStructure(HantoBoard board) {
		return !board.isCellCritical(from);
	}
	
	/**
	 * 
	 * @param board
	 * @return
	 */
	protected boolean pieceCanSqueeze(HantoBoard board) {
		System.out.println("pieceCanSqueeze: " + board.countCommonOccupiedNeighbors(from, to));
		return (board.countCommonOccupiedNeighbors(from, to) != 2);
	}
	
	public HantoPlayerColor getColor() {
		return color;
	}

	public HantoPieceType getPieceType() {
		return pieceType;
	}

	public HantoCoordinate getFrom() {
		return from;
	}

	public HantoCoordinate getTo() {
		return to;
	}
	
	public MoveResult getResult() {
		return result;
	}
	
	public MoveType getMoveType() {
		return moveType;
	}

	public void setColor(HantoPlayerColor color) {
		this.color = color;
	}

	public void setPieceType(HantoPieceType pieceType) {
		this.pieceType = pieceType;
	}

	public void setFrom(HantoCoordinate from) {
		this.from = from;
	}

	public void setTo(HantoCoordinate to) {
		this.to = to;
	}

	public void setResult(MoveResult result) {
		this.result = result;
	}
	
	
}
