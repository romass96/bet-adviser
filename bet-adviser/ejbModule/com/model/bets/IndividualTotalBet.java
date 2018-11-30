package com.model.bets;

import javax.persistence.*;

import com.model.BetResult;

@Entity
@Table(name = "individual_total_bets")
public class IndividualTotalBet extends Bet {

	@Enumerated(EnumType.STRING)
	@Column(name = "total_type")
	private TotalType totalType;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "player")
	private Player player;
	
	@Column(name = "total_value")
	private double value;
	
	public IndividualTotalBet() {
		
	}
	
	public IndividualTotalBet(TotalType totalType, double value, Player player) {
		this.totalType = totalType;
		this.value = value;
		this.player = player;
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
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public BetResult calculate() {
		int[] points = getResultPoints();
		int total = 0;
		if (player == Player.FIRST_PLAYER) {
			total = points[0];
		} else {
			total = points[1];
		}
		
		if ((total > value && totalType == TotalType.TOTAL_OVER) || (total < value && totalType == TotalType.TOTAL_LESS)) {
			return BetResult.WIN;
		} else if (total == value) {
			return BetResult.RETURN;
		} else {
			return BetResult.LOSE;
		}
	}

}
