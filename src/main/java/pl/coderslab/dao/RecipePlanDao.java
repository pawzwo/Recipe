package pl.coderslab.dao;

import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RecipePlanDao {

    private final String CREATE_PLAN_RECIPE_QUERY ="INSERT INTO scrumlab.recipe_plan (recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?, ?, ?, ?, ?)";






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

}
