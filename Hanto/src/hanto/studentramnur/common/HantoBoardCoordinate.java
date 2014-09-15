/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.common;

import hanto.common.HantoCoordinate;

/**
 * The implementation for HantoCoordinate interface.
 * 
 * @author Batyr
 *
 */
public class HantoBoardCoordinate implements HantoCoordinate {
	
	private int x;
	private int y;
	
	/**
	 * Constructor that creates the new coordinate based on its location along x and y axes.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public HantoBoardCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor that creates the new coordinate based on the location of the passed in coordinate.
	 * 
	 * @param where the coordinate to extract location from
	 */
	public HantoBoardCoordinate(HantoCoordinate where) {
		this(where.getX(), where.getY());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getY() {
		return y;
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		HantoBoardCoordinate other = (HantoBoardCoordinate) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
}
