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
//            int[] daysMeal =new int [7]; //this table give me information how many different days we have
//            for (PlanDays planDay : planDays) {
//                if (planDay.getDayName().equals("poniedziałek")) {
//                    daysMeal[0]++;
//                } else if (planDay.getDayName().equals("wtorek")) {
//                    daysMeal[1]++;
//                } else if (planDay.getDayName().equals("środa")) {
//                    daysMeal[2]++;
//                } else if (planDay.getDayName().equals("czwartek")) {
//                    daysMeal[3]++;
//                } else if (planDay.getDayName().equals("piątek")) {
//                    daysMeal[4]++;
//                } else if (planDay.getDayName().equals("sobota")) {
//                    daysMeal[5]++;
//                } else if (planDay.getDayName().equals("niedziela")) {
//                    daysMeal[6]++;
//                }
//            }
            List <String> days = uniqueDays(planDays);
//            int j =0;
//            for (int i : daysMeal) {
//                if (i!=0) {
//                    if (j==0) {
//                        days.add("poniedziałek");
//                    } else if (j==1) {
//                        days.add("wtorek");
//                    }else if (j==2) {
//                        days.add("środa");
//                    }else if (j==3) {
//                        days.add("czwartek");
//                    }else if (j==4) {
//                        days.add("piątek");
//                    }else if (j==5) {
//                        days.add("sobota");
//                    }else if (j==6) {
//                        days.add("niedziela");
//                    }
//                }
//                j++;
//            }
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
