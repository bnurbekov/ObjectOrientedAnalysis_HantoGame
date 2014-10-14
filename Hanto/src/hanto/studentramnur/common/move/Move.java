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
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoBoard;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.HantoPlayerStatistics;
import hanto.studentramnur.common.PieceCoordinatePair;

import java.util.ArrayList;
import java.util.Collection;

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
	 * @param currentPlayer 
	 * 
	 * @param board HantoBoard
	 * @return boolean indication of whether the move is valid or not
	 * @throws HantoException 
	 * @throws HantoMoveException 
	 */
	public boolean validate(HantoPlayerStatistics currentPlayer, HantoBoard board) throws HantoException {
		boolean isValid = true;

		if(this.isCellOccupied(board)) {
			isValid = false;
		}

		if(!board.isEmpty()) {
			if(!board.isAdjacentToExistingCells(to, getFrom(), null)) {
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
	 * Returns the list of available moves for the player
	 * 
	 * @param currentPlayer	the player to check the available moves for
	 * @param board the board
	 * @return the list of available moves
	 * @throws HantoException
	 */
	public static Collection<Move> getAllAvailableMoves(HantoPlayerStatistics currentPlayer, HantoBoard board) throws HantoException {
		Collection<Move> availableMoves = new ArrayList<Move>();
		Collection<HantoCoordinate> openCoor = board.getAllUnoccupiedAdjacentCells();
				
		//checks all the possible moves for pieces that are on the board
		for(PieceCoordinatePair pieceLocationPair: board.getPlayerPieces(currentPlayer.getColor())) { //for each piece on the hanto board belonging to currentPlayer
			for(HantoCoordinate to: openCoor) { // every available move of this piece
				Move move = HantoMoveFactory.getInstance().createMove(HantoGameID.EPSILON_HANTO, currentPlayer.getColor(), pieceLocationPair.getPiece().getType(), pieceLocationPair.getLocation(), to);
				if(move.validate(currentPlayer, board)) {
					availableMoves.add(move);
				}
			}
		}
		
		//checks all the possible add moves for the pieces that the current player still has
		for(HantoPiece piece: currentPlayer.getNotAddedPieces()) {
			for(HantoCoordinate to: openCoor) {
				Move move = HantoMoveFactory.getInstance().createMove(HantoGameID.EPSILON_HANTO, currentPlayer.getColor(), piece.getType(), null, to);
				if(move.validate(currentPlayer, board)) {
					availableMoves.add(move);
				}
			}
		}
		
		return availableMoves;
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
