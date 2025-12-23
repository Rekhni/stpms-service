package com.rakhymzhan.stmps_service.repo;


import com.rakhymzhan.stmps_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    // при необходимости сюда добавишь поиск по ФИО/телефону
}