package com.khoabug.coffeshop.user.controller;

import com.khoabug.coffeshop.common.services.RequestMappingService;
import com.khoabug.coffeshop.role.infrastructure.repository.RoleRepository;
import com.khoabug.coffeshop.user.model.UserSignupRequest;
import com.khoabug.coffeshop.user.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddController", value = "/add-user")
public class AddController extends HttpServlet {

    @Inject
    private UserService userService;
    @Inject
    private RoleRepository roleRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("roles", roleRepository.findAll());

        request.getRequestDispatcher("/WEB-INF/jsp/user-management-add-user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserSignupRequest registerUses = RequestMappingService.toModel(UserSignupRequest.class, request);

        userService.addUser(registerUses);

        response.sendRedirect(request.getContextPath() + "/add-user");
    }
}
