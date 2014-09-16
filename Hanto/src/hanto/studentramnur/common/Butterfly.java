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

/**
 * Class for the butterfly piece.
 */
public class Butterfly extends AbstractHantoPiece {

	/**
	 * Constructor for Butterfly class.
	 * 
	 * @param color the color of the piece.
	 */
	public Butterfly(HantoPlayerColor color) {
		super(color, HantoPieceType.BUTTERFLY);
	}
}
