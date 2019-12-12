package com.study.persistence.dao;

import com.study.persistence.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Override
    Page<Role> findAll(Pageable pageable);
}
