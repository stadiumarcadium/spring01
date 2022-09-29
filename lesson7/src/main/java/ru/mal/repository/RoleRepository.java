package ru.mal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mal.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}