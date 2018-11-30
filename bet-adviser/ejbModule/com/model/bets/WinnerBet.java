package com.model.bets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.model.BetResult;

@Entity
@Table(name = "winner_bets")
public class WinnerBet extends Bet {
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "winner")
	private Winner winner;
	
	public WinnerBet() {

	}

	public WinnerBet(Winner winner) {
		this.winner = winner;
	}

	public Winner getWinner() {
		return winner;
	}

	public void setWinner(Winner winner) {
		this.winner = winner;
	}
	
	@Override
	public BetResult calculate() {
		int[] points = getResultPoints();
		int firstPlayerPoints = points[0];
		int secondPlayerPoints = points[1];
		return ((firstPlayerPoints == secondPlayerPoints && winner == Winner.DRAW) 
				|| (firstPlayerPoints > secondPlayerPoints && winner == Winner.WINNER_FIRST)
				|| (firstPlayerPoints < secondPlayerPoints && winner == Winner.WINNER_SECOND)) ?
						BetResult.WIN : BetResult.LOSE;
	}
	

}

enum Winner {
	WINNER_FIRST,
	DRAW,
	WINNER_SECOND
}
