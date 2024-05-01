package com.hillel.task_management_system.service_auth;

import com.hillel.task_management_system.exceptions.UserSqlException;
import com.hillel.task_management_system.model.Auth;
import com.hillel.task_management_system.repository.AuthDaoJpa;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@ConditionalOnProperty(prefix = "app.connection", name = "type", havingValue = "jpa")
public class AuthServiceJpa implements AuthService{

    private final AuthDaoJpa authDaoJpa;

    public AuthServiceJpa(AuthDaoJpa authDaoJpa) {
        this.authDaoJpa = authDaoJpa;
    }

    public Auth getAuthByLogin(String login) throws SQLException {
        if (authDaoJpa.findAuthByLogin(login) == null) {
            throw new UserSqlException("Error: Can't get Auth from DB. Auth with login " + login
                    + " doesn't exist in DB.");
        } else {
            return authDaoJpa.findAuthByLogin(login);
        }
    }
}
