package com.model;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "predictions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "prediction_type")
@NamedQueries({
	@NamedQuery(name = "Prediction.findBySportEvent", query = "SELECT p FROM Prediction c WHERE event_id = :eventId")
})
public abstract class Prediction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "prediction_date")
	private Date predictionDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "prediction_result")
	private BetResult predictionResult;
	
	public Prediction() {
		
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getPredictionDate() {
		return predictionDate;
	}
	
	public void setPredictionDate(Date predictionDate) {
		this.predictionDate = predictionDate;
	}
	
	public BetResult getPredictionResult() {
		return predictionResult;
	}

	public void setPredictionResult(BetResult predictionResult) {
		this.predictionResult = predictionResult;
	}
	
	public void initPredictionResult() {
		this.predictionResult = calculatePredictionResult();
	}

	public abstract BetResult calculatePredictionResult();
	
}
