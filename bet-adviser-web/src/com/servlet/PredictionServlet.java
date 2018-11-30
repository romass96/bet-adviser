package com.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.PredictionService;

@WebServlet("/PredictionServlet")
public class PredictionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PredictionService predictionService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("predictionList", predictionService.getAllPredictions());
		request.getRequestDispatcher("/pages/predictionList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
