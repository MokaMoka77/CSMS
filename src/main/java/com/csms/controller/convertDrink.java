package com.csms.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.csms.dal.DAO;
import com.csms.modal.Drink;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ConvertDrink", value = "/convertDrink")
public class ConvertDrink extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_cate = request.getParameter("cid");
        DAO da = new DAO();
        ArrayList<Drink> listDrink = da.getDrinkByCid(id_cate);
        HttpSession se = request.getSession();
        se.setAttribute("listDrink", listDrink);
        request.getRequestDispatcher("bill.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}