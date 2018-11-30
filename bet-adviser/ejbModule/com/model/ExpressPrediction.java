package com.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.model.bets.Bet;


@Entity
@DiscriminatorValue("express")
public class ExpressPrediction extends Prediction {
	@OneToMany
	@JoinColumn(name = "prediction_id")
	private Set<Bet> bets;
	
	public Set<Bet> getBets() {
		return bets;
	}

	public void setBets(Set<Bet> bets) {
		this.bets = bets;
	}

	@Override
	public BetResult calculatePredictionResult() {
		bets.stream().forEach(bet -> bet.initBetResult());
		boolean lost = bets.stream().anyMatch((bet) -> {
			return bet.getBetResult() == BetResult.LOSE;
		});
		if (lost) {
			return BetResult.LOSE;
		} else {
			boolean returned = bets.stream().allMatch((bet) -> {
				return bet.getBetResult() == BetResult.RETURN;
			});
			if (returned) {
				return BetResult.RETURN;
			} else {
				return BetResult.WIN;
			}
		}
	}
	
}
