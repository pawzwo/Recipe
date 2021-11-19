package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AppRecipeEdit", value = "/app/recipe/edit")
public class AppRecipeEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        RecipeDao recipeDao = new RecipeDao();

        if (checkAdmin(recipeDao, recipeId, request)) {
            request.setAttribute("recipe", recipeDao.read(recipeId));
            request.getRequestDispatcher("/jsp/appRecipeEdit.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        String created = request.getParameter("created");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String ingredients = request.getParameter("ingredients");
        String preparation = request.getParameter("preparation");
        String _preparationTime = request.getParameter("preparationTime");

        if (name != null && description != null && ingredients != null && preparation != null && _preparationTime != null &&
                !name.isBlank() && !description.isBlank() && !ingredients.isBlank() && !preparation.isBlank() && !_preparationTime.isBlank() &&
                !name.isEmpty() && !description.isEmpty() && !ingredients.isEmpty() && !preparation.isEmpty() && !_preparationTime.isEmpty()) {

            int preparationTime = Integer.parseInt(_preparationTime);
            HttpSession session = request.getSession();
            Admins admin = (Admins) session.getAttribute("admin");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            Recipe recipe = new Recipe(name, ingredients, description, created,
                    simpleDateFormat.format(date), preparationTime, preparation, admin.getId());
            recipe.setId(recipeId);
            RecipeDao recipeDao = new RecipeDao();
            recipeDao.update(recipe);
            response.sendRedirect("/app/recipe/list");
        } else {
            response.sendRedirect("/app/recipe/add?badData=1");
        }

    }

    private boolean checkAdmin(RecipeDao recipeDao, Integer recipeId, HttpServletRequest request) {
        boolean checkAdmin = false;
        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        List<Recipe> recipesOfAdmin = recipeDao.findAllAdminRecipe(admin.getId());
        for (Recipe recipe : recipesOfAdmin) {
            if (recipe.getId() == recipeId) {
                checkAdmin = true;
                break;
            }
        }
        return checkAdmin;
    }
}
