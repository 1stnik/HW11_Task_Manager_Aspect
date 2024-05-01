package com.hillel.task_management_system.dao;

import com.hillel.task_management_system.config.ConnectionConfig;
import com.hillel.task_management_system.exceptions.UserSqlException;
import com.hillel.task_management_system.model.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AuthDao {

    @Autowired
    private ConnectionConfig connectionConfig;

    public Auth getAuthByLogin(String login) throws UserSqlException {
        String sql = "SELECT * FROM auths WHERE login = ?";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String login_name = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    return new Auth(id, login_name, password);
                }
            }
        } catch (SQLException e) {
            throw new UserSqlException("Error: Failed to get Auth by Login (Dao)");
        }
        return null;
    }
}
