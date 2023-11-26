package com.csms.controller;

import java.io.IOException;
import java.sql.Date;

import com.csms.dal.DAO;
import com.csms.modal.Ingredient;
import com.csms.modal.IngredientPerDay;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AddIngredient", value = "/addIngredient")
public class AddIngredient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        String name = request.getParameter("name");
        String weight = request.getParameter("weight");
        String id = "I"+(dao.getAllIngredients().size()+1);

        try {
            int weightD = Integer.parseInt(weight);
            Ingredient nl = new Ingredient(id, name, weightD ,new Date(System.currentTimeMillis()), true);
            IngredientPerDay nlpd = new IngredientPerDay(0, nl);
            dao.addIngredient(nl);
            dao.addIngredientPerDay(nlpd);
            request.getRequestDispatcher("viewIngredient").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}