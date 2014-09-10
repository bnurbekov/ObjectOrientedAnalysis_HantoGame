/**
 * 
 */
package hanto.studentramnur.alpha;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * @author Batyr
 *
 */
public abstract class AbstractHantoPiece implements HantoPiece {
	
	private HantoPlayerColor color;
	private HantoPieceType type;
	
	protected AbstractHantoPiece(HantoPlayerColor color, HantoPieceType type) {
		this.color = color;
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see hanto.common.HantoPiece#getColor()
	 */
	@Override
	public HantoPlayerColor getColor() {
		return color;
	}

	/* (non-Javadoc)
	 * @see hanto.common.HantoPiece#getType()
	 */
	@Override
	public HantoPieceType getType() {
		return type;
	}

}
