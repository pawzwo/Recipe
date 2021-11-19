package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AppRecipeList", value = "/app/recipe/list")
public class AppRecipeList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("admin")!=null) {
            String _recipeId = request.getParameter("recipeId");
            if (_recipeId!=null) {
                int recipeId = Integer.parseInt(_recipeId);
                request.setAttribute("delete", recipeId);
            }
            if (session.getAttribute("planName")!=null) {
                request.setAttribute("planName",(String)session.getAttribute("planName"));
                session.removeAttribute("planName");
            }


            Admins admin = (Admins) session.getAttribute("admin");
            RecipeDao recipeDao = new RecipeDao();
            List<Recipe> allAdminRecipe = recipeDao.findAllAdminRecipe(admin.getId());
            request.setAttribute("allAdminRecipe",allAdminRecipe);
            request.getRequestDispatcher("/jsp/appRecipeList.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
