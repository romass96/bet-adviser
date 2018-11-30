package com.model.bets;

import java.util.Arrays;
import javax.persistence.*;

import com.model.BetResult;
import com.model.SportEvent;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Bet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private SportEvent sportEvent;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "bet_result")
	private BetResult betResult;
	
	public void initBetResult() {
		this.betResult = calculate();
	}
	
	public abstract BetResult calculate();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public SportEvent getSportEvent() {
		return sportEvent;
	}

	public void setSportEvent(SportEvent sportEvent) {
		this.sportEvent = sportEvent;
	}

	public BetResult getBetResult() {
		return betResult;
	}

	public void setBetResult(BetResult betResult) {
		this.betResult = betResult;
	}

	public int[] getResultPoints() {
		return Arrays.stream(sportEvent.getResult().split("-")).mapToInt(Integer::parseInt).toArray();
	}
	
	
}
