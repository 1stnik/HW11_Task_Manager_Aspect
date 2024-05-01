package com.hillel.task_management_system.config;

import com.hillel.task_management_system.dao.AuthDao;
import com.hillel.task_management_system.dao.TaskDao;
import com.hillel.task_management_system.dao.UserDao;
import com.hillel.task_management_system.model.Auth;
import com.hillel.task_management_system.repository.AuthDaoJpa;
import com.hillel.task_management_system.repository.TaskDaoJpa;
import com.hillel.task_management_system.repository.UserDaoJpa;
import com.hillel.task_management_system.service.TaskServiceJdbc;
import com.hillel.task_management_system.service.TaskServiceJpa;
import com.hillel.task_management_system.service.UserServiceJdbc;
import com.hillel.task_management_system.service.UserServiceJpa;
import com.hillel.task_management_system.service_auth.AuthServiceJdbc;
import com.hillel.task_management_system.service_auth.AuthServiceJpa;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ConfigTaskApplication {

    @Bean
    @ConditionalOnProperty(prefix = "app.connection", name = "type", havingValue = "jdbc")
    public AuthServiceJdbc authServiceJdbc(AuthDao authDao) {
        return new AuthServiceJdbc(authDao);
    }

    @Bean
    @ConditionalOnProperty(prefix = "app.connection", name = "type", havingValue = "jpa")
    public AuthServiceJpa authServiceJpa(AuthDaoJpa authDaoJpa) {
        return new AuthServiceJpa(authDaoJpa);
    }

    @Bean
    @ConditionalOnProperty(prefix = "app.connection", name = "type", havingValue = "jdbc")
    public TaskServiceJdbc taskServiceJdbc(TaskDao taskDao, UserDao userDao) {
        return new TaskServiceJdbc(taskDao, userDao);
    }

    @Bean
    @ConditionalOnProperty(prefix = "app.connection", name = "type", havingValue = "jdbc")
    public UserServiceJdbc userServiceJdbc(UserDao userDao) {
        return new UserServiceJdbc(userDao);
    }

    @Bean
    @ConditionalOnProperty(prefix = "app.connection", name = "type", havingValue = "jpa")
    public TaskServiceJpa taskServiceJpa(TaskDaoJpa taskDaoJpa, UserDaoJpa userDaoJpa) {
        return new TaskServiceJpa(taskDaoJpa, userDaoJpa);
    }

    @Bean
    @ConditionalOnProperty(prefix = "app.connection", name = "type", havingValue = "jpa")
    public UserServiceJpa userServiceJpa(UserDaoJpa userDaoJpa) {
        return new UserServiceJpa(userDaoJpa);
    }

    @Bean
    public Auth auth() {
        return new Auth();
    }

    // Connections

    @Bean
    public ConnectionConfig connectionConfig() {
        return new ConnectionConfig();
    }

    @Bean
    public DataSource dbDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("08080808");
        return dataSource;
    }
}
