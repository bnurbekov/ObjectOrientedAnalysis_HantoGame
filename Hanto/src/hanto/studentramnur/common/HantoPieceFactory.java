package hanto.studentramnur.common;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.beta.Butterfly;
import hanto.studentramnur.beta.Crab;
import hanto.studentramnur.beta.Crane;
import hanto.studentramnur.beta.Dove;
import hanto.studentramnur.beta.Horse;
import hanto.studentramnur.beta.Sparrow;

public class HantoPieceFactory {
	private static final HantoPieceFactory instance = new HantoPieceFactory();
	
	/**
	 * Default private descriptor.
	 */
	private HantoPieceFactory()
	{
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static HantoPieceFactory getInstance()
	{
		return instance;
	}
	
	public HantoPiece createPiece(HantoPlayerColor color, HantoPieceType type) {
		HantoPiece piece;
		
		switch (type) {
			default: //by default it is going to be a butterfly
			case BUTTERFLY:
				piece = new Butterfly(color, type);
				break;
			case CRAB:
				piece = new Crab(color, type);
				break;
			case CRANE:
				piece = new Crane(color, type);
				break;
			case DOVE:
				piece = new Dove(color, type);
				break;
			case HORSE:
				piece = new Horse(color, type);
				break;
			case SPARROW:
				piece = new Sparrow(color, type);
				break;
		}
		
		return piece;
	}
}
