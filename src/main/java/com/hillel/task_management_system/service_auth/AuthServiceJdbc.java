package com.hillel.task_management_system.service_auth;

import com.hillel.task_management_system.dao.AuthDao;
import com.hillel.task_management_system.exceptions.UserSqlException;
import com.hillel.task_management_system.model.Auth;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Primary
@ConditionalOnProperty(prefix = "app.connection", name = "type", havingValue = "jdbc")
public class AuthServiceJdbc implements AuthService{

    private final AuthDao authDao;

    public AuthServiceJdbc(AuthDao authDao) {
        this.authDao = authDao;
    }

    public Auth getAuthByLogin(String login) throws SQLException {
        if (authDao.getAuthByLogin(login) == null) {
            throw new UserSqlException("Error: Can't get Auth from DB. Auth with login " + login
                    + " doesn't exist in DB.");
        } else {
            return authDao.getAuthByLogin(login);
        }
    }
}
