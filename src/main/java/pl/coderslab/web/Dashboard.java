package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.PlanDays;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Dashboard", value = "/dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (true) { //session.getAttribute("id")!=null
           // int adminId = (Integer) session.getAttribute("id");
            int adminId=2; // to do usuniecia jak juz bedzie login
            RecipeDao recipeDao = new RecipeDao();
            PlanDao planDao = new PlanDao();
            request.setAttribute("recipeQua",recipeDao.recipeQuantity(adminId));
            request.setAttribute("planQua",planDao.countPlans(adminId));
            request.setAttribute("planName",planDao.lastPlanName(adminId));
            List<PlanDays> planDays = planDao.readLastPlan(adminId);
            int[] daysMeal =new int [7]; //this table give me information how many different days we have
            for (PlanDays planDay : planDays) {
                if (planDay.getDayName().equals("poniedziałek")) {
                    daysMeal[0]++;
                } else if (planDay.getDayName().equals("wtorek")) {
                    daysMeal[1]++;
                } else if (planDay.getDayName().equals("środa")) {
                    daysMeal[2]++;
                } else if (planDay.getDayName().equals("czwartek")) {
                    daysMeal[3]++;
                } else if (planDay.getDayName().equals("piątek")) {
                    daysMeal[4]++;
                } else if (planDay.getDayName().equals("sobota")) {
                    daysMeal[5]++;
                } else if (planDay.getDayName().equals("niedziela")) {
                    daysMeal[6]++;
                }
            }
            List <String> days = new ArrayList<>();
            int j =0;
            for (int i : daysMeal) {
                if (i!=0) {
                    if (j==0) {
                        days.add("poniedziałek");
                    } else if (j==1) {
                        days.add("wtorek");
                    }else if (j==2) {
                        days.add("środa");
                    }else if (j==3) {
                        days.add("czwartek");
                    }else if (j==4) {
                        days.add("piątek");
                    }else if (j==5) {
                        days.add("sobota");
                    }else if (j==6) {
                        days.add("niedziela");
                    }
                }
                j++;
            }
            request.setAttribute("plan",planDays);
            request.setAttribute("daysMeal",daysMeal);
            request.setAttribute("days",days);
        }
        request.getRequestDispatcher("/jsp/appDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
