package com.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.PredictionDao;
import com.dao.SportEventDao;
import com.model.BetResult;
import com.model.Prediction;
import com.model.Sport;
import com.model.SportEvent;

@Stateless
public class SportEventService {
	@EJB
	private SportEventDao sportEventDao;
	@EJB
	private PredictionDao predictionDao;
	
	public void setEventAsFinished(long eventId, Sport sport, String result, Date endDate) {
		SportEvent sportEvent= sportEventDao.find(eventId);
		sportEvent.setResult(result);
		sportEvent.setEndDate(endDate);
		List<Prediction> predictions = predictionDao.findBySportEvent(eventId);
		calculatePredictions(predictions);
	}
	
	@Asynchronous
	public void calculatePredictions(List<Prediction> predictions) {
		predictions.forEach(prediction -> {
			prediction.initPredictionResult();
			predictionDao.update(prediction);
		});
	}

}
