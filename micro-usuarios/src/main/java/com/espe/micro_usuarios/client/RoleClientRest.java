package com.espe.micro_usuarios.client;

import com.espe.micro_usuarios.model.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-roles", url = "localhost:8005/api/roles")
public interface RoleClientRest {
    @GetMapping("/{id}")
    Role findById(@PathVariable Long id);
}