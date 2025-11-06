package org.example.user_system.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 1. username만 삭제해서 로그아웃
    // req.getSession().removeAttribute("username");
    // 2. session 자체를 만료시킴
    req.getSession().invalidate(); // 메모리에서 현재 세션을 삭제
    resp.sendRedirect(req.getContextPath() + "/login");
  }
}