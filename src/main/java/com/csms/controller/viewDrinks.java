package com.csms.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.csms.dal.DAO;
import com.csms.modal.Category;
import com.csms.modal.Drink;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ViewDrinks", value = "/viewDrinks")
public class ViewDrinks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String navbar = request.getParameter("navbar");
        DAO dao = new DAO();
        ArrayList<Category> listCa = dao.getAllCategories();
        ArrayList<Drink> listDrink = dao.getDrinkByCid("C0");
        HttpSession se = request.getSession();
        se.setAttribute("listCa", listCa);
        se.setAttribute("listDrink", listDrink);
        if(navbar.equals("drink")){
            se.setAttribute("listNLPD", dao.getAllIngredientsPerDay());
            request.getRequestDispatcher("drink.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("bill.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}