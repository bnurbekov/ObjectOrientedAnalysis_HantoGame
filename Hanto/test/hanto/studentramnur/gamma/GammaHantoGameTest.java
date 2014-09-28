package hanto.studentramnur.gamma;

import static hanto.common.HantoPieceType.*;
import static org.junit.Assert.*;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoTestCoordinate;
import hanto.studentramnur.common.HantoTestGame;
import hanto.studentramnur.common.HantoTestGame.PieceLocationPair;
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
		thrown.expectMessage("Invalid Move.");

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
		thrown.expectMessage("Invalid Move.");

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
		thrown.expectMessage("Invalid Move.");

		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(1, 1));
	}

	/**
	 * Blue forfeits
	 * @throws HantoException
	 */
	@Test
	public void blueForfeits() throws HantoException {
		thrown.expect(HantoException.class);
		thrown.expectMessage("Forfeiting is not allowed in Gamma Hanto.");
		
		assertEquals(MoveResult.RED_WINS, game.makeMove(BUTTERFLY, null, null));
	}
	
	@Test
	public void BlueMovesButterfly() throws HantoException {
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new HantoTestCoordinate(0, 1));
		game.makeMove(BUTTERFLY, new HantoTestCoordinate(0, 0), new HantoTestCoordinate(1, 0));
	}
	
	/**
	 * Checks if red wins when the blue butterfly is surrounded.
	 * 
	 * @throws HantoException
	 */
	@Test
	public void blueButterflyIsSurrounded() throws HantoException {
		PieceLocationPair[] initialPieces = new HantoTestGame.PieceLocationPair[7];
		
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
}
