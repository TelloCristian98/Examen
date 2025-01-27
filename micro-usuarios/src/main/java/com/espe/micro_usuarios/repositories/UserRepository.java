package com.espe.micro_usuarios.repositories;

import com.espe.micro_usuarios.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}