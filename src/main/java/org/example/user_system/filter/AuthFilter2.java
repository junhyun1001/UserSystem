package org.example.user_system.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/login", "/join"})
public class AuthFilter2 implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse resp = (HttpServletResponse) servletResponse;

    HttpSession session = req.getSession();
    if (session.getAttribute("username") != null) {
      resp.sendRedirect(req.getContextPath() + "/");
      return;
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }
}
