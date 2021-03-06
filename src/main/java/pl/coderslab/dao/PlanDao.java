package pl.coderslab.dao;


import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.PlanDays;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name, description, created, admin_id) VALUES (?,?,?,?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan;";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ?, adminId = ? WHERE	id = ?;";
    private static final String COUNT_PLANS_QUERY = "SELECT COUNT(*) AS plans FROM plan WHERE admin_id=?;";
    private static final String READ_LAST_PLAN_QUERY = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.id as recipeId\n" +
            "FROM `recipe_plan`\n" +
            "JOIN day_name on day_name.id=day_name_id\n" +
            "JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String FIND_ALL_PLANS_BY_ADMIN_QUERY = "SELECT * FROM plan WHERE admin_id=? ORDER BY created DESC;";
    private static final String READ_LAST_PLAN_NAME_QUERY = "SELECT name from plan where id=(SELECT MAX(id) from plan WHERE admin_id = ?)";
    private static final String READ_PLAN_DETAILS_QUERRY = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.id as recipeId, recipe_plan.id as recipe_plan_Id\n" +
            "FROM recipe_plan\n" +
            "JOIN day_name on day_name.id=day_name_id\n" +
            "JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "recipe_plan.plan_id = ?\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String READ_PLAN_BY_PLANID_ADMINID_QUERY = "SELECT * FROM plan WHERE id = ? AND admin_id = ?;";




    public Plan readByPlanIdAdminId(Integer planId, Integer adminId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_BY_PLANID_ADMINID_QUERY)
        ) {
            statement.setInt(1, planId);
            statement.setInt(2, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;
    }

    public List<PlanDays> readPlanDetails(int planId) {
        List<PlanDays> planDaysList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_DETAILS_QUERRY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    PlanDays planDay = new PlanDays();
                    planDay.setDayName(resultSet.getString("day_name"));
                    planDay.setMealName(resultSet.getString("meal_name"));
                    planDay.setRecipeName(resultSet.getString("recipe_name"));
                    planDay.setRecipeId(resultSet.getInt("recipeId"));
                    planDay.setRecipePlanId(resultSet.getInt("recipe_plan_Id"));
                    planDaysList.add(planDay);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planDaysList;
    }

    public List<Plan> findAllByAdmin(int adminId) {
        List<Plan> planListByAdmin = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_BY_ADMIN_QUERY)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Plan plan = new Plan();
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));

                    planListByAdmin.add(plan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planListByAdmin;
    }

    public String lastPlanName(int adminId) {
        String name = "";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_LAST_PLAN_NAME_QUERY)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    name = resultSet.getString("name");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public List<PlanDays> readLastPlan(int adminId) {
        List<PlanDays> planDaysList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_LAST_PLAN_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    PlanDays planDay = new PlanDays();
                    planDay.setDayName(resultSet.getString("day_name"));
                    planDay.setMealName(resultSet.getString("meal_name"));
                    planDay.setRecipeName(resultSet.getString("recipe_name"));
                    planDay.setRecipeId(resultSet.getInt("recipeId"));
                    planDaysList.add(planDay);


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planDaysList;
    }

    public int countPlans(int adminId) {
        int numPlans = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_PLANS_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    numPlans = resultSet.getInt("plans");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numPlans;
    }

    public Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }

    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }

    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setString(3, plan.getCreated());
            insertStm.setInt(4, plan.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));

                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(4, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3, plan.getCreated());
            statement.setInt(5, plan.getAdminId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
