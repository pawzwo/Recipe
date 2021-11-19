package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admins;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AppDeleteRecipeFromPlan", value = "/app/recipe/plan/delete")
public class AppDeleteRecipeFromPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipePlanId = Integer.parseInt(request.getParameter("recipePlanId"));
        int planId = Integer.parseInt(request.getParameter("planId"));
        System.out.println(recipePlanId);
        System.out.println(planId);
        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        System.out.println(checkAdmin(planId, admin));
        if (checkAdmin(planId, admin)) {
            RecipePlanDao recipePlanDao = new RecipePlanDao();
            recipePlanDao.delete(recipePlanId);
            request.getRequestDispatcher("/app/plan/details?planId="+planId).forward(request, response);
        } else {
            response.sendRedirect("/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean checkAdmin(int planId, Admins admin) {
        PlanDao planDao = new PlanDao();
        boolean checkAdmin = false;
        if (planDao.readByPlanIdAdminId(planId, admin.getId()) != null) {
            checkAdmin = true;
        }
        return checkAdmin;
    }
}
