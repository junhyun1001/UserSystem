package org.example.user_system.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/content/*")
public class ContentsController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String path = req.getRequestURI();
    String value = path.split("/")[2];
    req.setAttribute("value", value);
    req.getRequestDispatcher("/WEB-INF/views/contents.jsp").forward(req, resp);
  }
}
