package com.khoabug.coffeshop.user.controller; /**
 * @author : KhoaBug
 * @since : 8/16/2023 ,Wed
 */

import com.khoabug.coffeshop.user.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ActiveController", value = "/active-user")
public class ActiveController extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.changeActiveUser(request.getParameter("reactivate-id"), true);
        response.sendRedirect(request.getContextPath() + "/users?page=1&size=10");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.changeActiveUser(request.getParameter("deactivate-id"), false);
        response.sendRedirect(request.getContextPath() + "/users?page=1&size=10");
    }
}
