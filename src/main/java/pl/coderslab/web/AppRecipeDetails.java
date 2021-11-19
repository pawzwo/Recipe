package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AppRecipeDetails", value = "/app/recipe/details")
public class AppRecipeDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _recipeId = request.getParameter("recipeId");
        int recipeId = -1;
        String dashboard = request.getParameter("dashboard");
        if (dashboard!=null) {
            request.setAttribute("dashboard",1);
        }
        String list = request.getParameter("list");
        if (list!=null) {
            request.setAttribute("list",1);
        }
        try {
            recipeId=Integer.parseInt(_recipeId);
        }catch (NumberFormatException er) {
            er.getLocalizedMessage();
        }
        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        RecipeDao recipeDao = new RecipeDao();
        if(recipeDao.readAdminRecipe(recipeId, admin.getId())!=null) {
            Recipe read = recipeDao.read(recipeId);
            String[] ingredients = read.getIngredients().split(",");
            request.setAttribute("recipe",read);
            request.setAttribute("ingredients",ingredients);

            request.getRequestDispatcher("/jsp/appRecipeDetails.jsp").forward(request,response);
        } else {
            session.removeAttribute("admin");
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
