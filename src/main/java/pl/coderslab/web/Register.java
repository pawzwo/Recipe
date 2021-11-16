package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admins;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        if (name!=null && surname!=null && email!=null && password!=null && repassword!=null
        && !name.isEmpty() && !surname.isEmpty() && !email.isEmpty() && !password.isEmpty() && !repassword.isEmpty()
        && !name.isBlank() && !surname.isBlank() && !email.isBlank() && !password.isBlank() && !repassword.isBlank()) {
            if (!password.equals(repassword)) {
                response.sendRedirect("/register?pass=0&name="+name+"&surname="+surname+"&email="+email);
            } else {
                AdminDao adminDao = new AdminDao();
                Admins admins = new Admins(name, surname, email, password);
                adminDao.create(admins);

                response.sendRedirect("/login");
            }
        }
    }
}
