package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AppPlanList", value = "/app/plan/list")
public class AppPlanList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        PlanDao planDao = new PlanDao();
        List<Plan> planListByAdmin = planDao.findAllbyAdmin(admin.getId());
        request.setAttribute("planList", planListByAdmin);
        System.out.println("success");
        request.getRequestDispatcher("/jsp/appPlanList.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
