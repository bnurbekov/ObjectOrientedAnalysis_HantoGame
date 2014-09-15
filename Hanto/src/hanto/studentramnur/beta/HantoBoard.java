/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.beta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoardCoordinate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that contains the board and implements the operations on cells.
 * 
 * @author Batyr
 *
 */
public class HantoBoard {

	private Map<HantoCoordinate, HantoPiece> boardCoorToPieces;
	private Map<HantoPiece, HantoCoordinate> boardPiecesToCoor;
	
	private HantoPiece redButterfly, blueButterfly;
	
	/**
	 * The constructor for the HantoBoard class.
	 */
	public HantoBoard() {
		boardCoorToPieces = new HashMap<HantoCoordinate, HantoPiece>();
		boardPiecesToCoor = new HashMap<HantoPiece, HantoCoordinate>();
	}
	
	/**
	 * Determines if the board is empty.
	 * 
	 * @return indication of whether the board is empty or not
	 */
	public boolean isEmpty() {
		return boardCoorToPieces.isEmpty();
	}
	
	/**
	 * Adds the piece to the board.
	 * 
	 * @param toCell cell to add the piece to
	 * @param piece the piece to add
	 * @throws HantoMoveException
	 */
	public void addPiece(HantoCoordinate toCell, HantoPiece piece) throws HantoMoveException {
		if(toCell == null) throw new HantoMoveException("To coordinate cannot be null.");
		if(isCellEmpty(toCell)) throw new HantoMoveException("To coordinate cannot be occupied.");
		
		if(piece.getType() == HantoPieceType.BUTTERFLY) {
			if(piece.getColor() == HantoPlayerColor.BLUE) {
				blueButterfly = piece;
			}
			else {
				redButterfly = piece;
			}
		}
		
		boardCoorToPieces.put(toCell, piece);
		boardPiecesToCoor.put(piece, toCell);
	}
	
	/**
	 * Determines if the cell is origin.
	 * 
	 * @param cell cell to check
	 * @return the indication of whether the cell is origin or not
	 */
	public boolean cellIsOrigin(HantoCoordinate cell) {
		return (cell.getX() == 0) || (cell.getY() == 0);
	}
	
	/**
	 * Determines if the cell is empty.
	 * 
	 * @param cell cell to check
	 * @return the indication of whether the cell is empty or not
	 */
	public boolean isCellEmpty(HantoCoordinate cell) {
		return boardCoorToPieces.containsKey(cell);
	}
	
	/**
	 * Determines if the 
	 * 
	 * @param piece
	 * @return
	 */
	public boolean hasPiece(HantoPiece piece) {
		return boardPiecesToCoor.containsKey(piece);
	}

	/**
	 * 
	 * @param cellToCheck
	 * @return
	 */
	public boolean isAdjacentToExistingCell(HantoCoordinate cellToCheck) {
		boolean isAdjacentToExistingCells = false;
		
		for(Map.Entry<HantoCoordinate, HantoPiece> entry : boardCoorToPieces.entrySet()){
			HantoCoordinate key = entry.getKey();

			if (cellIsAdjacentTo(cellToCheck, key)) {
				isAdjacentToExistingCells = true;
				break;
			}
		}
		
		return isAdjacentToExistingCells;
	}
	
	/**
	 * 
	 * @param cell
	 * @param toCell
	 * @return
	 */
	public boolean cellIsAdjacentTo(HantoCoordinate cell, HantoCoordinate toCell) {
		int cellDifference = getCellDistance(cell, toCell);
		return cellDifference == 1;
	}
	
	/**
	 * 
	 * @param fromCell
	 * @param toCell
	 * @return
	 */
	public int getCellDistance(HantoCoordinate fromCell, HantoCoordinate toCell) {
		int xDifference = fromCell.getX() - toCell.getX();
		int yDifference = fromCell.getY() - toCell.getY();
		int absXDifference = Math.abs(xDifference);
		int absYDifference = Math.abs(yDifference);
		
		int absSumOfDifferences = Math.abs(xDifference + yDifference);
		
		int resultingDifference = Math.max(Math.max(absXDifference, absYDifference), absSumOfDifferences);
		
		return resultingDifference;
	}
	
	/**
	 * 
	 * @param cell
	 * @return
	 */
	public boolean isCellSurrounded(HantoCoordinate cell) {
		boolean cellSurrounded = true;
		
		for(HantoCoordinate surroundingCell: getSurroundingCells(cell)) {
			if(isCellEmpty(surroundingCell)) cellSurrounded = false;
		}
		return cellSurrounded;
	}

	/**
	 * 
	 * @param cell
	 * @return
	 */
	private Collection<HantoCoordinate> getSurroundingCells(HantoCoordinate cell) {
		Collection<HantoCoordinate> surroundingCells = new ArrayList<HantoCoordinate>();
		
		surroundingCells.add(new HantoBoardCoordinate(cell.getX() + 1, cell.getY()));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX() + 1, cell.getY() - 1));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX(), cell.getY() - 1));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX() - 1, cell.getY()));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX() - 1, cell.getY() + 1));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX(), cell.getY() + 1));
		
		return surroundingCells;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isBlueButterflySurrounded() {
		HantoCoordinate blueButterflyCoor = boardPiecesToCoor.get(blueButterfly);
		return isCellSurrounded(blueButterflyCoor);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isRedButterflySurrounded() {
		HantoCoordinate redButterflyCoor = boardPiecesToCoor.get(redButterfly);
		return isCellSurrounded(redButterflyCoor);
	}

	/**
	 * 
	 * @param where
	 * @return
	 */
	public HantoPiece getPiece(HantoCoordinate where) {
		return boardCoorToPieces.get(where);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPrintableBoard() {
		StringBuilder output = new StringBuilder();
		
		for(Map.Entry<HantoCoordinate, HantoPiece> entry : boardCoorToPieces.entrySet()){
			HantoCoordinate key = entry.getKey();
			HantoPiece value = entry.getValue();
			
			String appendString = "X: " + Integer.toString(key.getX()) + ", Y:" + Integer.toString(key.getY()) + ": " + value.getColor().toString() + " - " + value.getType().toString() + "\n";
			output.append(appendString);
		}

		return output.toString();
	}
}
