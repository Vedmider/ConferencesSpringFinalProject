package com.study.persistence.dao;

import com.study.persistence.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
   @Override
   Page<User> findAll(Pageable pageable);
}
