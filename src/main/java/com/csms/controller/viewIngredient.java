package com.csms.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.csms.dal.DAO;
import com.csms.modal.Ingredient;
import com.csms.modal.IngredientPerDay;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ViewIngredient", value = "/viewIngredient")
public class ViewIngredient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        HttpSession se = request.getSession();
        //Ingredient
        ArrayList<Ingredient> listNL = dao.getAllIngredients();
        se.setAttribute("listNL", listNL);
        //Ingredient Per Day
        ArrayList<IngredientPerDay> listNLPD = dao.getAllIngredientsPerDay();
        se.setAttribute("listNLPD", listNLPD);

        request.getRequestDispatcher("ingredient.jsp").forward(request, response);
        //processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}