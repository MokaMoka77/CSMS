package com.csms.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.csms.dal.DAO;
import com.csms.modal.Ingredient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SearchIngredient", value = "/searchIngredient")
public class SearchIngredient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchedText = request.getParameter("textName");
        DAO dao = new DAO();
        ArrayList<Ingredient> listNL = dao.searchByName(searchedText);
        HttpSession se = request.getSession();
        se.setAttribute("listNL", listNL);
        request.getRequestDispatcher("ingredient.jsp").forward(request, response);
    }
}