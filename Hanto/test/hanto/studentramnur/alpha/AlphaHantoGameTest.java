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

import static hanto.common.HantoPieceType.BUTTERFLY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.HantoGameFactory;
import hanto.studentramnur.common.HantoTestCoordinate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests the alpha hanto game.
 */
public class AlphaHantoGameTest {
	private HantoGameFactory factory;
	private HantoGame game;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Initializes the fields.
	 */
	@Before
	public void setUp() {
		factory = HantoGameFactory.getInstance();
		game = factory.makeHantoGame(HantoGameID.ALPHA_HANTO);
	}

	/**
	 * Test the initialization of the fields.
	 */
	@Test
	public void testInitialization() {
		assertNotNull(factory);
		assertNotNull(game);
		assertTrue(game instanceof AlphaHantoGame);
	}

	/**
	 * Test valid first move.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void blueMakesValidFirstMove() throws HantoException {
<<<<<<< HEAD
		assertEquals(MoveResult.OK, game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0)));
=======
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoTestCoordinate(0, 0)), MoveResult.OK);
>>>>>>> 93b186715f921bbdff1e475ff8ee430e9bfc7966
		final HantoPiece piece = game.getPieceAt(new HantoTestCoordinate(0, 0));
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
<<<<<<< HEAD
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(1, 0));
=======
		
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoTestCoordinate(1, 0));
>>>>>>> 93b186715f921bbdff1e475ff8ee430e9bfc7966
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
<<<<<<< HEAD
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1));
=======
		
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoTestCoordinate(0, 1));
>>>>>>> 93b186715f921bbdff1e475ff8ee430e9bfc7966
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
<<<<<<< HEAD
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(1, 1));
=======
		
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoTestCoordinate(1, 1));
>>>>>>> 93b186715f921bbdff1e475ff8ee430e9bfc7966
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
		thrown.expectMessage("Only butterflies are allowed for this game.");

		game.makeMove(null, null, new HantoTestCoordinate(0, 0));
	}

	/**
	 * Test moving a non existent piece on the board (moving is illegal).
	 * @throws HantoException
	 */
	@Test
	public void moveNonExistingPiece() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("The only move allowed is to add a Butterfly.");
<<<<<<< HEAD
		game.makeMove(BUTTERFLY, new HantoTestCoordinate(0, 0), new HantoTestCoordinate(0, 1));
=======

		game.makeMove(HantoPieceType.BUTTERFLY, new HantoTestCoordinate(0, 0), new HantoTestCoordinate(0, 1));
>>>>>>> 93b186715f921bbdff1e475ff8ee430e9bfc7966
	}

	/**
	 * Test moving a piece on the board (moving is illegal).
	 * @throws HantoException
	 */
	@Test
	public void moveExistingPiece() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("The only move allowed is to add a Butterfly.");

<<<<<<< HEAD
		assertEquals(MoveResult.OK, game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0)));
		game.makeMove(BUTTERFLY, new HantoTestCoordinate(0, 0), new HantoTestCoordinate(0, 1));
=======
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoTestCoordinate(0, 0)), MoveResult.OK);
		game.makeMove(HantoPieceType.BUTTERFLY, new HantoTestCoordinate(0, 0), new HantoTestCoordinate(0, 1));
>>>>>>> 93b186715f921bbdff1e475ff8ee430e9bfc7966
	}

	/**
	 * Test valid second move.  (RED's first move)
	 * 
	 * @throws HantoException
	 */
	@Test
	public void redMakesValidFirstMove() throws HantoException {
<<<<<<< HEAD
		assertEquals(MoveResult.OK, game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0)));
		final HantoPiece blueButterfly = game.getPieceAt(new HantoTestCoordinate(0, 0));
		assertEquals(BUTTERFLY, blueButterfly.getType());
		assertEquals(HantoPlayerColor.BLUE, blueButterfly.getColor());

		assertEquals(MoveResult.DRAW, game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1)));
=======
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoTestCoordinate(0, 0)), MoveResult.OK);
		final HantoPiece blueButterfly = game.getPieceAt(new HantoTestCoordinate(0, 0));
		assertEquals(BUTTERFLY, blueButterfly.getType());
		assertEquals(HantoPlayerColor.BLUE, blueButterfly.getColor());
		
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoTestCoordinate(0, 1)), MoveResult.DRAW);
>>>>>>> 93b186715f921bbdff1e475ff8ee430e9bfc7966
		final HantoPiece redButterfly = game.getPieceAt(new HantoTestCoordinate(0, 1));
		assertEquals(BUTTERFLY, redButterfly.getType());
		assertEquals(HantoPlayerColor.RED, redButterfly.getColor());

		System.out.println(game.getPrintableBoard());
	}


	/**
	 * Test invalid second move.  (RED's first move)
	 * 
	 * @throws HantoException
	 */
	@Test
	public void redMakesInvalidFirstMove() throws HantoException {
<<<<<<< HEAD
		assertEquals(MoveResult.OK, game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0)));
=======
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoTestCoordinate(0, 0)), MoveResult.OK);
>>>>>>> 93b186715f921bbdff1e475ff8ee430e9bfc7966
		final HantoPiece blueButterfly = game.getPieceAt(new HantoTestCoordinate(0, 0));
		assertEquals(BUTTERFLY, blueButterfly.getType());
		assertEquals(HantoPlayerColor.BLUE, blueButterfly.getColor());

		thrown.expect(HantoException.class);
		thrown.expectMessage("Move is invalid.");
<<<<<<< HEAD
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(1, 1));
=======
		
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoTestCoordinate(1, 1));
>>>>>>> 93b186715f921bbdff1e475ff8ee430e9bfc7966
	}
}
