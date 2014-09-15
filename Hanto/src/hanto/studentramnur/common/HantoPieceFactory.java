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

/**
 * This is a singleton class that provides a factory to create an instance of any version
 * of a Hanto piece.
 * 
 * @author Batyr
 */
public class HantoPieceFactory {
	private static final HantoPieceFactory INSTANCE = new HantoPieceFactory();
	
	/**
	 * Default private descriptor.
	 */
	private HantoPieceFactory()
	{
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static HantoPieceFactory getInstance()
	{
		return INSTANCE;
	}
	
	public HantoPiece createPiece(HantoPlayerColor color, HantoPieceType type) {
		HantoPiece piece;
		
		switch (type) {
			case BUTTERFLY:
				piece = new Butterfly(color);
				break;
			case SPARROW:
				piece = new Sparrow(color);
				break;
			default: 
				piece = new Butterfly(color);
				break;
		}
		
		return piece;
	}
}
