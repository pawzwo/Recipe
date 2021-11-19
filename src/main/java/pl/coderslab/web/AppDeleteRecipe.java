package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Plan;
import pl.coderslab.model.RecipePlan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AppDeleteRecipe", value = "/app/delete/recipe")
public class AppDeleteRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _recipeId = request.getParameter("recipeId");
        if (_recipeId != null) {
            int recipeId = Integer.parseInt(_recipeId);
            RecipeDao recipeDao = new RecipeDao();
            HttpSession session = request.getSession();
            Admins admin = (Admins) session.getAttribute("admin");
            if (recipeDao.readAdminRecipe(recipeId, admin.getId()) != null) {
                RecipePlanDao recipePlanDao = new RecipePlanDao();
                RecipePlan recipePlan = recipePlanDao.checkRecipeInPlan(recipeId);
                if (recipePlan == null) {
                    recipeDao.delete(recipeId);
                    response.sendRedirect("/app/recipe/list");
                } else {
                    PlanDao planDao = new PlanDao();
                    Plan read = planDao.read(recipePlan.getPlanId());
                    session.setAttribute("planName",read.getName());
                    response.sendRedirect("/app/recipe/list?");
                }

            } else {
                session.removeAttribute("admin");
                response.sendRedirect("/login");
            }
        } else {
            request.getRequestDispatcher("/jsp/appRecipeList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
