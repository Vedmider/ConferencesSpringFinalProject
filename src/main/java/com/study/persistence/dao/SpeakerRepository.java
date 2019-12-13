package com.study.persistence.dao;

import com.study.persistence.entity.Speaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {
    @Override
    Page<Speaker> findAll(Pageable pageable);
}
