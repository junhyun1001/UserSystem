package org.example.user_system.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.user_system.service.UserAccountService;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
  private final UserAccountService accountService = new UserAccountService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    boolean loginSuccess = accountService.login(username, password);
    if (!loginSuccess) {
      resp.sendRedirect(req.getContextPath() + "/login"); // 재시도하도록...
      return;
    }
    HttpSession session = req.getSession();
    session.setAttribute("username", username); // 내가 연결된 session에 로그인한 username을 집어넣기
    resp.sendRedirect(req.getContextPath() + "/"); // 로그인 성공 시 main
  }
}
