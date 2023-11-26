package com.csms.controller;

import com.csms.dal.AccountDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csms.dal.RoleDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String FULL_NAME = "fullname";
    private static final String PASS_WORD = "password";
    private static final String AUTHORIZE = "authorize";
    private static final Logger logger = LogManager.getLogger();

    private static List<String> getAuthorize(String id) {
        RoleDao rdb = new RoleDao();
        String[] authorizeList = rdb.getAuthorizeById(id).split(" - ");
        List<String> authorize = new ArrayList<>();

        for (String part : authorizeList) {
            String cleanPart = part.replace("Quản lý ", "");
            authorize.add(cleanPart);
        }
        return authorize;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String email = request.getParameter(EMAIL);
        String pass = request.getParameter(PASS_WORD);
        AccountDAO adb = new AccountDAO();
        try {
            if (adb.checkLogin(email, pass)) {
                HttpSession session = request.getSession();
                session.setAttribute(ID, adb.getId(email));
                session.setAttribute(EMAIL, email);
                session.setAttribute(FULL_NAME, adb.getFullName(email));
                session.setAttribute(AUTHORIZE, getAuthorize(adb.getRoleId(email)));
                Cookie usernameCookie = new Cookie(EMAIL, email);
                usernameCookie.setMaxAge(86400);
                response.addCookie(usernameCookie);
                response.setContentType("text/plain");
                response.getWriter().write("redirect:home.jsp");
            } else {
                response.setContentType("text/plain");
                response.getWriter().write("error:Sai email hoặc mật khẩu.");
            }
        } catch (IOException e) {
            logger.error("Error at Login", e);
        }
    }
}
