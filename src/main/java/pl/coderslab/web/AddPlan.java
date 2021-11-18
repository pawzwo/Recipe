package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AddPlan", value = "/app/plan/add")
public class AddPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/appAddSchedules.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String planName = request.getParameter("planName");
        String planDescription = request.getParameter("planDescription");

        if (planName != null && planDescription != null && !planName.isBlank() && !planDescription.isBlank() && !planName.isEmpty() && !planDescription.isEmpty()) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String created  = simpleDateFormat.format(date);
            HttpSession session = request.getSession();
            Admins admin = (Admins) session.getAttribute("admin");
            int adminId = admin.getId();

            PlanDao planDao = new PlanDao();
            if (!checkIfPlanExists(planDao, planName)) {
                Plan newPlan = new Plan(planName, planDescription, created, adminId);
                planDao.create(newPlan);
                response.sendRedirect("/app/plan/list");
            } else {
                response.sendRedirect("/app/plan/add?planName=1");
            }
        } else {response.sendRedirect("/app/plan/add?planName=0");

        }
    }

    public boolean checkIfPlanExists(PlanDao planDao, String planName) {
        boolean checkIfPlanExists = false;
        List<Plan> allPlansList =  planDao.findAll();
        for (Plan plan:allPlansList) {
            if (plan.getName().equals(planName)) {
                checkIfPlanExists = true;
                break;
            }
        }
        return checkIfPlanExists;
    }
}
