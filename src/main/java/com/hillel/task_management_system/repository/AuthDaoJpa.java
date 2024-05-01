package com.hillel.task_management_system.repository;

import com.hillel.task_management_system.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthDaoJpa extends JpaRepository<Auth, Integer> {

    Auth findAuthByLogin(String login);
}
