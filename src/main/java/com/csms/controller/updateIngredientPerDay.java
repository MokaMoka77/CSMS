package com.csms.controller;

import java.io.IOException;

import com.csms.dal.DAO;
import com.csms.modal.IngredientPerDay;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateIngredientPerDay", value = "/updateIngredientPerDay")
public class UpdateIngredientPerDay extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        String[] weights = request.getParameterValues("weight");
        for (int i=0; i<weights.length; i++) {
            IngredientPerDay ipd = dao.getAllIngredientsPerDay().get(i);
            if(!weights[i].equals(ipd.getWeight()+"")){
                dao.updateIngredientPerDay(new IngredientPerDay(Integer.parseInt(weights[i]), ipd.getNl()));
            }
        }
        request.getRequestDispatcher("viewIngredient").forward(request, response);
//        try {
//            int weightD = Integer.parseInt(weight);
//            DAO dao = new DAO();
//            dao.
//        } catch (Exception e) {
//        }
    }
}