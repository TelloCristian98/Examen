package com.espe.micro_roles.repositories;

import com.espe.micro_roles.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}