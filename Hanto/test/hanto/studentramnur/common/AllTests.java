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

import hanto.studentramnur.delta.DeltaHantoGameTest;
import hanto.studentramnur.gamma.GammaHantoGameTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Tests all registered JUnit test classes.
 */
@RunWith(Suite.class)
@SuiteClasses({GammaHantoGameTest.class,  DeltaHantoGameTest.class})
public class AllTests {}
