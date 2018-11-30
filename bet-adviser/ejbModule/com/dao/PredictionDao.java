package com.dao;

import java.util.List;

import javax.ejb.Stateless;

import com.model.BetResult;
import com.model.Prediction;

@Stateless
public class PredictionDao extends GenericDao<Prediction> {
	
	public PredictionDao(Class<Prediction> entityClass) {
		super(entityClass);
	}
	
	public List<Prediction> findBySportEvent(long eventId) {
		return entityManager.createNamedQuery("Prediction.findBySportEvent", Prediction.class)
			.setParameter(1, eventId)
			.getResultList();
	}
	
	public void updateSuccessByEventId(long predictionId, boolean success) {
		entityManager.createNamedQuery("Prediction.updateSuccess")
			.setParameter(1, success)
			.setParameter(2, predictionId);
	}
	
}
