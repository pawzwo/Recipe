package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {
    // ZAPYTANIA SQL
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO scrumlab.recipe (name, ingredients, " +
            "description, created, preparation_time, preparation, admin_id) " +
            "VALUES (?, ?, ?, ?,?, ?, ?)";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM scrumlab.recipe where id = ?;";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM scrumlab.recipe;";
    private static final String READ_RECIPE_QUERY = "SELECT * from scrumlab.recipe where id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE	scrumlab.recipe SET name = ? , ingredients = ?," +
            " description= ? , updated = ? , preparation_time = ?, preparation= ? , admin_id= ? " +
            "WHERE	id = ?;";
    private static final String SELECT_RECIPES_BY_ID_QUERY = "SELECT COUNT(*) AS recipes FROM recipe WHERE admin_id=?";
    private static final String FIND_ALL_ADMIN_RECIPES_QUERY = "SELECT * FROM scrumlab.recipe WHERE admin_id=?;";
    private static final String CHECK_IF_ADMIN_HAS_RECIPE_QUERY = "SELECT * FROM scrumlab.recipe WHERE admin_id=? AND id=?;";
    /**
     * Get book by id
     *
     * @param recipeId
     * @return
     */
    public Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;

    }

    /**
     * Return all books
     *
     * @return
     */
    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getString("created"));
                recipeToAdd.setUpdated(resultSet.getString("updated"));
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }

    /**
     * Create book
     *
     * @param recipe
     * @return
     */
    public Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, recipe.getName());
            insertStm.setString(2, recipe.getIngredients());
            insertStm.setString(3, recipe.getDescription());
            insertStm.setString(4, recipe.getCreated());
            insertStm.setInt(5, recipe.getPreparationTime());
            insertStm.setString(6, recipe.getPreparation());
            insertStm.setInt(7, recipe.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Remove book by id
     *
     * @param recipeId
     */
    public void delete(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update book
     *
     * @param recipe
     */
    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {

            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
            statement.setString(4, recipe.getUpdated());
            statement.setInt(5, recipe.getPreparationTime());
            statement.setString(6, recipe.getPreparation());
            statement.setInt(7, recipe.getAdminId());
            statement.setInt(8,recipe.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public int recipeQuantity (int adminId) {
        int quantity = -1;
        try (Connection connection = DbUtil.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECIPES_BY_ID_QUERY);
            preparedStatement.setInt(1,adminId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    quantity=resultSet.getInt("recipes");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }

    public List<Recipe> findAllAdminRecipe(int adminId) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMIN_RECIPES_QUERY)){
            statement.setInt(1,adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipeToAdd = new Recipe();
                    recipeToAdd.setId(resultSet.getInt("id"));
                    recipeToAdd.setName(resultSet.getString("name"));
                    recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                    recipeToAdd.setDescription(resultSet.getString("description"));
                    recipeToAdd.setCreated(resultSet.getString("created"));
                    recipeToAdd.setUpdated(resultSet.getString("updated"));
                    recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipeToAdd.setPreparation(resultSet.getString("preparation"));
                    recipeToAdd.setAdminId(resultSet.getInt("admin_id"));
                    recipeList.add(recipeToAdd);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }

    public Recipe readAdminRecipe(int recipeId, int adminId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_IF_ADMIN_HAS_RECIPE_QUERY)
        ) {
            statement.setInt(1, adminId);
            statement.setInt(2, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;

    }



}