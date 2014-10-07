/**
 * 
 */
package hanto.studentramnur.common.piece;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * Abstract class that contains the common logic for all pieces.
 */
public abstract class AbstractHantoPiece implements HantoPiece {

	private final HantoPlayerColor color;
	private final HantoPieceType type;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AbstractHantoPiece other = (AbstractHantoPiece) obj;
		if (color != other.color) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
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
