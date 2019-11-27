package com.study.persistence.dao;

import com.study.persistence.entity.Speaker;
import org.springframework.data.repository.CrudRepository;

public interface SpeakerRepository extends CrudRepository<Speaker, Integer> {
}
