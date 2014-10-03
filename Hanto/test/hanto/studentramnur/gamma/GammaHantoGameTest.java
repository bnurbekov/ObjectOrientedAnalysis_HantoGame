/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.gamma;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.CRAB;
import static hanto.common.HantoPieceType.SPARROW;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.common.MoveResult.DRAW;
import static hanto.common.MoveResult.OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoTestCoordinate;
import hanto.studentramnur.common.HantoTestGame;
import hanto.studentramnur.common.HantoTestGameFactory;
import hanto.studentramnur.common.PieceLocationPair;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
<<<<<<< HEAD
 * The class that is responsible for Gamma Hanto Game tests.
 * 
 * @author Batyr and Shadi
 *
=======
 * @author Shadi
 * @author Batyr
>>>>>>> 209a7b202503cc3239b9d459aad60eeeff57aa2b
 */
public class GammaHantoGameTest {

	private HantoTestGame game;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Initializes private variables before each test method.
	 */
	@Before
	public void setUp() {
		game = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO);
	}

	/**
	 * Test the initialization of the fields.
	 */
	@Test
	public void testInitialization() {
		assertNotNull(game);
		assertTrue(game instanceof GammaHantoGame);
	}

	/**
	 * Test valid first blue move.
	 * 

	 * @throws HantoException */
	@Test
	public void blueMakesValidFirstMove() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0)));
		final HantoPiece piece = game.getPieceAt(new HantoTestCoordinate(0, 0));
		assertEquals(BUTTERFLY, piece.getType());
		assertEquals(HantoPlayerColor.BLUE, piece.getColor());
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

	 * @throws HantoException */
	@Test
	public void blueForfeits() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Forfeiting is not allowed in Gamma Hanto.");

		assertEquals(MoveResult.RED_WINS, game.makeMove(BUTTERFLY, null, null));
	}

	/**
	 * Blue moves a piece off the board.

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

	 * @throws HantoException */
	@Test
	public void movePiecePlayerDoesNotHave() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Player does not have that piece to add.");

		game.makeMove(CRAB, null, new HantoTestCoordinate(0, 0));
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

	 * @throws HantoException */
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
	 * 

	 * @throws HantoException */
	@Test
	public void blueButterflyIsSurrounded() throws HantoException {
		final PieceLocationPair[] initialPieces = new PieceLocationPair[7];

		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoTestCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoTestCoordinate(1, 0));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoTestCoordinate(0, 1));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoTestCoordinate(1, 1));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoTestCoordinate(2, 0));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoTestCoordinate(2, -1));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoTestCoordinate(0, -1));

		game.initializeBoard(initialPieces);
		game.setPlayerMoving(HantoPlayerColor.BLUE);

		assertEquals(MoveResult.BLUE_WINS, game.makeMove(SPARROW, new HantoTestCoordinate(0, -1), new HantoTestCoordinate(1, -1)));
	}

	/**
	 * Checks if blue wins when the red butterfly is surrounded.
	 * 

	 * @throws HantoException */
	@Test
	public void redButterflyIsSurrounded() throws HantoException {
		final PieceLocationPair[] initialPieces = new PieceLocationPair[7];

		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoTestCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoTestCoordinate(1, 0));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoTestCoordinate(0, 1));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoTestCoordinate(1, 1));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoTestCoordinate(2, 0));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoTestCoordinate(2, -1));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoTestCoordinate(0, -1));

		game.initializeBoard(initialPieces);
		game.setPlayerMoving(HantoPlayerColor.RED);

		assertEquals(MoveResult.RED_WINS, game.makeMove(SPARROW, new HantoTestCoordinate(0, -1), new HantoTestCoordinate(1, -1)));
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
	 * Method gameEndsInDrawAfter20Moves.
	 * @throws HantoException
	 */
	@Test
	public void gameEndsInDrawAfter20Moves() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] {
				new PieceLocationPair(BLUE, BUTTERFLY, new HantoTestCoordinate(0, 0)), new PieceLocationPair(RED, BUTTERFLY, new HantoTestCoordinate(0, 1)),
				new PieceLocationPair(BLUE, SPARROW, new HantoTestCoordinate(0, -1)), new PieceLocationPair(RED, SPARROW, new HantoTestCoordinate(0, 2))
		};
		game.initializeBoard(board);
		game.setPlayerMoving(RED);
		game.setTurnNumber(20);
		assertEquals(DRAW, game.makeMove(SPARROW, new HantoTestCoordinate(0, 2), new HantoTestCoordinate(1, 1)));
	}

	/**
	 * Method gameIsAlreadyOver.
	 * @throws HantoException
	 */
	@Test
	public void gameIsAlreadyOver() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Game is already over.");

		final PieceLocationPair[] board = new PieceLocationPair[] {
				new PieceLocationPair(BLUE, BUTTERFLY, new HantoTestCoordinate(0, 0)), new PieceLocationPair(RED, BUTTERFLY, new HantoTestCoordinate(0, 1)),
				new PieceLocationPair(BLUE, SPARROW, new HantoTestCoordinate(0, -1)), new PieceLocationPair(RED, SPARROW, new HantoTestCoordinate(0, 2))
		};
		game.initializeBoard(board);
		game.setPlayerMoving(RED);
		game.setTurnNumber(20);
		assertEquals(DRAW, game.makeMove(SPARROW, new HantoTestCoordinate(0, 2), new HantoTestCoordinate(1, 1)));
		game.makeMove(SPARROW, new HantoTestCoordinate(1, 1), new HantoTestCoordinate(0, 2));
	}

	/**
	 * Checks breaking of a chain.
	 * 

	 * @throws HantoException */
	@Test
	public void breakChain() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Invalid Move.");

		final PieceLocationPair[] initialPieces = new PieceLocationPair[7];

		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoTestCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoTestCoordinate(0, -1));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoTestCoordinate(0, 1));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoTestCoordinate(1, 1));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoTestCoordinate(0, -2));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoTestCoordinate(-1, -2));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoTestCoordinate(2, 1));

		game.initializeBoard(initialPieces);
		game.setPlayerMoving(HantoPlayerColor.RED);

		game.makeMove(BUTTERFLY, new HantoTestCoordinate(0, 1), new HantoTestCoordinate(-1, 2));
	}

	/**
	 * Checks squeezing a piece through.
	 * 

	 * @throws HantoException */
	@Test
	public void squeezePieceThrough() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Invalid Move.");

		final PieceLocationPair[] initialPieces = new PieceLocationPair[6];

		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoTestCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoTestCoordinate(1, 0));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoTestCoordinate(0, 1));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoTestCoordinate(1, 1));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoTestCoordinate(2, 0));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoTestCoordinate(2, -1));

		game.initializeBoard(initialPieces);
		game.setPlayerMoving(HantoPlayerColor.BLUE);

		game.makeMove(BUTTERFLY, new HantoTestCoordinate(1, 0), new HantoTestCoordinate(1, -1));
	}
	
	/**
	 * Checks adding a blue piece next to a red piece.
	 * 
	 * @throws HantoException */
	@Test
	public void addBlueToRed() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Invalid Move.");

		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1));
		
		game.makeMove(SPARROW, null, new HantoTestCoordinate(1, 1));
	}
}
