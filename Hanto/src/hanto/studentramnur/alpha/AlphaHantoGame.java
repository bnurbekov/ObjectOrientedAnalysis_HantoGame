/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.HantoPieceFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for alpha Hanto game.
 */
public class AlphaHantoGame implements HantoGame {
	private HantoPlayerColor currentPlayerColor;
	private Map<HantoCoordinate, HantoPiece> board; 

	/**
	 * Constructor for the alpha Hanto game.
	 */
	public AlphaHantoGame() {
		currentPlayerColor = HantoPlayerColor.BLUE;
		board = new HashMap<HantoCoordinate, HantoPiece>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to) throws HantoException {

		if (pieceType != HantoPieceType.BUTTERFLY) throw new HantoException("Only butterflies are allowed for this game.");
		if (from != null) throw new HantoException("The only move allowed is to add a Butterfly.");

		if (to == null) {
			throw new HantoException("To coordinates cannot be null.");
		} else {
			to = new HantoBoardCoordinate(to);
		}

		HantoPiece piece = HantoPieceFactory.getInstance().createPiece(currentPlayerColor, pieceType);
		MoveResult result = MoveResult.OK;

		if (board.isEmpty()) {
			if (!((to.getX() == 0) && (to.getY() == 0))) {
				throw new HantoException("The first move should always be placed at (0, 0).");
			}

			board.put(to, piece);
			result = MoveResult.OK;
			System.out.println(currentPlayerColor + " player adding : " + piece.getColor() + " " + piece.getType() + " at (" + to.getX() + ", " + to.getY() + ")");
			currentPlayerColor = HantoPlayerColor.RED;
		} else if(isAdjacentToOrigin(to)) {
			board.put(to, piece);
			result = MoveResult.DRAW;
			System.out.println(currentPlayerColor + " player adding : " + piece.getColor() + " " + piece.getType() + " at (" + to.getX() + ", " + to.getY() + ")");
		} else {
			throw new HantoException("Move is invalid.");
		}

		System.out.println("Move result: " + result.toString());
		return result;
	}

	/**
	 * Checks if the cell is adjacent to the origin.
	 * 
	 * @param cellToCheck cell to check
	 * @return indication of whether the field is adjacent to the origin or not
	 */
	private boolean isAdjacentToOrigin(HantoCoordinate cellToCheck) {
		return getCellDistance(cellToCheck, new HantoBoardCoordinate(0, 0)) == 1;
	}

	/**
	 * Gets the distance between two cells
	 * 
	 * @param firstCell the first cell
	 * @param secondCell the second cell
	 * @return the difference between two cells
	 */
	private int getCellDistance(HantoCoordinate firstCell, HantoCoordinate secondCell) {
		int xDifference = firstCell.getX() - secondCell.getX();
		int yDifference = firstCell.getY() - secondCell.getY();
		int absXDifference = Math.abs(xDifference);
		int absYDifference = Math.abs(yDifference);

		int absSumOfDifferences = Math.abs(xDifference + yDifference);

		int resultingDifference = Math.max(Math.max(absXDifference, absYDifference), absSumOfDifferences);

		return resultingDifference;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		where = new HantoBoardCoordinate(where);
		return board.get(where);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrintableBoard() {
		StringBuilder output = new StringBuilder();

		for(Map.Entry<HantoCoordinate, HantoPiece> entry : board.entrySet()){
			HantoCoordinate key = entry.getKey();
			HantoPiece value = entry.getValue();

			String appendString = value.getColor().toString() + " " + value.getType().toString() + " at (" + Integer.toString(key.getX()) + ", " + Integer.toString(key.getY()) + ")\n";
			output.append(appendString);
		}

		return output.toString();
	}
}
