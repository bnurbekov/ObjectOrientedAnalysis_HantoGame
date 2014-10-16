/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.tournament;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoTestGame;
import hanto.studentramnur.common.HantoTestGameFactory;
import hanto.studentramnur.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

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
public class TournamentGameTest {

	private HantoTestGame game;
	private HantoGamePlayer player1;
	private HantoGamePlayer player2;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Initializes private variables before each test method.
	 */
	@Before
	public void setUp() {
		game = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO);
		player1 = new HantoPlayer();
		player2 = new HantoPlayer();
	}

	/**
	 * Test the initialization of the fields.
	 */
	@Test
	public void testInitialization() {
		assertNotNull(game);
		assertNotNull(player1);
		assertNotNull(player2);
		assertTrue(game instanceof EpsilonHantoGame);
	}

	/**
	 * Test Game
	 * 
	 * @throws HantoException */
	@Test
	public void testGameP1BlueGoesFirst() throws HantoException {
		player1.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		player2.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
		
		HantoMoveRecord p1Move = player1.makeMove(null);
		game.makeMove(p1Move.getPiece(), p1Move.getFrom(), p1Move.getTo());
		HantoMoveRecord p2Move = null;
		while(true) {
			p2Move = player2.makeMove(p1Move);
			MoveResult resultP2 = game.makeMove(p2Move.getPiece(), p2Move.getFrom(), p2Move.getTo());
			
			if(resultP2 != MoveResult.OK) {
				System.out.println(resultP2.toString());
				break;
			}
			
			p1Move = player1.makeMove(p2Move);
			MoveResult resultP1 = game.makeMove(p1Move.getPiece(), p1Move.getFrom(), p1Move.getTo());
			
			if(resultP1 != MoveResult.OK) {
				System.out.println(resultP1.toString());
				break;
			}
		}
		
		System.out.println(game.getPrintableBoard());
	}
	
	/**
	 * Test Game
	 * 
	 * @throws HantoException */
	@Test
	public void testGameP1RedGoesFirst() throws HantoException {
		player1.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, true);
		player2.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, false);
		
		HantoMoveRecord p1Move = player1.makeMove(null);
		game.makeMove(p1Move.getPiece(), p1Move.getFrom(), p1Move.getTo());
		HantoMoveRecord p2Move = null;
		while(true) {
			p2Move = player2.makeMove(p1Move);
			MoveResult resultP2 = game.makeMove(p2Move.getPiece(), p2Move.getFrom(), p2Move.getTo());
			
			if(resultP2 != MoveResult.OK) {
				System.out.println(resultP2.toString());
				break;
			}
			
			p1Move = player1.makeMove(p2Move);
			MoveResult resultP1 = game.makeMove(p1Move.getPiece(), p1Move.getFrom(), p1Move.getTo());
			
			if(resultP1 != MoveResult.OK) {
				System.out.println(resultP1.toString());
				break;
			}
		}
		
		System.out.println(game.getPrintableBoard());
	}
}