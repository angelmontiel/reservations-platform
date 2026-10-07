package com.kukultec.reservations_service.service.user;

import com.kukultec.reservations_service.dto.request.user.UserRequest;
import com.kukultec.reservations_service.dto.response.user.UserResponse;
import com.kukultec.reservations_service.entity.Business;
import com.kukultec.reservations_service.entity.User;
import com.kukultec.reservations_service.exception.ResourceNotFoundException;
import com.kukultec.reservations_service.mapper.user.UserMapper;
import com.kukultec.reservations_service.repository.business.BusinessRepository;
import com.kukultec.reservations_service.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse save(UserRequest request) {
        Business business = resolveBusinessOrNull(request.businessId());
        String passwordHash = passwordEncoder.encode(request.password());

        User user = userMapper.mapToEntity(request, business, passwordHash);
        User savedUser = userRepository.save(user);

        return userMapper.mapToResponse(savedUser);
    }

    public UserResponse getById(Long id) {
        User user = findUserOrThrow(id);
        return userMapper.mapToResponse(user);
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToResponse)
                .toList();
    }

    public UserResponse update(Long id, UserRequest request) {
        User existingUser = findUserOrThrow(id);
        Business business = resolveBusinessOrNull(request.businessId());

        existingUser.setBusiness(business);
        existingUser.setUsername(request.username());
        existingUser.setEmail(request.email());
        existingUser.setRole(request.role());
        // Solo re-hashea si mandaron una contraseña nueva distinta de vacío.
        // (En un endpoint real de "cambiar contraseña" esto se separa aparte,
        // nunca se actualiza junto con username/email en el mismo request).
        if (request.password() != null && !request.password().isBlank()) {
            existingUser.setPasswordHash(passwordEncoder.encode(request.password()));
        }

        User updatedUser = userRepository.save(existingUser);

        return userMapper.mapToResponse(updatedUser);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    /** businessId es opcional (un CUSTOMER puede no tener negocio asociado). */
    private Business resolveBusinessOrNull(Long businessId) {
        if (businessId == null) {
            return null;
        }
        return businessRepository.findById(businessId)
                .orElseThrow(() -> new ResourceNotFoundException("Negocio no encontrado"));
    }
}