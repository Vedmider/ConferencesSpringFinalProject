package com.study.persistence.dao;

import com.study.persistence.entity.Conference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
    @Override
    Page<Conference> findAll(Pageable pageable);
}
