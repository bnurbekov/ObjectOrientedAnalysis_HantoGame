/**
 * 
 */
package hanto.studentramnur.common;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * Abstract class that contains the common logic for all pieces. 
 * 
 * @author Batyr
 *
 */
public abstract class AbstractHantoPiece implements HantoPiece {
	
	private HantoPlayerColor color;
	private HantoPieceType type;
	
	/**
	 * Constructor for AbstractHantoPiece class.
	 * 
	 * @param color the color of the piece
	 * @param type the type of the piece
	 */
	protected AbstractHantoPiece(HantoPlayerColor color, HantoPieceType type) {
		this.color = color;
		this.type = type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HantoPlayerColor getColor() {
		return color;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HantoPieceType getType() {
		return type;
	}

}
