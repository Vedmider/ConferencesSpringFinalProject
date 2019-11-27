package com.study.persistence.dao;

import com.study.persistence.entity.Conference;
import org.springframework.data.repository.CrudRepository;

public interface ConferenceRepository extends CrudRepository<Conference, Integer> {
}
