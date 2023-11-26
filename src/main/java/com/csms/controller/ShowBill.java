package com.csms.controller;

import com.csms.dal.BillDao;
import com.csms.dal.DAO;
import com.csms.modal.OrderLine02;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.csms.modal.Bill;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.System.out;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ShowBill", urlPatterns = {"/showBill"})
public class ShowBill extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowBill</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowBill at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BillDao b = new BillDao();
        List<Bill> listBill = b.getAllBill();
        request.setAttribute("dt", listBill);
        request.getRequestDispatcher("listBill.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAO d = new DAO();
        String id = request.getParameter("id");
        List<OrderLine02> list = d.getAllOrderlineByBillId(id);
        PrintWriter out = response.getWriter();
        for (OrderLine02 ol : list) {
            out.println("<tr>\n" +
                    "   <td>" + ol.getDrink_id() + "</td>\n" +
                    "   <td>" + d.getDrinkNameById(ol.getDrink_id()) + "</td>\n" +
                    "   <td>" + ol.getPrice() + "</td>\n" +
                    "   <td>" + ol.getNumber() + "</td>\n" +
                    "</tr>");
        }
        request.getRequestDispatcher("listBill.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
