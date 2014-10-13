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

/**
 * A data structure used in setting up the the initial configuration of a game
 * for testing.
 * @version Sep 21, 2014
 */
public class PieceCoordinatePair {
	private final HantoPiece piece;
	private final HantoCoordinate location;

	/**
	 * Default constructor
	 * @param player the player color
	 * @param pieceType the piece type
	 * @param location the coordinate where the piece is at the beginning of the test
	 */
	public PieceCoordinatePair(HantoPiece piece,
			HantoCoordinate location) {
		this.piece = piece;
		this.location = location;
	}

	public HantoCoordinate getLocation() {
		return location;
	}

	public HantoPiece getPiece() {
		return piece;
	}
}
