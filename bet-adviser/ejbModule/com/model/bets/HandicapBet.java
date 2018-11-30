package com.model.bets;

import javax.persistence.*;

import com.model.BetResult;

@Entity
@Table(name = "handicap_bets")
public class HandicapBet extends Bet {

	@Column(name = "handicap_value")
	private double value;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "player")
	private Player player;
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public BetResult calculate() {
		int[] points = getResultPoints();
		int firstPlayerPoints = points[0];
		int secondPlayerPoints = points[1];
		if ((player == Player.FIRST_PLAYER && firstPlayerPoints + value > secondPlayerPoints) 
				|| (player == Player.SECOND_PLAYER && secondPlayerPoints + value > firstPlayerPoints)) {
			return BetResult.WIN;
		} else if ((player == Player.FIRST_PLAYER && firstPlayerPoints + value == secondPlayerPoints) 
				|| (player == Player.SECOND_PLAYER && secondPlayerPoints + value == firstPlayerPoints)) {
			return BetResult.RETURN;
		} else {
			return BetResult.LOSE;
		}
	}

}

