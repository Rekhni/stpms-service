package com.rakhymzhan.stmps_service.service;

import com.rakhymzhan.stmps_service.model.User;
import com.rakhymzhan.stmps_service.model.dto.CreateUserRequest;
import com.rakhymzhan.stmps_service.model.dto.UpdateUserRequest;
import com.rakhymzhan.stmps_service.model.dto.UserResponse;
import com.rakhymzhan.stmps_service.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserResponse createUser(CreateUserRequest request) {
        User user = User.builder()
                .fullName(request.fullName())
                .phoneNumber(request.phoneNumber())
                .build();

        return toResponse(userRepo.save(user));
    }

    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found, id=" + id));

        if (request.fullName() != null) {
            user.setFullName(request.fullName());
        }
        if (request.phoneNumber() != null) {
            user.setPhoneNumber(request.phoneNumber());
        }

        return toResponse(userRepo.save(user));
    }

    public List<UserResponse> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found, id=" + id));
        return toResponse(user);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getPhoneNumber()
        );
    }

    public void deleteUser(Long id)  {
        userRepo.deleteById(id);
    }
}