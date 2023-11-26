package com.csms.controller;

import java.io.IOException;

import com.csms.dal.DAO;
import com.csms.modal.Drink;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DetailDrink", value = "/detailDrink")
public class DetailDrink extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("Drink_id");
        DAO dao = new DAO();
        Drink getedDri = dao.getDrinkByid(id);
        request.setAttribute("getedDri", getedDri);
        request.getRequestDispatcher("detailDrink.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getAttribute("getedDri");
        request.getRequestDispatcher("detailDrink.jsp").forward(request, response);
    }
}