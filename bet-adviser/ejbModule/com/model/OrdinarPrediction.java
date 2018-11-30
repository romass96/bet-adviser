package com.model;

import javax.persistence.*;

import com.model.bets.Bet;

@Entity
@DiscriminatorValue("ordinar")
public class OrdinarPrediction extends Prediction {
	@OneToOne
	@JoinColumn(name = "prediction_id")
	private Bet bet;

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

	@Override
	public BetResult calculatePredictionResult() {
		bet.initBetResult();
		return bet.getBetResult();
	}
	
}
