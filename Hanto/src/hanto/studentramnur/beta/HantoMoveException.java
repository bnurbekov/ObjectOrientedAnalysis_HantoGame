/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.beta;

import hanto.common.HantoException;

/**
 * The HantoMoveException is the Exception that is thrown for any error that occurs during the move.
 */
public class HantoMoveException extends HantoException {
	
	/**
	 * Every instance of this exception must have a message describing the exception.
	 * 
	 * @param message
	 *            the string describing the error causing the exception
	 */
	public HantoMoveException(String message) {
		super(message);
	}
}
