package hanto.studentramnur.beta;

import static org.junit.Assert.*;
import hanto.HantoGameFactory;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentramnur.alpha.AlphaHantoGame;
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
}
