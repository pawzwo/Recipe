package pl.coderslab.model;

public class RecipePlan {
    private int id;
    private int recipe_id;
    private String meal_name;
    private int displayOrder;
    private int dayNameId;
    private int planId;

    public RecipePlan(int recipe_id, String meal_name, int displayOrder, int dayNameId, int planId) {
        this.recipe_id = recipe_id;
        this.meal_name = meal_name;
        this.displayOrder = displayOrder;
        this.dayNameId = dayNameId;
        this.planId = planId;
    }

    public RecipePlan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getDayNameId() {
        return dayNameId;
    }

    public void setDayNameId(int dayNameId) {
        this.dayNameId = dayNameId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
}
