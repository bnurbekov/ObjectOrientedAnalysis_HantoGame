/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.tournament;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.AbstractHantoGame;
import hanto.studentramnur.common.HantoBoard;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.HantoGameFactory;
import hanto.studentramnur.common.HantoPlayerStatistics;
import hanto.studentramnur.common.PieceCoordinatePair;
import hanto.studentramnur.common.move.Move;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * 
 * @author Batyr
 *
 */
public class CopyOfHantoPlayer implements HantoGamePlayer {
	private AbstractHantoGame game;
	private HantoPlayerColor myColor;
	private HantoPlayerColor opponentColor;
	private HantoMoveRecord opponentsMove;
	private HantoMoveRecord result;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor,
			boolean doIMoveFirst) {
		this.myColor = myColor;
		opponentColor = (myColor == HantoPlayerColor.BLUE) ? HantoPlayerColor.RED : HantoPlayerColor.BLUE;
		 
		if (doIMoveFirst) {
			game = (AbstractHantoGame) HantoGameFactory.getInstance().makeHantoGame(version, myColor);
		} else {
			game = (AbstractHantoGame)HantoGameFactory.getInstance().makeHantoGame(version, opponentColor);
		}
		
		result = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove) {
		this.opponentsMove = opponentsMove;
		
		try {
			MoveResult moveResult = makeOpponentsMove();
			
			if (moveResult == MoveResult.OK) {
				makeMyMove();
			}
			else {
				System.out.println("WARNING! The tournament runner tried to ask for another move when the game was already over!");
			}
		} catch(HantoException e) {
			System.out.println("WARNING! Caught exception: " + e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * Makes the opponents move in the current game.
	 * 
	 * @param opponentsMove
	 * @throws HantoException
	 */
	private MoveResult makeOpponentsMove() throws HantoException {
		MoveResult moveResult;
		
		if (opponentsMove != null) {
			moveResult = game.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
		}
		else {
			moveResult = MoveResult.OK;
		}
		
		return moveResult;
	}
	
	/**
	 * Makes the next move of the current player.
	 * 
	 * @throws HantoException
	 */
	private void makeMyMove() throws HantoException {
		HantoPlayerStatistics currentPlayer = game.getPlayerStats(myColor);
		HantoBoard board = game.getBoard();
		
		if (opponentsMove == null) {
			game.makeMove(HantoPieceType.CRAB, null, new HantoBoardCoordinate(0, 0));
			result = new HantoMoveRecord(HantoPieceType.CRAB, null, new HantoBoardCoordinate(0, 0));
		} else {
			Collection<Move> allAvailableMoves = Move.getAllAvailableMoves(currentPlayer, board);
			
			if (allAvailableMoves.size() == 0) { //if we don't have any moves available, then forfeit
				game.makeMove(null, null, null);
				result = new HantoMoveRecord(null, null, null);
			} else {
				Move selectedMove = selectMove(allAvailableMoves, currentPlayer, board); //involves move selection techniques (AI)
				
				if (selectedMove == null) {
					game.makeMove(null, null, null);
					result = new HantoMoveRecord(null, null, null);
				} else {
					game.makeMove(selectedMove.getPieceType(), selectedMove.getFrom(), selectedMove.getTo());								
					result = new HantoMoveRecord(selectedMove.getPieceType(), selectedMove.getFrom(), selectedMove.getTo());
				}
			}
		}
	}

	/**
	 * Selects the appropriate move based on created set of rules (AI).
	 * 
	 * @param allAvailableMoves the list of all available moves
	 * @param currentPlayer the current player statistics
	 * @param board the board
	 * @return the next move selected from the list
	 */
	private Move selectMove(Collection<Move> allAvailableMoves, HantoPlayerStatistics currentPlayer, HantoBoard board) {
		Move resultingMove = null;
		
		if (!currentPlayer.hasPlacedButterfly()) { //if we haven't placed the butterfly, then count the number of turns
				if (currentPlayer.getMovesMade() >= 2) {//place butterfly anywhere on the board
					for (Move move : getAvailableMovesForButterflyAt(null, allAvailableMoves)) {
					if (move.getPieceType() == HantoPieceType.BUTTERFLY) {
						resultingMove = move;
					}
				}
				
				if (resultingMove == null) {
					return null; //we cannot place butterfly, we will forfeit implicitly (null is considered as forfeit by the upper layer function)
				}
			}
		}
		
		if (resultingMove == null) {		
			if (resultingMove == null) { // if by the end of this operation the move was not selected, then select the random move from the list
				Random randomGenerator = new Random();
				
				resultingMove = (Move)allAvailableMoves.toArray()[randomGenerator.nextInt(allAvailableMoves.size())];
			}
		} 
		
		return resultingMove;
	}
	
	/**
	 * Filters the available moves list so that it only contains available moves for the piece at the specific coordinate.
	 * 
	 * @param coordinate the coordinate
	 * @param allAvailableMoves the list of all available moves
	 * @return the filtered list of available moves
	 */
	private Collection<Move> getAvailableMovesForButterflyAt(HantoCoordinate coordinate, Collection<Move> allAvailableMoves) {
		Collection<Move> availableMovesForPiece = new ArrayList<Move>();
		
		for (Move move : allAvailableMoves) {
			if (move.getFrom() != null) {
				if (move.getFrom().equals(coordinate)) {
					availableMovesForPiece.add(move);
				}
			}
			else {
				if (move.getPieceType() == HantoPieceType.BUTTERFLY) {
					availableMovesForPiece.add(move);
				}
			}
		}
		
		return availableMovesForPiece;
	}

	/**
	 * Gets the coordinate of the butterfly with the specified color. 
	 * 
	 * @param board the board
	 * @param color the color
	 * @return the coordinate of the butterfly with the specified color. Returns null if the butterfly haven't been placed yet.
	 */
	private HantoCoordinate getButterflyCoor(HantoBoard board, HantoPlayerColor color) {
		HantoCoordinate butterflyCoor = null;
		
		Collection<PieceCoordinatePair> pieceLocationPairs = board.getPlayerPieces(color);
		for (PieceCoordinatePair plp : pieceLocationPairs) {
			if (plp.getPiece().getType() == HantoPieceType.BUTTERFLY) {
				butterflyCoor = plp.getLocation();
				break;
			}
		}
		
		return butterflyCoor;
	}
}
