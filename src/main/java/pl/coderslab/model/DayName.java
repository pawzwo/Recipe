package pl.coderslab.model;

public class DayName {

    private int dayNameId = 0;
    private String name = "";
    private int displayOrder = 0;

    public DayName() {
    }

    public DayName(String name, int displayOrder) {
        this.name = name;
        this.displayOrder = displayOrder;
    }

    @Override
    public String toString() {
        return "DayName{" +
                "dayNameId=" + dayNameId +
                ", name='" + name + '\'' +
                ", displayOrder=" + displayOrder +
                '}';
    }

    public int getDayNameId() {
        return dayNameId;
    }

    public void setDayNameId(int dayNameId) {
        this.dayNameId = dayNameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
