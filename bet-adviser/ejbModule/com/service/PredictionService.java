package com.service;

import java.util.List;

import javax.ejb.EJB;

import com.dao.PredictionDao;
import com.model.Prediction;

public class PredictionService {
	@EJB
	private PredictionDao predictionDao;
	
	public List<Prediction> getAllPredictions() {
		return predictionDao.findAll();
	}
	
}
