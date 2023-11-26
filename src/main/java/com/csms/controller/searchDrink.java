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

@WebServlet(name = "SearchDrink", value = "/searchDrink")
public class SearchDrink extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String cid = request.getParameter("cate");
        String price = request.getParameter("price");
        String name = request.getParameter("textName");

        System.out.println(cid);
        DAO dao = new DAO();
        ArrayList<Drink> searchedDrink = dao.searchByPredicates(cid, price, name);

        HttpSession se = request.getSession();
        se.setAttribute("listDrink", searchedDrink);
        request.setAttribute("price", price);
        request.setAttribute("cid", cid);
        request.getRequestDispatcher("drink.jsp").forward(request, response);
    }
}