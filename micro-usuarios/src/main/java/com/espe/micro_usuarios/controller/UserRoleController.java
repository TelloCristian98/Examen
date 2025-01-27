package com.espe.micro_usuarios.controller;

import com.espe.micro_usuarios.model.UserRole;
import com.espe.micro_usuarios.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<?> assignRole(@PathVariable Long userId, @PathVariable Long roleId) {
        return userRoleService.assignRoleToUser(userId, roleId)
                .map(role -> ResponseEntity.status(201).body(role))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/roles")
    public List<UserRole> listRoles(@PathVariable Long userId) {
        return userRoleService.findByUserId(userId);
    }

    @GetMapping("/roles/{roleId}/users")
    public List<UserRole> listUsersByRole(@PathVariable Long roleId) {
        return userRoleService.findByRoleId(roleId);
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<?> revokeRole(@PathVariable Long userId, @PathVariable Long roleId) {
        userRoleService.revokeRoleFromUser(userId, roleId);
        return ResponseEntity.noContent().build();
    }
}