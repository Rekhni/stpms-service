package com.rakhymzhan.stmps_service.controller;


import com.rakhymzhan.stmps_service.model.dto.CreateUserRequest;
import com.rakhymzhan.stmps_service.model.dto.UpdateUserRequest;
import com.rakhymzhan.stmps_service.model.dto.UserResponse;
import com.rakhymzhan.stmps_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9000")
public class UserController {

    private final UserService userService;

    // Создать пользователя
    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    // Обновить пользователя
    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ) {
        return userService.updateUser(id, request);
    }

    // Получить всех
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // Получить по id
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}