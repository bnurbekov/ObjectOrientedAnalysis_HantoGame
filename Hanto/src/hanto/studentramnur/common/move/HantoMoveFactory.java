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
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * This is a singleton class that provides a factory to create an instance of any version
 * of a Hanto piece.
 */
public class HantoMoveFactory {
	private static final HantoMoveFactory INSTANCE = new HantoMoveFactory();

	/**
	 * Default private descriptor.
	 */
	private HantoMoveFactory()
	{
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * Gets the instance of the factory.
	 * 
	 * @return the instance
	 */
	public static HantoMoveFactory getInstance()
	{
		return INSTANCE;
	}

	/**
	 * Determines the piece that should be created based on the input parameters.
	 * 
	 * @param id The HantoGameID
	 * @param color the color of the piece that should be created
	 * @param pieceType the type of the piece that should be created 
	 * @param from The from coordinate
	 * @param to The to coordinate
	 * @return the created piece
	 * @throws HantoException 
	 */
	public Move createMove(HantoGameID id, HantoPlayerColor color, HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to) throws HantoException {
		Move move;

		if(id == HantoGameID.EPSILON_HANTO) { // Epsilon Hanto
			if(from == null && to == null) {
				move = new ForfeitMove(color, pieceType, from, to);
			} else if(from == null) {
				move = new AddMove(color, pieceType, from, to);
			} else if(to == null) {
				throw new HantoException("To location must be on board.");
			} else { // player is moving
				switch (pieceType) {
				case BUTTERFLY:
					move = new WalkMove(color, pieceType, from, to);
					break;
				case CRAB:
					move = new WalkMove(color, pieceType, from, to);
					break;
				case SPARROW:
					move = new FlyMove(color, pieceType, from, to);
					break;
				case HORSE:
					move = new JumpMove(color, pieceType, from, to);
					break;
				default:
					throw new HantoException("No such piece type.");
				}
			}
		} else {
			throw new HantoException("Not a valid HantoGameID.");
		}

		return move;
	}
}
