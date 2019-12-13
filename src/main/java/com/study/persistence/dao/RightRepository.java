package com.study.persistence.dao;

import com.study.persistence.entity.Right;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RightRepository extends JpaRepository<Right, Integer> {
    @Override
    Page<Right> findAll(Pageable pageable);
}
