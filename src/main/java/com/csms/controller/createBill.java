package com.csms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.csms.dal.DAO;
import com.csms.modal.OrderLine;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateBill", value = "/createBill")
public class CreateBill extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String[] listSoldDrink = request.getParameterValues("listSoldDrink");
        ObjectMapper objectMapper = new ObjectMapper();

        // Đọc dữ liệu JSON từ request và chuyển đổi thành mảng đối tượng
        System.out.println(request.getReader());
        OrderLine[] receivedData = objectMapper.readValue(request.getReader(), OrderLine[].class);

        DAO dao = new DAO();
        System.out.println(receivedData);
        PrintWriter p = response.getWriter();

        dao.addBill(receivedData[0].getBill());

        for (OrderLine o : receivedData) {
            p.println(o.getDrink().getId()+"-"+o.getQuantity()+"-"+o.getTotalPrice()+"-"+o.getBill().getTotalPrice());
            dao.addOrderline(o);
        }
        //dao.getBill();
    }
}