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
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.HantoGameFactory;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.TestHantoCoordinate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests BetaHantoGame class.
 */
public class BetaHantoGameTest {
	private HantoGameFactory factory;
	private HantoGame game;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Initializes private variables before each test method.
	 */
	@Before
	public void setUp() {
		factory = HantoGameFactory.getInstance();
		game = factory.makeHantoGame(HantoGameID.BETA_HANTO);
	}

	/**
	 * Test the initialization of the fields.
	 */
	@Test
	public void testInitialization() {
		assertNotNull(factory);
		assertNotNull(game);
		assertTrue(game instanceof BetaHantoGame);
	}
	
	/**
	 * Test valid first move.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void blueMakesValidFirstMove() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0)));
		final HantoPiece piece = game.getPieceAt(new TestHantoCoordinate(0, 0));
		assertEquals(BUTTERFLY, piece.getType());
		assertEquals(HantoPlayerColor.BLUE, piece.getColor());
		System.out.println(game.getPrintableBoard());
	}

	/**
	 * Test invalid first move.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void blueMakesInvalidFirstMove1() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("The first move should always be placed at (0, 0).");

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(1, 0));
	}

	/**
	 * Test invalid first move.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void blueMakesInvalidFirstMove2() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("The first move should always be placed at (0, 0).");

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1));
	}

	/**
	 * Test invalid first move.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void blueMakesInvalidFirstMove3() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("The first move should always be placed at (0, 0).");

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(1, 1));
	}

	/**
	 * Test move with null coordinates
	 * @throws HantoException
	 */
	@Test
	public void moveWithNullCoor() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("To coordinates cannot be null.");

		game.makeMove(BUTTERFLY, null, null);
	}

	/**
	 * Test move with null HantoPieceType
	 * @throws HantoException
	 */
	@Test
	public void moveWithNullHantoPieceType() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Only butterflies and sparrows are allowed for this game.");

		game.makeMove(null, null, new TestHantoCoordinate(0, 0));
	}

	/**
	 * Test moving a non existent piece on the board (moving is illegal).
	 * @throws HantoException
	 */
	@Test
	public void moveNonExistingPiece() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("You cannot move pieces.");

		game.makeMove(BUTTERFLY, new TestHantoCoordinate(0, 0), new TestHantoCoordinate(0, 1));
	}

	/**
	 * Test moving a piece on the board (moving is illegal).
	 * @throws HantoException
	 */
	@Test
	public void moveExistingPiece() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("You cannot move pieces.");

		assertEquals(MoveResult.OK, game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0)));
		game.makeMove(BUTTERFLY, new TestHantoCoordinate(0, 0), new TestHantoCoordinate(0, 1));
	}

	/**
	 * Checks that the exception is thrown if the blue player does not put a butterfly
	 * during the first four turns.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void bluePlayerDoesNotPlaceButterfly() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0)));	// Blue
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1)));	// Red

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1)));	// Blue
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 0)));	// Red

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 1)));	// Blue
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -1)));	// Red

		thrown.expect(HantoException.class);
		thrown.expectMessage("BLUE player must place a butterfly.");

		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, 0));	// Blue
	}

	/**
	 * Checks that the exception is thrown if the red player does not put a butterfly
	 * during the first four turns.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void redPlayerDoesNotPlaceButterfly() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0)));	// Blue
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1)));	// Red

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1)));	// Blue
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 0)));	// Red

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 1)));	// Blue
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -1)));	// Red

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 0)));	// Blue

		thrown.expect(HantoException.class);
		thrown.expectMessage("RED player must place a butterfly.");

		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -1));	// Red
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

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 2)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -2)));
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

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, -1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -2)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -2)));
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -1));

		assertEquals(MoveResult.DRAW, result);
	}

	/**
	 * Checks if the exception is thrown if the player tries to place one more piece
	 * after the game has ended.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void playerTriesToPlayAfterTheEndOfGame() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 2)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(2, -2)));
		assertEquals(MoveResult.DRAW, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -2)));
		
		thrown.expect(HantoException.class);
		thrown.expectMessage("Game is already over.");
		
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -2));
	}

	/**
	 * Checks if red wins when the blue butterfly is surrounded.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void blueButterflyIsSurrounded() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(0, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, -1)));
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
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(0, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(-1, 2)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 2)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(1, 1)));
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 0));

		assertEquals(MoveResult.BLUE_WINS, result);
	}

	/**
	 * Checks if the blue butterfly is at (0, 0) after the first move.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void afterFirstMoveBlueButterflyIsAt0_0() throws HantoException {
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
	public void getPrintableBoardDoesntReturnNull() throws HantoException {
		game.makeMove(BUTTERFLY, null, new HantoBoardCoordinate(0, 0));
		String printableBoard = game.getPrintableBoard();

		assertNotNull(printableBoard);
	}

	/**
	 * Checks if the exception is thrown when red places piece non adjacent to other pieces in the game.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void redPlacesPieceNonAdjacentToOtherPieces() throws HantoException {
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(0, 1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1));
		
		thrown.expect(HantoException.class);
		thrown.expectMessage("Move is invalid.");
		
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(3, 0));
	}

	/**
	 * Checks if the exception is thrown when blue tries to place the piece
	 * that is not allowed in this game.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void bluePlacesCrane() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Only butterflies and sparrows are allowed for this game.");
		
		game.makeMove(HantoPieceType.CRANE, null, new HantoBoardCoordinate(0, 1));
	}
	
	/**
	 * Add a piece onto another piece.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void redPlacesPieceOntopOfOtherPiece() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(0, 1)));
		
		thrown.expect(HantoException.class);
		thrown.expectMessage("Move is invalid.");
		
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 0));
	}
}
