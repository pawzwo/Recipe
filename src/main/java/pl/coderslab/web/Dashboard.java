package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.DayName;
import pl.coderslab.model.PlanDays;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Dashboard", value = "/app/dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("admin")!=null) {
            Admins admin = (Admins) session.getAttribute("admin");
            int adminId=admin.getId();
            RecipeDao recipeDao = new RecipeDao();
            PlanDao planDao = new PlanDao();
            List<PlanDays> planDays = planDao.readLastPlan(adminId);

            List <String> days = uniqueDays(planDays);

            request.setAttribute("plan",planDays);
            request.setAttribute("days",days);
            request.setAttribute("name",admin.getFirstName());
            request.setAttribute("recipeQua",recipeDao.recipeQuantity(adminId));
            request.setAttribute("planQua",planDao.countPlans(adminId));
            request.setAttribute("planName",planDao.lastPlanName(adminId));
        }
        request.getRequestDispatcher("/jsp/appDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private List <String> uniqueDays (List<PlanDays> planDays) {
        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> weekDays = dayNameDao.findAll();
        List <String> days = new ArrayList<>();
        for (DayName weekDay : weekDays) {
            for (PlanDays planDay : planDays) {
                if(weekDay.getName().equals(planDay.getDayName())) {
                    days.add(planDay.getDayName());
                    break;
                }
            }
        }
        return  days;
    }
}
