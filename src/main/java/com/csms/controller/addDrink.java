package com.csms.controller;

import java.io.IOException;

import com.csms.dal.DAO;
import com.csms.modal.Category;
import com.csms.modal.Drink;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AddDrink", value = "/addDrink")
public class AddDrink extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String image = request.getParameter("pathImage");
        String price = request.getParameter("price");
        String status = request.getParameter("status");
        String id_cate = request.getParameter("category");
        DAO dao = new DAO();
        try {
            String id_auto = "D"+(dao.getDrinkByCid("C0").size()+1);
            int priceD = Integer.parseInt(price);
            boolean statusD = status == "1" ? true : false;
            Category cate = dao.getCategoryById(id_cate);
            Drink d = new Drink(id_auto, cate ,name, image, priceD, statusD);
//           HttpSession se = request.getSession();
//           se.getAttribute("")
            dao.addDrink(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("viewDrinks?navbar=drink").forward(request, response);
    }
}