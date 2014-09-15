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
import hanto.HantoGameFactory;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.TestHantoCoordinate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests the alpha hanto game.
 * 
 * @author Batyr
 *
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
	 */
	@Test
	public void blueMakesValidFirstMove() throws HantoException {
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 0)), MoveResult.OK);
		final HantoPiece piece = game.getPieceAt(new TestHantoCoordinate(0, 0));
		assertEquals(BUTTERFLY, piece.getType());
		assertEquals(HantoPlayerColor.BLUE, piece.getColor());
		System.out.println(game.getPrintableBoard());
	}
	
	/**
	 * Test invalid first move.
	 */
	@Test
	public void blueMakesInvalidFirstMove1() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("The first move should always be placed at (0, 0).");
		
		game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(1, 0));
	}

	/**
	 * Test invalid first move.
	 */
	@Test
	public void blueMakesInvalidFirstMove2() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("The first move should always be placed at (0, 0).");
		
		game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 1));
	}

	/**
	 * Test invalid first move.
	 */
	@Test
	public void blueMakesInvalidFirstMove3() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("The first move should always be placed at (0, 0).");
		
		game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(1, 1));
	}

	/**
	 * Test move with null coordinates
	 * @throws HantoException
	 */
	@Test
	public void moveWithNullCoor() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("To coordinate cannot be null.");

		game.makeMove(HantoPieceType.BUTTERFLY, null, null);
	}

	/**
	 * Test move with null HantoPieceType
	 * @throws HantoException
	 */
	@Test
	public void moveWithNullHantoPieceType() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Only butterflies are allowed for this game.");

		game.makeMove(null, null, new TestHantoCoordinate(0, 0));
	}

	/**
	 * Test moving a non existent piece on the board (moving is illegal).
	 * @throws HantoException
	 */
	@Test
	public void moveNonExistingPiece() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("The only move allowed is to add a Butterfly.");

		game.makeMove(HantoPieceType.BUTTERFLY, new TestHantoCoordinate(0, 0), new TestHantoCoordinate(0, 1));
	}

	/**
	 * Test moving a piece on the board (moving is illegal).
	 * @throws HantoException
	 */
	@Test
	public void moveExistingPiece() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("The only move allowed is to add a Butterfly.");

		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 0)), MoveResult.OK);
		game.makeMove(HantoPieceType.BUTTERFLY, new TestHantoCoordinate(0, 0), new TestHantoCoordinate(0, 1));
	}
	
	/**
	 * Test valid second move.  (RED's first move)
	 */
	@Test
	public void redMakesValidFirstMove() throws HantoException {
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 0)), MoveResult.OK);
		final HantoPiece blueButterfly = game.getPieceAt(new TestHantoCoordinate(0, 0));
		assertEquals(BUTTERFLY, blueButterfly.getType());
		assertEquals(HantoPlayerColor.BLUE, blueButterfly.getColor());
		
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 1)), MoveResult.DRAW);
		final HantoPiece redButterfly = game.getPieceAt(new TestHantoCoordinate(0, 1));
		assertEquals(BUTTERFLY, redButterfly.getType());
		assertEquals(HantoPlayerColor.RED, redButterfly.getColor());
		
		System.out.println(game.getPrintableBoard());
	}
	

	/**
	 * Test invalid second move.  (RED's first move)
	 */
	@Test
	public void redMakesInvalidFirstMove() throws HantoException {
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(0, 0)), MoveResult.OK);
		final HantoPiece blueButterfly = game.getPieceAt(new TestHantoCoordinate(0, 0));
		assertEquals(BUTTERFLY, blueButterfly.getType());
		assertEquals(HantoPlayerColor.BLUE, blueButterfly.getColor());
		
		thrown.expect(HantoException.class);
		thrown.expectMessage("Move is invalid.");
		
		game.makeMove(HantoPieceType.BUTTERFLY, null, new TestHantoCoordinate(1, 1));
	}
}
