package com.study.persistence.dao;

import com.study.persistence.entity.Report;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Integer> {
}
