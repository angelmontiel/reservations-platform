package com.kukultec.reservations_service.repository.user;

import com.kukultec.reservations_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}