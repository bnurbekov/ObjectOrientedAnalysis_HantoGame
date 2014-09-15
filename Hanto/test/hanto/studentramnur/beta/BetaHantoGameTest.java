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

import static hanto.common.HantoPieceType.BUTTERFLY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hanto.HantoGameFactory;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoBoardCoordinate;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests BetaHantoGame class.
 * 
 * @author Batyr
 *
 */
public class BetaHantoGameTest {
	private HantoGameFactory factory;
	private HantoGame game;

	/**
	 * Initializes private variables before each test method.
	 */
	@Before
	public void setUp() {
		factory = HantoGameFactory.getInstance();
		game = factory.makeHantoGame(HantoGameID.BETA_HANTO);
	}
	
	/**
	 * Makes sure that BetaHantoGame was initialized by the factory.
	 */
	@Test
	public void getABetaHantoGameFromTheFactory()
	 {
		 assertTrue(game instanceof BetaHantoGame);
	 }

	/**
	 * Checks that the exception is thrown if the players don't put the butterfly
	 * during the first four turns.
	 * 
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void playersDontPutButterfly() throws HantoException {
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, 0));
	}
	
	/**
	 * Checks if the game is successfully ended in a draw, if player obeys the rules
	 * and neither of two butterflies is surrounded.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void playersPlayDraw() throws HantoException {
		MoveResult result;
		
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -1));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 2));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -2));
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -2));
		
		assertEquals(MoveResult.DRAW, result);
	}
	
	/**
	 * Checks if the game ends in a draw if two butterflies are surrounded simultaneously.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void playersPlayDrawAndButterfliesAreSurrounded() throws HantoException {
		MoveResult result;
		
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, 1));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, -1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -2));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -2));
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -1));
		
		assertEquals(MoveResult.DRAW, result);
	}
	
	/**
	 * Checks if the exception is thrown if the player tries to place one more piece
	 * after the game has ended.
	 * 
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void playerTriesToPlayAfterTheEndOfGame() throws HantoException {
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -1));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 2));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -2));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -2));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -2));
	}
	
	/**
	 * Checks if red wins when the blue butterfly is surrounded.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void blueButterflyIsSurrounded() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(0, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -1));
		MoveResult result = game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, 0));
		
		assertEquals(MoveResult.RED_WINS, result);
	}
	
	/**
	 * * Checks if blue wins when the red butterfly is surrounded.
	 * 	
	 * @throws HantoException
	 */
	@Test
	public void redButterflyIsSurrounded() throws HantoException {
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(0, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -1));
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 0));
		
		assertEquals(MoveResult.BLUE_WINS, result);
	}
	
	/**
	 * Checks if the blue butterfly is at (0, 0) after the first move.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void afterFirstMoveBlueButterflyIsAt0_0() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoBoardCoordinate(0, 0));
		final HantoPiece p = game.getPieceAt(new HantoBoardCoordinate(0, 0));
		assertEquals(BUTTERFLY, p.getType());
		assertEquals(HantoPlayerColor.BLUE, p.getColor());
	}
	
	/**
	 * Checks if the string returned by the getPrintableBoard method is not null.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void getPrintableBoardDoesntReturnNull() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoBoardCoordinate(0, 0));
		String printableBoard = game.getPrintableBoard();
		
		assertNotNull(printableBoard);
	}
	
	/**
	 * Checks if the exception is thrown when red places piece non adjacent to other pieces in the game.
	 * 
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void redPlacesPieceNonAdjacentToOtherPieces() throws HantoException
	{
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(0, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(3, 0));
	}
	
	/**
	 * Checks if exception is thrown when blue attempts to place butterfly at wrong location.
	 * 
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void blueAttemptsToPlaceButterflyAtWrongLocation() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoBoardCoordinate(-1, 1));
	}
	
	/**
	 * Checks if the exception is thrown when blue tries to place the piece
	 * that is not allowed in this game.
	 * 
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void bluePlacesCrane() throws HantoException {
		game.makeMove(HantoPieceType.CRANE, null, new HantoBoardCoordinate(0, 1));
	}
	
	/**
	 * Checks if the exception is thrown when player attempts to move rather than place.
	 * 
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToMoveRatherThanPlace() throws HantoException
	{
		game.makeMove(BUTTERFLY, new HantoBoardCoordinate(0, 1), new HantoBoardCoordinate(0, 0));
	}
	
	/**
	 * Checks if the exception is thrown when player attempts to move beyond the board.
	 * 
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToMoveBeyondTheBoard() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, null);
	}
}
