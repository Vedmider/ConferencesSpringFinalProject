package com.study.persistence.dao;

import com.study.persistence.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Override
    Page<Report> findAll(Pageable pageable);
}
