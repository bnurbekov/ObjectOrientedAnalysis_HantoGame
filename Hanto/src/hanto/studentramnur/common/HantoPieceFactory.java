package hanto.studentramnur.common;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.beta.Butterfly;
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
			default: // Butterfly added by default
			case BUTTERFLY:
				piece = new Butterfly(color, type);
				break;
			/*
			case CRAB:
				break;
			case CRANE:
				break;
			case DOVE:
				break;
			case HORSE:
				break;
			*/
			case SPARROW:
				piece = new Sparrow(color, type);
				break;
		}
		
		return piece;
	}
}
