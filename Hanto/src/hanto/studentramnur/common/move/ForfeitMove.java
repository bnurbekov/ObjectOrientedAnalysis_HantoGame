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
 * Forfeit move class that is responsible for the move that allows player to forfeit.
 * 
 * @author Batyr and Shadi
 *
 */
public class ForfeitMove extends Move {

	public ForfeitMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.FORFEIT);
	}

	@Override
	public boolean validate(HantoBoard board) {
		return true;
	}

	@Override
	public void execute(HantoBoard board) {
		result = (color == HantoPlayerColor.BLUE) ? MoveResult.RED_WINS : MoveResult.BLUE_WINS;
	}
}
