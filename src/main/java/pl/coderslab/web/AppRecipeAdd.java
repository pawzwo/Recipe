package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AppRecipeAdd", value = "/app/recipe/add")
public class AppRecipeAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/appRecipeAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String ingredients = request.getParameter("ingredients");
        String preparation = request.getParameter("preparation");
        String _preparationTime = request.getParameter("preparationTime");

        if (name!=null && description!=null && ingredients!=null && preparation!=null && _preparationTime!=null &&
        !name.isBlank() && !description.isBlank() && !ingredients.isBlank() && !preparation.isBlank() && !_preparationTime.isBlank() &&
            !name.isEmpty() && !description.isEmpty() && !ingredients.isEmpty() && !preparation.isEmpty() && !_preparationTime.isEmpty()) {
            int preparationTime=-1;
            try {
                preparationTime = Integer.parseInt(_preparationTime);
            } catch (NumberFormatException er) {
                er.getLocalizedMessage();
            }
            HttpSession session = request.getSession();
            Admins admin = (Admins) session.getAttribute("admin");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
            Date date = new Date(System.currentTimeMillis());
            Recipe recipe = new Recipe(name, ingredients, description,simpleDateFormat.format(date),
                    "0", preparationTime, preparation, admin.getId());
            RecipeDao recipeDao = new RecipeDao();
            Recipe recipe1 = recipeDao.create(recipe);
            if (recipe1==null) {
                response.sendRedirect("/app/recipe/add?notCreated=1");
            } else {
                response.sendRedirect("/app/recipe/list");
            }

        } else {
            response.sendRedirect("/app/recipe/add?badData=1");
        }
    }
}
