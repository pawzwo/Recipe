package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RecipePlanDao {

    private final String CREATE_PLAN_RECIPE_QUERY ="INSERT INTO scrumlab.recipe_plan (recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?, ?, ?, ?, ?)";
    private final String DELETE_PLAN_RECIPE_QUERY = "DELETE FROM recipe_plan WHERE id = ?;";
    private final String CHECK_IF_RECIPE_IS_IN_PLAN ="SELECT * FROM scrumlab.recipe_plan WHERE recipe_id=?";




    public RecipePlan create(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setInt(1, recipePlan.getRecipe_id());
            insertStm.setString(2, recipePlan.getMeal_name());
            insertStm.setInt(3, recipePlan.getDisplayOrder());
            insertStm.setInt(4, recipePlan.getDayNameId());
            insertStm.setInt(5, recipePlan.getPlanId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipePlan.setId(generatedKeys.getInt(1));

                    return recipePlan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer recipePlanId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_RECIPE_QUERY)) {
            statement.setInt(1, recipePlanId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");

    public RecipePlan checkRecipeInPlan(Integer recipeId) {
        RecipePlan recipePlan = new RecipePlan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_IF_RECIPE_IS_IN_PLAN)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                int i = 0;
                while (resultSet.next()) {
                    recipePlan.setId(resultSet.getInt("id"));
                    recipePlan.setRecipe_id(resultSet.getInt("recipe_id"));
                    recipePlan.setMeal_name(resultSet.getString("meal_name"));
                    recipePlan.setDisplayOrder(resultSet.getInt("display_order"));
                    recipePlan.setDayNameId(resultSet.getInt("day_name_id"));
                    recipePlan.setPlanId(resultSet.getInt("plan_id"));
                    i++;
                }
                if (i==0) {
                    return null;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        return recipePlan;

    }




}
