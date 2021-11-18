package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDays;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AppPlanDetails", value = "/app/plan/details")
public class AppPlanDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("planId"));
        PlanDao planDao = new PlanDao();

        if (checkAdmin(planDao, planId, request)) {
            if (daysToDisplay(planDao, planId).isEmpty()) {
                request.setAttribute("komunikat", "Żryj co chcesz albo uzupełnij plan leniu!");
            }
            request.setAttribute("days", daysToDisplay(planDao, planId));
            request.setAttribute("planDays", planDao.readPlanDetails(planId));
            request.setAttribute("planDescr", planDao.read(planId));
            request.getRequestDispatcher("/jsp/appPlanDetails.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean checkAdmin(PlanDao planDao, Integer planId, HttpServletRequest request) {
        boolean checkAdmin = false;
        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        List<Plan> plansOfAdmin = planDao.findAllByAdmin(admin.getId());
        for (Plan plan : plansOfAdmin) {
            if (plan.getId() == planId) {
                checkAdmin = true;
                break;
            }
        }
        return checkAdmin;
    }

    private List<String> daysToDisplay(PlanDao planDao, int planId) {
        List<String> daysToDisplay = new ArrayList<>();
        DayNameDao dayNameDao = new DayNameDao();
        List<PlanDays> planDaysList = planDao.readPlanDetails(planId);
        List<DayName> dayNameList = dayNameDao.findAll();

        for (DayName day : dayNameList) {
            for (PlanDays planDay : planDaysList) {
                if (planDay.getDayName().equals(day.getName())) {
                    daysToDisplay.add(planDay.getDayName());
                    break;
                }
            }
        }
        return daysToDisplay;
    }
}
