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

@WebServlet(name = "EditDrink", value = "/editDrink")
public class EditDrink extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String image = request.getParameter("pathImage");
        String price = request.getParameter("price");
        String status = request.getParameter("status");
        String id_cate = request.getParameter("category");
        DAO dao = new DAO();
        try {
            int priceD = Integer.parseInt(price);
            boolean statusD = status.equals("1");
            Category cate = dao.getCategoryById(id_cate);
            Drink d = new Drink(id, cate ,name, image, priceD, statusD);
//           HttpSession se = request.getSession();
//           se.getAttribute("")
            dao.updateDrink(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("getedDri", dao.getDrinkByid(id));
        request.getRequestDispatcher("detailDrink").forward(request, response);
    }
}