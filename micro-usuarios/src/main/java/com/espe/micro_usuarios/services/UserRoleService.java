package com.espe.micro_usuarios.services;

import com.espe.micro_usuarios.client.RoleClientRest;
import com.espe.micro_usuarios.model.Role;
import com.espe.micro_usuarios.model.UserRole;
import com.espe.micro_usuarios.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleClientRest roleClientRest;

    public List<UserRole> findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    public List<UserRole> findByRoleId(Long roleId) {
        return userRoleRepository.findByRoleId(roleId);
    }

    public Optional<UserRole> assignRoleToUser(Long userId, Long roleId) {
        Role role = roleClientRest.findById(roleId);
        if (role == null) {
            return Optional.empty();
        }

        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);

        return Optional.of(userRoleRepository.save(userRole));
    }

    public void revokeRoleFromUser(Long userId, Long roleId) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        userRoles.stream()
                .filter(ur -> ur.getRoleId().equals(roleId))
                .findFirst()
                .ifPresent(userRoleRepository::delete);
    }
}