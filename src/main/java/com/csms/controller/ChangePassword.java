package com.csms.controller;

import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import com.csms.dal.AccountDAO;
import com.csms.utils.SecretHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.spec.SecretKeySpec;

@WebServlet(name = "ChangePassword", value = "/changepassword")
public class ChangePassword extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    private static final String CHANGE_PASSWORD_JSP = "changePassword.jsp";

    private Claims parsedToken(String token) {
        String secret = SecretHolder.getSecretKeyAsString();
        Key key = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.error("Invalid token: {}", e.getMessage());
            return Jwts.claims();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        Date expirationDate = parsedToken(token).getExpiration();
        Date now = new Date();

        if (expirationDate != null && expirationDate.after(now)) {
            request.getRequestDispatcher(CHANGE_PASSWORD_JSP).forward(request, response);
        } else {
            logger.warn("Token expired: {}", token);
            response.sendRedirect("403Forbidden.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        AccountDAO adb = new AccountDAO();

        String token = request.getParameter("token");
        String userName = parsedToken(token).getSubject();
        String newPass = request.getParameter("newpass");

        Date expirationDate = parsedToken(token).getExpiration();
        Date now = new Date();

        try {
            if (expirationDate != null && expirationDate.after(now)) {
                if (adb.checkPasswordExist(userName, newPass)) {
                    logger.warn("Password change request: New password is the same as the old password. User: {}", userName);
                    response.getWriter().write("error:Mật khẩu mới giống mật khẩu cũ. Vui lòng chọn một mật khẩu khác.");
                } else if (adb.changePassword(userName, newPass)) {
                    logger.info("Password changed successfully. User: {}", userName);
                    response.getWriter().write("success:Mật khẩu của bạn đã được cập nhật.");
                } else {
                    logger.warn("Password change failed");
                }
            } else {
                logger.warn("Token expired: {}", token);
                response.getWriter().write("invalid:403Forbidden.jsp");
            }
        } catch (Exception e) {
            logger.error("Error at ChangePassword: {}", e.getMessage());
        }
    }
}