package com.model.bets;

import javax.persistence.*;

import com.model.BetResult;

@Entity
@Table(name = "total_bets")
public class TotalBet extends Bet {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "total_type")
	private TotalType totalType;
	
	@Column(name = "total_value")
	private double value;
	
	public TotalBet() {
		
	}
	
	public TotalBet(TotalType totalType, double value) {
		this.totalType = totalType;
		this.value = value;
	}
	
	public TotalType getTotalType() {
		return totalType;
	}

	public void setTotalType(TotalType totalType) {
		this.totalType = totalType;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public BetResult calculate() {
		int[] points = getResultPoints();
		int total = points[0] + points[1];
		if ((total > value && totalType == TotalType.TOTAL_OVER) || (total < value && totalType == TotalType.TOTAL_LESS)) {
			return BetResult.WIN;
		} else if (total == value) {
			return BetResult.RETURN;
		} else {
			return BetResult.LOSE;
		}
	}
	
}
