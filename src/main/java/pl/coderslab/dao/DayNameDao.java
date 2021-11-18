package pl.coderslab.dao;


import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admins;
import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {

    // ZAPYTANIA SQL

    private static final String FIND_ALL_DAYNAMES_QUERY = "SELECT * FROM day_name;";


    /**
     * Return all DayName
     *
     * @return list dayNames
     */
    public List<DayName> findAll() {
        List<DayName> dayNameList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_DAYNAMES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DayName dayName = new DayName();
                dayName.setName(resultSet.getString("name"));
                dayName.setDisplayOrder(resultSet.getInt("display_order"));
                dayName.setDayNameId(resultSet.getInt("id"));
                dayNameList.add(dayName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayNameList;
    }

}
