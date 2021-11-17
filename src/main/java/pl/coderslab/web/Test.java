package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.PlanDays;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "Test", value = "/test")
public class Test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PlanDao planDao = new PlanDao();
        List<PlanDays> readLastPlan = planDao.readLastPlan(1);
        Set<String> dayNames = new HashSet<>();
        for (PlanDays el: readLastPlan) {
            dayNames.add(el.getDayName()); //set z dniami tygodnia bo plan moze nie byc na wszystkie dni
        }

        for (String day: dayNames) {
            List<PlanDays> oneDayMenuList = new ArrayList<>();
            for (PlanDays el: readLastPlan) {
                if (day.equals(el.getDayName())) {
                    oneDayMenuList.add(el);
                }
            }
            System.out.println(day);
            for (PlanDays meal:oneDayMenuList) {
                System.out.println(meal.getMealName() + " " +meal.getRecipeName());
            }
            request.setAttribute(day, oneDayMenuList); //w jsp w petli wstawiasz header tabeli i nazwe atrybutu do headera, a
        }                                              // w zagniezdzonej petli wyrzucasz kolejne rzedy z komorkami z .getMealName i .getRecipe .
                                                        // z wartosci atrybutu






    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
