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
import static hanto.common.HantoPieceType.SPARROW;
import static hanto.common.MoveResult.OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hanto.HantoGameFactory;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the alpha hanto game. 
 *
 */
public class AlphaHantoMasterTest
{
	 /**
	 * Internal class for these test cases.
	 * @version Sep 13, 2014
	 */
	 public class TestHantoCoordinate implements HantoCoordinate
	 {
		 private final int x, y;
		
		 /**
		  * 
		  * @param x
		  * @param y
		  */
		 public TestHantoCoordinate(int x, int y)
		 {
			 this.x = x;
			 this.y = y;
		 }
		
		 /*
		 * @see hanto.common.HantoCoordinate#getX()
		 */
		 @Override
		 public int getX()
		 {
			 return x;
		 }
		
		 /*
		 * @see hanto.common.HantoCoordinate#getY()
		 */
		 @Override
		 public int getY()
		 {
			 return y;
		 }
	 }
	
	 private static HantoGameFactory factory;
	
	 private HantoGame game;
	
	 /**
	  * 
	  */
	 @BeforeClass
	 public static void initializeClass()
	 {
		 factory = HantoGameFactory.getInstance();
	 }
	
	 /**
	  * 
	  */
	 @Before
	 public void setup()
	 {
		 game = factory.makeHantoGame(HantoGameID.BETA_HANTO);
	 }
	
	 /**
	  * 
	  */
	 @Test
	 public void getAnAlphaHantoGameFromTheFactory()
	 {
		 assertTrue(game instanceof AlphaHantoGame);
	 }
	
	 /**
	  * 
	  * @throws HantoException
	  */
	 @Test
	 public void blueMakesValidFirstMove() throws HantoException
	 {
		 final MoveResult mr = game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		 assertEquals(OK, mr);
	 }
	
	/**
	 * 
	 * @throws HantoException
	 */
	 @Test
	 public void afterFirstMoveBlueButterflyIsAt0_0() throws HantoException
	 {
		 game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		 final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 0));
		 assertEquals(BUTTERFLY, p.getType());
		 assertEquals(HantoPlayerColor.BLUE, p.getColor());
	 }
	
	 /**
	  * 
	  * @throws HantoException
	  */
	 @Test(expected=HantoException.class)
	 public void bluePlacesNonButterfly() throws HantoException
	 {
		 game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));
	 }
	
	 /**
	  * 
	  * @throws HantoException
	  */
	 @Test
	 public void redPlacesButterflyNextToBlueButterfly() throws HantoException
	 {
		 game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		 game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1));
		 final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 1));
		 assertEquals(BUTTERFLY, p.getType());
		 assertEquals(HantoPlayerColor.RED, p.getColor());
	 }
	
	 /**
	  * 
	  * @throws HantoException
	  */
	 @Test(expected=HantoException.class)
	 public void blueAttemptsToPlaceButterflyAtWrongLocation() throws HantoException
	 {
		 game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 1));
	 }
	
	 /**
	  * 
	  * @throws HantoException
	  */
	 @Test
	 public void redMakesValidSecondMoveAndGameIsDrawn() throws HantoException
	 {
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
	 	final MoveResult mr = game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 1));
	 	assertEquals(MoveResult.DRAW, mr);
	 }
	
	 /**
	  * 
	  * @throws HantoException
	  */
	 @Test(expected=HantoException.class)
	 public void redPlacesButterflyNonAdjacentToBlueButterfly() throws HantoException
	 {
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
	 	game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 2));
	 }
	
	 /**
	  * 
	  * @throws HantoException
	  */
	 @Test(expected=HantoException.class)
	 public void attemptToMoveRatherThanPlace() throws HantoException
	 {
		game.makeMove(BUTTERFLY, new TestHantoCoordinate(0, 1), new TestHantoCoordinate(0, 0));
	 }
}
