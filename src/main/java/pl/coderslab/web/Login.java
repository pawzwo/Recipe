package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AdminDao adminDao = new AdminDao();
        if (email != null && password != null && !email.isBlank() && !password.isBlank() && !email.isEmpty() && !password.isEmpty()) {
            System.out.println("step1");
            if (adminDao.verifyEmail(email)) {
                System.out.println("step2");
                if (adminDao.verifyPassword(email, password)) {
                    int adminId = adminDao.readByEmail(email).getId();
                    int superadmin = adminDao.readByEmail(email).getSuperAdmin();
                    System.out.println(adminId);
                    System.out.println(superadmin);
                    HttpSession session = request.getSession();
                    session.setAttribute("id", adminId);
                    session.setAttribute("superadmin", superadmin);
                    session.setMaxInactiveInterval(3600);
                    response.sendRedirect("/jsp/dashboard.jsp");
                } else {
                    response.sendRedirect("/login?password=0");
                }
            } else {
                response.sendRedirect("/login?email=0");
            }
        }


    }
}