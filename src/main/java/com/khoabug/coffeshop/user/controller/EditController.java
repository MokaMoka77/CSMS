package com.khoabug.coffeshop.user.controller; /**
 * @author : KhoaBug
 * @since : 8/16/2023 ,Wed
 */

import com.khoabug.coffeshop.common.services.RequestMappingService;
import com.khoabug.coffeshop.role.infrastructure.repository.RoleRepository;
import com.khoabug.coffeshop.user.model.User;
import com.khoabug.coffeshop.user.model.UserSignupRequest;
import com.khoabug.coffeshop.user.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EditController", value = "/edit-user")
public class EditController extends HttpServlet {

    @Inject
    private UserService userService;
    @Inject
    private RoleRepository roleRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        User user = userService.findById(id).orElseThrow();

        request.setAttribute("roles", roleRepository.findAll());

        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/jsp/user-management-edit-user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserSignupRequest user = RequestMappingService.toModel(UserSignupRequest.class, request);
        String userId = user.getId();
        userService.updateUser(userId, user);
        response.sendRedirect(request.getContextPath() + "/edit-user?id=" + userId);
    }
}
