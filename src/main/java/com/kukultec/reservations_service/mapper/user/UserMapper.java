package com.kukultec.reservations_service.mapper.user;

import com.kukultec.reservations_service.dto.request.user.UserRequest;
import com.kukultec.reservations_service.dto.response.user.UserResponse;
import com.kukultec.reservations_service.entity.Business;
import com.kukultec.reservations_service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToEntity(UserRequest request, Business business, String passwordHash) {
        return User.builder()
                .business(business) // puede quedar null
                .username(request.username())
                .email(request.email())
                .passwordHash(passwordHash)
                .role(request.role())
                .build();
    }

    public UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getBusiness() != null ? user.getBusiness().getId() : null,
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}