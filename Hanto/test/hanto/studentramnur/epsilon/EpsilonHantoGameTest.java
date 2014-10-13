/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.epsilon;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.CRAB;
import static hanto.common.HantoPieceType.SPARROW;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.common.MoveResult.BLUE_WINS;
import static hanto.common.MoveResult.OK;
import static hanto.common.MoveResult.RED_WINS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoTestCoordinate;
import hanto.studentramnur.common.HantoTestGame;
import hanto.studentramnur.common.HantoTestGameFactory;
import hanto.studentramnur.common.PieceLocationPair;
import hanto.studentramnur.epsilon.EpsilonHantoGame;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * The class that is responsible for Delta Hanto Game tests.
 * 
 * @author Shadi
 * @author Batyr
 */
public class EpsilonHantoGameTest {

	private HantoTestGame game;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Initializes private variables before each test method.
	 */
	@Before
	public void setUp() {
		game = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO);
	}

	/**
	 * Test the initialization of the fields.
	 */
	@Test
	public void testInitialization() {
		assertNotNull(game);
		assertTrue(game instanceof EpsilonHantoGame);
	}

	/**
	 * Test valid first blue move.
	 * 
	 * @throws HantoException */
	@Test
	public void blueMakesValidFirstMove() throws HantoException {
		assertEquals(OK, game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0)));
		final HantoPiece piece = game.getPieceAt(new HantoTestCoordinate(0, 0));
		assertEquals(BUTTERFLY, piece.getType());
		assertEquals(BLUE, piece.getColor());
		System.out.println(game.getPrintableBoard());
	}

	/**
	 * Test invalid first blue move.
	 * 
	 * @throws HantoException */
	@Test
	public void blueMakesInvalidFirstMove1() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Invalid Move.");

		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(1, 0));
	}

	/**
	 * Test invalid first blue move.
	 * 
	 * @throws HantoException */
	@Test
	public void blueMakesInvalidFirstMove2() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Invalid Move.");

		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1));
	}

	/**
	 * Test invalid first blue move.
	 * 
	 * @throws HantoException */
	@Test
	public void blueMakesInvalidFirstMove3() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Invalid Move.");

		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(1, 1));
	}

	/**
	 * Blue forfeits
	 * 
	 * @throws HantoException */
	@Test
	public void blueForfeits() throws HantoException {	
		assertEquals(RED_WINS, game.makeMove(BUTTERFLY, null, null));
	}

	/**
	 * Red forfeits
	 * 
	 * @throws HantoException */
	@Test
	public void redForfeits() throws HantoException {
		game.setPlayerMoving(RED);
		assertEquals(BLUE_WINS, game.makeMove(BUTTERFLY, null, null));
	}

	/**
	 * Blue moves a piece off the board.
	 * 
	 * @throws HantoException */
	@Test
	public void movesPieceOffBoard() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("To location must be on board.");

		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1));
		game.makeMove(BUTTERFLY, new HantoTestCoordinate(0, 0), null);
	}

	/**
	 * Move a piece a player does not have.
	 * 
	 * @throws HantoException */
	@Test
	public void movePiecePlayerDoesNotHave() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Player does not have that piece to add.");

		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, -1));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, 1));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, -2));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, 2));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, -3));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, 3));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, -4));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, 4));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, -5));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, 5));
	}

	/**
	 * Check if player must place a butterfly.

	 * @throws HantoException */
	@Test
	public void playerMustPlaceButterfly() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Player must place a butterfly.");

		game.makeMove(CRAB, null, new HantoTestCoordinate(0, 0));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, -1));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, 1));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, -2));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, 2));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, -3));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, 3));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, -4));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, 4));
	}

	/**
	 * Method blueMovesButterfly.
	 * @throws HantoException
	 */
	@Test
	public void blueMovesButterfly() throws HantoException {
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1));
		game.makeMove(BUTTERFLY, new HantoTestCoordinate(0, 0), new HantoTestCoordinate(1, 0));
	}

	/**
	 * Move a piece on top of a piece.
	 * 
	 * @throws HantoException */
	@Test
	public void moveAPieceOntoAPiece() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Invalid Move.");

		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1));
		game.makeMove(BUTTERFLY, new HantoTestCoordinate(0, 0), new HantoTestCoordinate(0, 1));
	}

	/**
	 * Move a piece to float.
	 * @throws HantoException
	 */
	@Test
	public void moveAPieceToFloat() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Invalid Move.");

		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1));
		game.makeMove(BUTTERFLY, new HantoTestCoordinate(0, 0), new HantoTestCoordinate(-2, 2));
	}


	/**
	 * Checks if red wins when the blue butterfly is surrounded.
	 * @throws HantoException
	 * */
	@Test
	public void blueButterflyIsSurrounded() throws HantoException {
		final PieceLocationPair[] initialPieces = new PieceLocationPair[7];

		initialPieces[0] = new PieceLocationPair(BLUE, SPARROW, new HantoTestCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(RED, BUTTERFLY, new HantoTestCoordinate(1, 0));
		initialPieces[2] = new PieceLocationPair(BLUE, BUTTERFLY, new HantoTestCoordinate(0, 1));
		initialPieces[3] = new PieceLocationPair(BLUE, SPARROW, new HantoTestCoordinate(1, 1));
		initialPieces[4] = new PieceLocationPair(RED, SPARROW, new HantoTestCoordinate(2, 0));
		initialPieces[5] = new PieceLocationPair(RED, SPARROW, new HantoTestCoordinate(2, -1));
		initialPieces[6] = new PieceLocationPair(BLUE, SPARROW, new HantoTestCoordinate(0, -1));

		game.initializeBoard(initialPieces);
		game.setPlayerMoving(BLUE);

		assertEquals(BLUE_WINS, game.makeMove(SPARROW, new HantoTestCoordinate(0, -1), new HantoTestCoordinate(1, -1)));
	}

	/**
	 * Checks if blue wins when the red butterfly is surrounded.
	 * @throws HantoException
	 * */
	@Test
	public void redButterflyIsSurrounded() throws HantoException {
		final PieceLocationPair[] initialPieces = new PieceLocationPair[7];

		initialPieces[0] = new PieceLocationPair(RED, SPARROW, new HantoTestCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(BLUE, BUTTERFLY, new HantoTestCoordinate(1, 0));
		initialPieces[2] = new PieceLocationPair(RED, BUTTERFLY, new HantoTestCoordinate(0, 1));
		initialPieces[3] = new PieceLocationPair(RED, SPARROW, new HantoTestCoordinate(1, 1));
		initialPieces[4] = new PieceLocationPair(BLUE, SPARROW, new HantoTestCoordinate(2, 0));
		initialPieces[5] = new PieceLocationPair(BLUE, SPARROW, new HantoTestCoordinate(2, -1));
		initialPieces[6] = new PieceLocationPair(RED, SPARROW, new HantoTestCoordinate(0, -1));

		game.initializeBoard(initialPieces);
		game.setPlayerMoving(RED);

		assertEquals(RED_WINS, game.makeMove(SPARROW, new HantoTestCoordinate(0, -1), new HantoTestCoordinate(1, -1)));
	}

	/**
	 * Method blueMovesSparrow.
	 * @throws HantoException
	 */
	@Test
	public void blueMovesSparrow() throws HantoException {
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1));
		game.makeMove(SPARROW, null, new HantoTestCoordinate(0, -1));
		game.makeMove(SPARROW, null, new HantoTestCoordinate(0, 2));
		final MoveResult result = game.makeMove(SPARROW, new HantoTestCoordinate(0, -1), new HantoTestCoordinate(-1, 0));
		assertEquals(OK, result);
		final HantoPiece piece = game.getPieceAt(new HantoTestCoordinate(-1, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(SPARROW, piece.getType());
	}

	/**
	 * Method gameDoesNotEndAfter1000Moves.
	 * @throws HantoException
	 */
	@Test
	public void gameDoesNotEndAfter1000Moves() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] {
				new PieceLocationPair(BLUE, BUTTERFLY, new HantoTestCoordinate(0, 0)), new PieceLocationPair(RED, BUTTERFLY, new HantoTestCoordinate(0, 1)),
				new PieceLocationPair(BLUE, SPARROW, new HantoTestCoordinate(0, -1)), new PieceLocationPair(RED, SPARROW, new HantoTestCoordinate(0, 2))
		};
		game.initializeBoard(board);
		game.setPlayerMoving(RED);
		game.setTurnNumber(1000);
		assertEquals(OK, game.makeMove(SPARROW, new HantoTestCoordinate(0, 2), new HantoTestCoordinate(1, 1)));
	}

	/**
	 * Checks breaking of a chain.
	 * @throws HantoException
	 */
	@Test
	public void breakChain() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Invalid Move.");

		final PieceLocationPair[] initialPieces = new PieceLocationPair[7];

		initialPieces[0] = new PieceLocationPair(RED, SPARROW, new HantoTestCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(BLUE, BUTTERFLY, new HantoTestCoordinate(0, -1));
		initialPieces[2] = new PieceLocationPair(RED, BUTTERFLY, new HantoTestCoordinate(0, 1));
		initialPieces[3] = new PieceLocationPair(RED, SPARROW, new HantoTestCoordinate(1, 1));
		initialPieces[4] = new PieceLocationPair(BLUE, SPARROW, new HantoTestCoordinate(0, -2));
		initialPieces[5] = new PieceLocationPair(BLUE, SPARROW, new HantoTestCoordinate(-1, -2));
		initialPieces[6] = new PieceLocationPair(RED, SPARROW, new HantoTestCoordinate(2, 1));

		game.initializeBoard(initialPieces);
		game.setPlayerMoving(RED);

		game.makeMove(BUTTERFLY, new HantoTestCoordinate(0, 1), new HantoTestCoordinate(-1, 2));
	}
	
	/**
	 * Moves a crab.
	 * @throws HantoException
	 */
	@Test
	public void moveCrab() throws HantoException {
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1));
		game.makeMove(SPARROW, null, new HantoTestCoordinate(0, -1));
		game.makeMove(SPARROW, null, new HantoTestCoordinate(0, 2));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, -2));
		game.makeMove(CRAB, null, new HantoTestCoordinate(0, 3));
		
		game.makeMove(CRAB, new HantoTestCoordinate(0, -2), new HantoTestCoordinate(1, -2));
	}
}
