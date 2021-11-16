package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Do not change servlet address !!!
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 0_RecipeDao


        System.out.println("Hello sample");
        System.out.println("hello 2");

        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.findAll();
        System.out.println(books);


develop


        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
