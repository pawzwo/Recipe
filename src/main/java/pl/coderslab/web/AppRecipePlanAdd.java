package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AppRecipePlanAdd", value = "/app/recipe/plan/add")
public class AppRecipePlanAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        DayNameDao dayNameDao = new DayNameDao();

        List<Plan> allPlan = planDao.findAllByAdmin(admin.getId());
        List<Recipe> allRec = recipeDao.findAllAdminRecipe(admin.getId());
        List<DayName> allDay = dayNameDao.findAll();

        request.setAttribute("allRecipe", allRec);
        request.setAttribute("allPlan", allPlan);
        request.setAttribute("allDays", allDay);

        request.getRequestDispatcher("/jsp/appRecipePlanAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String plan = request.getParameter("plan");
        String _number = request.getParameter("number");
        String recipe = request.getParameter("recipe");
        String day = request.getParameter("day");

        if (name != null && plan != null && _number != null && recipe != null && day != null &&
                !name.isEmpty() && !plan.isEmpty() && !_number.isEmpty() && !recipe.isEmpty() && !day.isEmpty() &&
                !name.isBlank() && !plan.isBlank() && !_number.isBlank() && !recipe.isBlank() && !day.isBlank()) {
            int dayNameId = -1;
            int recipeId=-1;
            int number = -1;
            int planId = -1;
            try {
                number = Integer.parseInt(_number);
            } catch (NumberFormatException er) {
                er.getLocalizedMessage();
            }
            HttpSession session = request.getSession();
            Admins admin = (Admins) session.getAttribute("admin");
            PlanDao planDao = new PlanDao();
            RecipeDao recipeDao = new RecipeDao();
            DayNameDao dayNameDao = new DayNameDao();

            List<Plan> allPlan = planDao.findAllByAdmin(admin.getId());
            List<Recipe> allRec = recipeDao.findAllAdminRecipe(admin.getId());
            List<DayName> allDay = dayNameDao.findAll();

            for (Plan plan1 : allPlan) {
                if(plan1.getName().equals(plan)) {
                    planId=plan1.getId();
                }
            }
            for (Recipe recipe1 : allRec) {
                if(recipe1.getName().equals(recipe)) {
                    recipeId=recipe1.getId();
                }
            }
            for (DayName dayName : allDay) {
                if (dayName.getName().equals(day)) {
                    dayNameId=dayName.getDayNameId();
                }
            }

            RecipePlan recipePlan = new RecipePlan(recipeId, name, number, dayNameId, planId);
            RecipePlanDao recipePlanDao = new RecipePlanDao();
            RecipePlan recipePlan1 = recipePlanDao.create(recipePlan);
            if (recipePlan1==null) {
                response.sendRedirect("/app/recipe/plan/add?dataBaseError=1");
            } else {
                response.sendRedirect("/app/recipe/plan/add");
            }
        } else {
            response.sendRedirect("/app/recipe/plan/add?inputError=1");
        }
    }
}
