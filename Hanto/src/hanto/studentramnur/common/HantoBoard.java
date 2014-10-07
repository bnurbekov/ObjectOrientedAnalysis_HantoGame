/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.piece.Butterfly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that contains the board and implements the operations on cells.
 */
public class HantoBoard {

	private final Map<HantoCoordinate, HantoPiece> board;
	private final Map<HantoPiece, List<HantoCoordinate>> pieceToCoordsMapping;

	/**
	 * The constructor for the HantoBoard class.
	 */
	public HantoBoard() {
		board = new HashMap<HantoCoordinate, HantoPiece>();
		pieceToCoordsMapping = new HashMap<HantoPiece, List<HantoCoordinate>>();
	}

	/**
	 * Determines if the board is empty.
	 * 
	 * @return indication of whether the board is empty or not
	 */
	public boolean isEmpty() {
		return board.isEmpty();
	}

	/**
	 * Adds the piece to the board.
	 * 
	 * @param toCell cell to add the piece to
	 * @param piece the piece to add
	 */
	public void addPiece(HantoCoordinate toCell, HantoPiece piece) {
		board.put(toCell, piece);

		if (!pieceToCoordsMapping.containsKey(piece)) {
			final List<HantoCoordinate> coords = new ArrayList<HantoCoordinate>();
			coords.add(toCell);
			pieceToCoordsMapping.put(piece, coords);
		}
		else {
			pieceToCoordsMapping.get(piece).add(toCell);
		}
	}

	/**
	 * Determines if the cell is origin.
	 * 
	 * @param cell cell to check
	 * @return the indication of whether the cell is origin or not 
	 */
	public boolean isCellOrigin(HantoCoordinate cell) {
		return (cell.getX() == 0) && (cell.getY() == 0);
	}

	/**
	 * Determines if the cell is empty.
	 * 
	 * @param cell cell to check
	 * @return the indication of whether the cell is empty or not
	 */
	public boolean isCellEmpty(HantoCoordinate cell) {
		return !board.containsKey(cell);
	}

	/**
	 * Determines if the piece exists on the board
	 * 
	 * @param piece the piece to check
	 * @return indication of whether the piece exists on the board or not 
	 */
	public boolean hasPiece(HantoPiece piece) {
		return pieceToCoordsMapping.containsKey(piece);
	}

	/**
	 * Determines if a specific cell is adjacent to some existing cell color on the board.
	 * 
	 * @param cellToCheck the cell to check
	 * @param color the color to check against, null means either color
	 * @return indication of whether the cell is adjacent to some existing cell on the board or not 
	 */
	public boolean isAdjacentToExistingCells(HantoCoordinate cellToCheck, HantoPlayerColor color) {
		boolean isAdjacentToExistingCells = false;

		for(Map.Entry<HantoCoordinate, HantoPiece> entry : board.entrySet()){
			HantoCoordinate key = entry.getKey();

			if (isCellAdjacentTo(cellToCheck, key) && (entry.getValue().getColor() == color || color == null)) {
				isAdjacentToExistingCells = true;
				break;
			}
		}

		return isAdjacentToExistingCells;
	}
	
	/**
	 * Returns all the cells that are in the mesh and unoccupied
	 * 
	 * @return A list of unoccupied cells
	 */
	public boolean getAllUnoccupiedAdjacentCells() {
		Collection<HantoCoordinate> unnocupiedCells = new ArrayList<HantoCoordinate>();
		
		for(Map.Entry<HantoCoordinate, HantoPiece> entry : board.entrySet()){
			HantoCoordinate cell = entry.getKey();

			// Have to remove duplicates??
			unnocupiedCells.addAll(this.getUnoccupiedNeighbors(cell));
		}

		return unnocupiedCells;
	}

	/**
	 * Checks two cells are adjacent to each other
	 * 
	 * @param cell the first cell
	 * @param toCell the second cell
	 * @return indication of whether the two cells are adjacent to each other 
	 */
	public boolean isCellAdjacentTo(HantoCoordinate cell, HantoCoordinate toCell) {
		final int cellDifference = getCellDistance(cell, toCell);
		return cellDifference == 1;
	}

	/**
	 * Gets the distance between two cells on the board.
	 * 
	 * @param fromCell the first cell
	 * @param toCell the second cell
	 * @return the distance between two cells 
	 */
	public int getCellDistance(HantoCoordinate fromCell, HantoCoordinate toCell) {
		final int xDifference = fromCell.getX() - toCell.getX();
		final int yDifference = fromCell.getY() - toCell.getY();
		final int absXDifference = Math.abs(xDifference);
		final int absYDifference = Math.abs(yDifference);

		final int absSumOfDifferences = Math.abs(xDifference + yDifference);

		final int resultingDifference = Math.max(Math.max(absXDifference, absYDifference), absSumOfDifferences);

		return resultingDifference;
	}

	/**
	 * Checks if the cell is surrounded by Hanto pieces.
	 * 
	 * @param cell the cell to check
	 * @return indication of whether the cell is surrounded by Hanto pieces or not 
	 */
	public boolean isCellSurrounded(HantoCoordinate cell) {
		boolean cellSurrounded = true;
		final Collection<HantoCoordinate> surroundingCellsList = getSurroundingCells(cell);

		for(HantoCoordinate surroundingCell: surroundingCellsList) {
			if(isCellEmpty(surroundingCell)) {
				cellSurrounded = false;
			}
		}
		return cellSurrounded;
	}

	/**
	 * Gets the list of surrounding cells
	 * 
	 * @param cell the cell surrounding cells surround
	 * @return the list of surrounding cells
	 */
	private Collection<HantoCoordinate> getSurroundingCells(HantoCoordinate cell) {
		final Collection<HantoCoordinate> surroundingCells = new ArrayList<HantoCoordinate>();

		surroundingCells.add(new HantoBoardCoordinate(cell.getX() + 1, cell.getY()));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX() + 1, cell.getY() - 1));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX(), cell.getY() - 1));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX() - 1, cell.getY()));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX() - 1, cell.getY() + 1));
		surroundingCells.add(new HantoBoardCoordinate(cell.getX(), cell.getY() + 1));

		return surroundingCells;
	}

	/**
	 * Returns the number of common neighbors cells for the pair of coordinates.
	 * 
	 * @param firstCell the first coordinate
	 * @param secondCell the second coordinate
	 * @return the cells that are both neighbors of the first coordinate and the second coordinate.
	 */
	private Collection<HantoCoordinate> getCommonNeighborghCells(HantoCoordinate firstCell, HantoCoordinate secondCell) {		
		final Collection<HantoCoordinate> firstCellNeighbors = getSurroundingCells(firstCell);
		final Collection<HantoCoordinate> secondCellNeighbors = getSurroundingCells(secondCell);

		final Collection<HantoCoordinate> commonNeighborghCells = new ArrayList<HantoCoordinate>(firstCellNeighbors);

		commonNeighborghCells.retainAll(secondCellNeighbors); //get the common elements of both collections (in other words, intersection)

		return commonNeighborghCells;
	}

	/**
	 * Counts the number of common non-empty neighbors.
	 * 
	 * @param firstCell
	 * @param secondCell

	 * @return A Collection of occupied neighbors */
	public int countCommonOccupiedNeighbors(HantoCoordinate firstCell, HantoCoordinate secondCell) {
		Collection<HantoCoordinate> commonOccupiedNeighborghCells = getCommonNeighborghCells(firstCell, secondCell);

		commonOccupiedNeighborghCells = filterOutEmptyCells(commonOccupiedNeighborghCells);

		return commonOccupiedNeighborghCells.size();
	}

	/**
	 * Returns the list of cells that are adjacent to the specified cell and that are occupied.
	 * 
	 * @param cell the cell to check the neighbors relative to
	 * @return returns the collection of occupied neighbors
	 */
	private Collection<HantoCoordinate> getOccupiedNeighbors(HantoCoordinate cell) {
		Collection<HantoCoordinate> nonEmptyNeighbors = getSurroundingCells(cell);

		nonEmptyNeighbors = filterOutEmptyCells(nonEmptyNeighbors);

		return nonEmptyNeighbors;
	}

	/**
	 * Determines if the cell is critical (bridge node that may break the graph if moved) 
	 * for the current graph of non-empty cells.
	 * 
	 * @param cell the cell to check
	 * @return whether the cell is critical or not
	 */
	public boolean isCellCritical(HantoCoordinate cell) {
		boolean isCellCritical = true;

		int count = 1;

		final Collection<HantoCoordinate> occupiedNeighbors = getOccupiedNeighbors(cell);

		//create the collection that contains the non-empty cells that were already checked
		final Collection<HantoCoordinate> alreadyCheckedCells = new ArrayList<>(); 
		//get the first neighbor from collection and work with it
		alreadyCheckedCells.add(cell);

		count += countNeighbors((HantoCoordinate)(occupiedNeighbors.toArray())[0], alreadyCheckedCells);

		if (count == board.size()) {
			isCellCritical = false;
		}

		return isCellCritical;
	}

	/**
	 * Counts the number of unique neighbors recursively for a specific cell.
	 * 
	 * @param cell cell to count the neighbors for
	 * @param alreadyCheckedCells the list of the cells that were already checked
	 * @return the number of neighbors
	 */
	private int countNeighbors(HantoCoordinate cell, Collection<HantoCoordinate> alreadyCheckedCells) {
		int count = 0;
		
		count++; //count itself
		
		alreadyCheckedCells.add(cell);

		final Collection<HantoCoordinate> occupiedNeighbors = getOccupiedNeighbors(cell);

		for(HantoCoordinate coor : occupiedNeighbors) {
			if (!alreadyCheckedCells.contains(coor)) {
				count += countNeighbors(coor, alreadyCheckedCells);
			}
		}

		return count;
	}

	/**
	 * Filters out the cells that are empty, leaving only non-empty cells in the list.
	 * 
	 * @param listOfCoor list to filter
	 * @return filtered list
	 */
	private Collection<HantoCoordinate> filterOutEmptyCells(Collection<HantoCoordinate> listOfCoor) {
		final Collection<HantoCoordinate> occupiedCoor = new ArrayList<HantoCoordinate>();

		for(HantoCoordinate coor : listOfCoor) { //filter the list of the neighbors, so that it contains only occupied neighbors
			if (board.containsKey(coor)) {
				occupiedCoor.add(coor);
			}
		}

		return occupiedCoor;
	}

	/**
	 * Checks if the butterfly was surrounded.
	 * 
	 * @param color the color of the butterfly
	 * @return indication of whether the butterfly with specific color was surrounded
	 */
	public boolean isButterflySurrounded(HantoPlayerColor color) {
		final HantoPiece butterfly = new Butterfly(color);

		if (pieceToCoordsMapping.containsKey(butterfly)) {
			final List<HantoCoordinate> coordsList = pieceToCoordsMapping.get(new Butterfly(color));
			final HantoCoordinate butterflyCoor = coordsList.get(0);
			return isCellSurrounded(butterflyCoor);
		} else {
			return false;
		}
	}

	/**
	 * Gets piece at specific coordinate
	 * 
	 * @param where the coordinate
	 * @return the returned piece 
	 */
	public HantoPiece getPiece(HantoCoordinate where) {
		return board.get(where);
	}

	/**
	 * Returns a printable board.
	 * 
	 * @return a printable representation of the board.
	 */ 
	public String getPrintableBoard() {
		final StringBuilder output = new StringBuilder();

		for(Map.Entry<HantoCoordinate, HantoPiece> entry : board.entrySet()){
			HantoCoordinate key = entry.getKey();
			HantoPiece value = entry.getValue();

			String appendString = value.getColor().toString() + " " + value.getType().toString() + " at (" + Integer.toString(key.getX()) + ", " + Integer.toString(key.getY()) + ")\n";
			output.append(appendString);
		}

		return output.toString();
	}

	/**
	 * Moves the piece from the specified location to the specified location.
	 * 
	 * @param from the coordinate to move the piece from
	 * @param to the coordinate to move the piece to 
	 */
	public void movePiece(HantoCoordinate from, HantoCoordinate to) {
		final HantoPiece piece = this.removePiece(from);
		this.addPiece(to, piece);
	}

	/**
	 * Removes the piece from the board.
	 * 
	 * @param from the coordinate to remove the piece from
	 * @return the piece removed 
	 */
	public HantoPiece removePiece(HantoCoordinate from) {
		final HantoPiece piece = board.remove(from);
		pieceToCoordsMapping.get(piece).remove(from);
		return piece;
	}

	/**
	 * Gets the vector between two coordinates.
	 * 
	 * @param from the from coordinate
	 * @param to the to coordinate
	 * @return the difference between coordinates [vector]
	 */
	public HantoCoordinate getVector(HantoCoordinate from, HantoCoordinate to) {
		return new HantoBoardCoordinate(to.getX() - from.getX(), to.getY() - from.getY());
	}

	/**
	 * Gets the unit vector of a vector.
	 * 
	 * @param vector the vector to normalize
	 * @return the normalized vector
	 */
	public HantoCoordinate getUnitVector(HantoCoordinate vector) {
		HantoCoordinate origin = new HantoBoardCoordinate(0, 0);
		int cellDistance = this.getCellDistance(origin, vector);
		
		return new HantoBoardCoordinate(vector.getX()/cellDistance, vector.getY()/cellDistance);
	}
	
	/**
	 * Is the vector straight.
	 * 
	 * @param vector the vector to check
	 * @return if the vector is straight or not
	 */
	public boolean isStraight(HantoCoordinate vector) {
		return vector.getX() == 0 || vector.getY() == 0 || vector.getX() == -vector.getY();
	}
	
	/**
	 * Gets the number of pieces on the board.
	 * 
	 * @return the number of pieces on the board
	 */
	public int getNumPiecesOnBoard() {
		return board.size();
	}

	/**
	 * Determines if the piece at the specified cell matches 
	 * certain criteria specified by the input parameters. 
	 * 
	 * @param color HantoPlayerColor the color of the piece
	 * @param pieceType HantoPieceType the type of the piece
	 * @param coor HantoCoordinate the coordinate at which the piece that we would like to check is placed.
	 * @return boolean whether the piece matches the parameters or not
	 */
	public boolean pieceMatchesAtCell(HantoPlayerColor color, HantoPieceType pieceType, HantoCoordinate coor) {
		final HantoPiece piece = board.get(coor);

		return (color == piece.getColor()) && (piece.getType() == pieceType);
	}
}
