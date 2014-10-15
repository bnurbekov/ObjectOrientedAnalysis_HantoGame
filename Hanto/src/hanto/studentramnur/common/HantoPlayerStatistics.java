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

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.piece.HantoPieceFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that contain the statistics for the Hanto player. 
 * Also, contains the pieces that the player can place on the board.
 */
public class HantoPlayerStatistics {
	private final HantoPlayerColor color;
	private final HashMap<HantoPieceType, Integer> pieceTypeCounts;
	private int movesMade;

	/**
	 * The constructor for HantoPlayer class.
	 * 
	 * @param color the color of the player
	 */
	public HantoPlayerStatistics(HantoPlayerColor color) {
		this.color = color;
		pieceTypeCounts = new HashMap<HantoPieceType, Integer>();
		movesMade = 0;
	}

	/**
	 * Sets the count of how many pieces the specific player has.
	 * 
	 * @param piece the piece which count should be changed
	 * @param count the piece count to set the piece to
	 */
	public void setPieceCount(HantoPieceType piece, int count) {
		pieceTypeCounts.put(piece, count);
	}

	/**
	 * Gets the piece count.
	 * 
	 * @param piece the piece which count should be returned
	 * @return The number of remaining pieces of type piece.
	 */
	public int getPieceCount(HantoPieceType piece) {
		Integer pieceCount = pieceTypeCounts.get(piece);
		
		return (pieceCount == null) ? 0 : pieceCount;
	}

	/**
	 * Decrements the piece count for the specific field.
	 * 
	 * @param piece the piece which count should be decremented
	 * @return indication of whether the piece count was successfully decremented or not
	 */
	public boolean decrementPieceCount(HantoPieceType piece) {
		if(pieceTypeCounts.containsKey(piece) && pieceTypeCounts.get(piece) > 0) {
			pieceTypeCounts.put(piece, pieceTypeCounts.get(piece) - 1);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Increments the moves made by the player.
	 */
	public void incrementMovesMade() {
		movesMade++;
	}

	/**
	 * Gets the count of moves that were made by the current player.
	 * 
	 * @return move count
	 */
	public int getMovesMade() {
		return movesMade;
	}

	/**
	 * Gets the color of the current player.
	 * 
	 * @return current player color
	 */
	public HantoPlayerColor getColor() {
		return color;
	}

	/**
	 * Indicates whether the player has placed the butterfly piece or not.
	 * 
	 * @return indication of whether the player has placed the butterfly piece or not.
	 */
	public boolean hasPlacedButterfly() {
		final boolean playerHasPlacedButterfly = (pieceTypeCounts.get(HantoPieceType.BUTTERFLY) == 0);
		return playerHasPlacedButterfly;
	}

	/**
	 * Checks if the player has any more pieces left.
	 * 
	 * @return indication of whether the player has any more pieces left
	 */
	public boolean hasPieces() {
		boolean hasPieces = false;

		//iterate through the hash map and check the count
		for(Map.Entry<HantoPieceType, Integer> entry : pieceTypeCounts.entrySet()){
			if (entry.getValue() != 0) {
				hasPieces = true;
			}
		}

		return hasPieces;
	}

	/**
	 * Sets the turn number.
	 * 
	 * @param turnNumber the turn number
	 */
	public void setMovesMade(int turnNumber) {
		movesMade = turnNumber;
	}

	/**
	 * Gets all the pieces that were not yet added to the board.
	 * 
	 * @return all the pieces that were not yet added to the board
	 */
	public Collection<HantoPiece> getNotAddedPieces() {
		Collection<HantoPiece> notAddedPieceList = new ArrayList<>();
		
		for(Map.Entry<HantoPieceType, Integer> entry : pieceTypeCounts.entrySet()) {
			for (int i = 0; i < entry.getValue(); i++) {
				notAddedPieceList.add(HantoPieceFactory.getInstance().createPiece(color, entry.getKey()));
			}
		}
		
		return notAddedPieceList;
	}
}
