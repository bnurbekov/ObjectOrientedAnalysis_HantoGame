package hanto.studentramnur.gamma;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoTestCoordinate;
import hanto.studentramnur.common.HantoTestGame;
import hanto.studentramnur.common.HantoTestGameFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
	 * Test valid first move.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void blueMakesValidFirstMove() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0)));
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

		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(1, 0));
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

		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1));
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

		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(1, 1));
	}

	/**
	 * Blue forfeits
	 * @throws HantoException
	 */
	@Test
	public void blueForfeits() throws HantoException {
		assertEquals(MoveResult.RED_WINS, game.makeMove(BUTTERFLY, null, null));
	}
	
	@Test
	public void BlueMovesButterfly() throws HantoException {
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1));
		game.makeMove(BUTTERFLY, new HantoTestCoordinate(0, 0), new HantoTestCoordinate(1, 0));
	}
}
