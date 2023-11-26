package com.csms.controller;

import java.io.IOException;
import java.sql.Date;

import com.csms.dal.DAO;
import com.csms.modal.Ingredient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EditIngredient", value = "/editIngredient")
public class EditIngredient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("ingredient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String weight = request.getParameter("weight");
        String date = request.getParameter("date");
        String status = request.getParameter("status");
        DAO dao = new DAO();
        try {
            int weightD = Integer.parseInt(weight);
            boolean statusD = status.equals("1");
            Ingredient d = new Ingredient(id,name, weightD, Date.valueOf(date), statusD);
            dao.updateIngredient(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("viewIngredient").forward(request, response);
    }
}