package com.model.bets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.model.BetResult;

@Entity
@Table(name = "two_chance_bets")
public class TwoChanceBet extends Bet {
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "two_chance")
	private TwoChance twoChance;
	
	public TwoChanceBet() {
		// TODO Auto-generated constructor stub
	}
	
	public TwoChanceBet(TwoChance twoChance) {
		this.twoChance = twoChance;
	}
	
	@Override
	public BetResult calculate() {
		int[] points = getResultPoints();
		int firstPlayerPoints = points[0];
		int secondPlayerPoints = points[1];
		return ((firstPlayerPoints == secondPlayerPoints 
						&& (twoChance == TwoChance.FIRST_OR_DRAW || twoChance == TwoChance.SECOND_OR_DRAW)) 
				|| (firstPlayerPoints > secondPlayerPoints 
						&& (twoChance == TwoChance.FIRST_OR_DRAW || twoChance == TwoChance.FIRST_OR_SECOND))
				|| (firstPlayerPoints < secondPlayerPoints 
						&& (twoChance == TwoChance.SECOND_OR_DRAW || twoChance == TwoChance.FIRST_OR_SECOND))) ?
								BetResult.WIN : BetResult.LOSE;
	}

}

enum TwoChance {
	FIRST_OR_DRAW,
	FIRST_OR_SECOND,
	SECOND_OR_DRAW
}
