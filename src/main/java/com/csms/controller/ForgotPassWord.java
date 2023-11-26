package com.csms.controller;

import java.io.IOException;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import com.csms.dal.AccountDAO;
import com.csms.utils.SecretHolder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Authenticator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;

@WebServlet(name = "ForgotPassword", value = "/forgotpassword")
public class ForgotPassWord extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();
    private static final String FORGOT_PASSWORD_JSP = "forgotPassword.jsp";

    private void sendEmail(String to, String subject, String htmlContent) {
        final String from = "csms.prj301@gmail.com";
        final String password = "ybaynitxsdtdedta";
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/Html; charset=UTF-8");
            msg.setFrom(from);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setContent(htmlContent, "text/html; charset=UTF-8");

            Transport.send(msg);
        } catch (MessagingException e) {
            logger.error("Error sending email: {}", e.getMessage());
        }
    }

    private String createToken(String email) {
        String secret = SecretHolder.getSecretKeyAsString();
        Key key = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(15, ChronoUnit.MINUTES)))
                .signWith(key)
                .compact();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(FORGOT_PASSWORD_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAO adb = new AccountDAO();
        String to = request.getParameter("email");
        if (!adb.checkEmailExist(to)) {
            logger.warn("Email does not exist: {}", to);
            response.getWriter().write("error:Email không tồn tại trong hệ thống.");
        } else {
            String fullName = adb.getFullName(to);
            String token = createToken(to);
            String link = "http://localhost:8080/changepassword?token=" + token;
            String subject = "Thay đổi mật khẩu";
            String htmlContent = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head><title>Thay đổi mật khẩu</title></head>\n" +
                    "<body style=\"margin: 0; padding: 0; font-family: Arial, sans-serif; background-color: #f4f4f4;\">\n" +
                    "<div style=\"width: 70%; max-width: 600px; margin: 0 auto; overflow: hidden;\">\n" +
                    "<div style=\"background-color: #007bff; color: #ffffff; text-align: center; padding: 20px 0;\"><h1 style=\"margin: 0;\">Thay Đổi Mật Khẩu</h1></div>\n" +
                    "<div style=\"background-color: #ffffff; padding: 20px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\">\n" +
                    "<p style=\"margin: 0; font-size: 16px; line-height: 24px;\">Hello " + fullName + "!</p>\n" +
                    "<p style=\"margin: 0; font-size: 16px; line-height: 24px;\">Nhấn vào nút dưới đây để thay đổi mật khẩu của bạn:</p>\n" +
                    "<div style=\"text-align: center; margin: 10px 0;\">\n" +
                    "<a href=\"" + link + "\" style=\"display: inline-block; padding: 10px 20px; background-color: #007bff; color: #ffffff; text-decoration: none; border-radius: 5px;\">Thay Đổi Mật Khẩu</a>\n" +
                    "</div>\n" +
                    "<p style=\"margin: 0; font-size: 16px; line-height: 24px;\">Nếu bạn không muốn thay đổi mật khẩu, vui lòng bỏ qua email này.</p></div>\n" +
                    "<div style=\"background-color: #007bff; color: #ffffff; text-align: center; padding: 10px 0;\"><p style=\"margin: 0;\">Copyright © CSMS 2023</p></div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            try {
                sendEmail(to, subject, htmlContent);
                logger.info("Email sent successfully");
                String successMessage = "Chúng tôi đã gửi một email chứa liên kết để thay đổi mật khẩu đến hộp thư của bạn. " +
                        "Vui lòng kiểm tra email và làm theo hướng dẫn để tiến hành thay đổi mật khẩu.";
                response.getWriter().write("success:" + successMessage);
            } catch (Exception e) {
                logger.error("Error at ForgotPassword: {}", e.getMessage());
            }
        }
    }

}