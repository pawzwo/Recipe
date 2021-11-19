package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admins;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AdminDao adminDao = new AdminDao();
        if (email != null && password != null && !email.isBlank() && !password.isBlank() && !email.isEmpty() && !password.isEmpty()) {
            if (adminDao.verifyEmail(email)) {
                if (adminDao.verifyPassword(email, password)) {
                    Admins adminToSession = adminDao.readByEmail(email);
                    HttpSession session = request.getSession();
                    session.setAttribute("admin", adminToSession);
                    session.setMaxInactiveInterval(3600);
                    Cookie cookie = new Cookie("name", adminToSession.getFirstName());
                    cookie.setMaxAge(3600);
                    response.addCookie(cookie);
                    response.sendRedirect("/app/dashboard");
                } else {
                    response.sendRedirect("/login?password=0");
                }
            } else {
                response.sendRedirect("/login?email=0");
            }
        } else {
            response.sendRedirect("/login");
        }
    }
}