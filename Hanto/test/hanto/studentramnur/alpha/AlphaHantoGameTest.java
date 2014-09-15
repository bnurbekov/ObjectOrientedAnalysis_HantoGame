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

import static org.junit.Assert.*;
import hanto.HantoGameFactory;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoBoardCoordinate;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the alpha hanto game.
 * 
 * @author Batyr
 *
 */
public class AlphaHantoGameTest {
	private HantoGameFactory factory;
	private HantoGame game;
	
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
	}

	/**
	 * Tests the first move.
	 * 
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void testFirstMove() throws HantoException {
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(0, 0)), MoveResult.OK);
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 0)), MoveResult.DRAW);
	}
}
