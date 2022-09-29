package ru.mal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mal.model.Role;
import ru.mal.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}