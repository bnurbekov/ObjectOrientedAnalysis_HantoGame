package hanto.studentramnur.alpha;

import static org.junit.Assert.*;
import hanto.HantoGameFactory;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;

import org.junit.Before;
import org.junit.Test;

public class AlphaHantoGameTest {
	private HantoGameFactory factory;
	private HantoGame game;
	
	@Before
	public void setUp() {
		factory = HantoGameFactory.getInstance();
		game = factory.makeHantoGame(HantoGameID.ALPHA_HANTO);
	}
	
	@Test
	public void testInitialization() {
		assertNotNull(factory);
		assertNotNull(game);
	}

	@Test(expected=HantoException.class)
	public void testFirstMove() throws HantoException {
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(0, 0)), MoveResult.OK);
		assertEquals(game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoBoardCoordinate(1, 0)), MoveResult.DRAW);
	}
}
