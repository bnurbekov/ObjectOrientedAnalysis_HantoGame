package hanto.studentramnur.beta;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static org.junit.Assert.*;
import hanto.HantoGameFactory;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.alpha.AlphaHantoGame;
import hanto.studentramnur.alpha.AlphaHantoMasterTest.TestHantoCoordinate;
import hanto.studentramnur.common.HantoBoardCoordinate;

import org.junit.Before;
import org.junit.Test;

public class BetaHantoGameTest {
	private HantoGameFactory factory;
	private HantoGame game;

	@Before
	public void setUp() throws Exception {
		factory = HantoGameFactory.getInstance();
		game = factory.makeHantoGame(HantoGameID.BETA_HANTO);
	}
	
	@Test
	public void getABetaHantoGameFromTheFactory()
	 {
		 assertTrue(game instanceof BetaHantoGame);
	 }

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
	
	@Test
	public void afterFirstMoveBlueButterflyIsAt0_0() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoBoardCoordinate(0, 0));
		final HantoPiece p = game.getPieceAt(new HantoBoardCoordinate(0, 0));
		assertEquals(BUTTERFLY, p.getType());
		assertEquals(HantoPlayerColor.BLUE, p.getColor());
	}
	
	@Test
	public void getPrintableBoardDoesntReturnNull() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoBoardCoordinate(0, 0));
		String printableBoard = game.getPrintableBoard();
		
		assertNotNull(printableBoard);
	}
	
	@Test(expected=HantoException.class)
	public void redPlacesPieceNonAdjacentToBlueOtherPieces() throws HantoException
	{
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, 1));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(0, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(0, -1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoBoardCoordinate(3, 0));
	}
	
	@Test(expected=HantoException.class)
	public void blueAttemptsToPlaceButterflyAtWrongLocation() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoBoardCoordinate(-1, 1));
	}
	
	@Test(expected=HantoException.class)
	public void bluePlacesCrane() throws HantoException {
		game.makeMove(HantoPieceType.CRANE, null, new HantoBoardCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class)
	public void attemptToMoveRatherThanPlace() throws HantoException
	{
		game.makeMove(BUTTERFLY, new HantoBoardCoordinate(0, 1), new HantoBoardCoordinate(0, 0));
	}
	
	@Test(expected=HantoException.class)
	public void attemptToMoveBeyondTheBoard() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, null);
	}
}
