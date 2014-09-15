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
 * 
 * @author Batyr
 *
 */
public class HantoBoardCoordinate implements HantoCoordinate {
	
	private int x;
	private int y;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public HantoBoardCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @param where
	 */
	public HantoBoardCoordinate(HantoCoordinate where) {
		this(where.getX(), where.getY());
	}

	/**
	 * 
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * 
	 */
	@Override
	public int getY() {
		return y;
	}

	/**
	 * 
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
	 * 
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
