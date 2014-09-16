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
 * Simple HantoCoordinate implementation for test cases.
 */
public class TestHantoCoordinate implements HantoCoordinate {
	private final int x, y;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public TestHantoCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * @see hanto.common.HantoCoordinate#getX()
	 */
	@Override
	public int getX() {
		return x;
	}

	/*
	 * @see hanto.common.HantoCoordinate#getY()
	 */
	@Override
	public int getY() {
		return y;
	}
}