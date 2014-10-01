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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that contain the statistics for the Hanto player. 
 * Also, contains the pieces that the player can place on the board.
 */
public class HantoPlayer {
	private HantoPlayerColor color;
	private HashMap<HantoPieceType, Integer> pieces;
	private int movesMade;

	/**
	 * The constructor for HantoPlayer class.
	 * 
	 * @param color the color of the player
	 */
	public HantoPlayer(HantoPlayerColor color) {
		this.color = color;
		pieces = new HashMap<HantoPieceType, Integer>();
		movesMade = 0;
	}

	/**
	 * Sets the count of how many pieces the specific player has.
	 * 
	 * @param piece the piece which count should be changed
	 * @param count the piece count to set the piece to
	 */
	public void setPieceCount(HantoPieceType piece, int count) {
		pieces.put(piece, count);
	}

	/**
	 * Gets the piece count.
	 * 
	 * @param piece the piece which count should be returned
	 * @return The number of remaining pieces of type piece.
	 */
	public int getPieceCount(HantoPieceType piece) {
		return pieces.getOrDefault(piece, 0);
	}

	/**
	 * Decrements the piece count for the specific field.
	 * 
	 * @param piece the piece which count should be decremented
	 * @return indication of whether the piece count was successfully decremented or not
	 */
	public boolean decrementPieceCount(HantoPieceType piece) {
		if(pieces.containsKey(piece) && pieces.get(piece) > 0) {
			pieces.put(piece, pieces.get(piece) - 1);
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
		boolean playerHasPlacedButterfly = (pieces.get(HantoPieceType.BUTTERFLY) == 0);
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
		for(Map.Entry<HantoPieceType, Integer> entry : pieces.entrySet()){
			if (entry.getValue() != 0) {
				hasPieces = true;
			}
		}

		return hasPieces;
	}

	public void setMovesMade(int turnNumber) {
		movesMade = turnNumber;
	}
}
