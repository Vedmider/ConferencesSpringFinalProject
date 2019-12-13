package com.study.service;

import com.study.persistence.dao.RoleRepository;
import com.study.persistence.dao.SpeakerRepository;
import com.study.persistence.entity.Role;
import com.study.persistence.entity.Speaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SpeakerService implements DBActionsService {
    private static final Logger LOG = LoggerFactory.getLogger(SpeakerService.class);
    @Resource
    private SpeakerRepository speakerRepository;
    @Resource
    private RoleRepository roleRepository;

    public List<Speaker> getAllSpeakers() {
        List<Speaker> speakers = speakerRepository.findAll();
        if (speakers.isEmpty()) {
            LOG.info("Get empty list from database");
        } else {
            return speakers;
        }
        return Collections.EMPTY_LIST;
    }

    public void deleteSpeaker(Speaker speaker) {
        speakerRepository.delete(speaker);
    }

    public Speaker createSpeaker(Speaker speaker) {
        return speakerRepository.save(speaker);
    }

    public void updateSpeaker(Speaker speaker) {
        speakerRepository.save(speaker);
    }

    public Optional<Speaker> getById(int id) {
        return speakerRepository.findById(id);
    }

    @Override
    public void perform(Map<String, String> params) {
        String type = params.get("type");
        Speaker speaker = mapSpeakerFromParams(params);

        LOG.info("DB Action type: " + type);
        if (type.equalsIgnoreCase("delete")) {
            if (speaker.getId() != 0) {
                deleteSpeaker(speaker);
            } else {
                LOG.info("Speaker ID 0. Could not perform delete");
            }
        }
        if (type.equalsIgnoreCase("update")) {
            if (speaker.getId() != 0) {
                updateSpeaker(speaker);
            } else {
                LOG.info("Speaker ID 0. Could not perform update");
            }
        }
        if (type.equalsIgnoreCase("create")) {
            if (speaker.getId() == 0) {
                createSpeaker(speaker);
            }
        } else {
            LOG.info("Speaker ID not 0. New Report would not be created");
        }

    }


    private Speaker mapSpeakerFromParams(Map<String, String> params) {
        Speaker speaker = null;

        if (params.get("id") != null && !params.get("id").equals("")) {
            Optional<Speaker> optionalSpeaker = speakerRepository.findById((Integer.parseInt(params.get("id"))));
            if (optionalSpeaker.isPresent()){
                speaker = optionalSpeaker.get();
            }
        } else {
            speaker = new Speaker();
        }

        if (params.get("login") != null && !params.get("login").equals("")) {
            speaker.setLogin(params.get("login"));
        }
        if (params.get("password") != null && !params.get("password").equals("")) {
            speaker.setPassword(params.get("password"));
        }
        if (params.get("firstName") != null && !params.get("firstName").equals("")) {
            speaker.setFirstName(params.get("firstName"));
        }
        if (params.get("lastName") != null && !params.get("lastName").equals("")) {
            speaker.setLastName(params.get("lastName"));
        }
        if (params.get("email") != null && !params.get("email").equals("")) {
            speaker.setEmail(params.get("email"));
        }
        if (params.get("userRole") != null && !params.get("userRole").equals("")) {
            Optional<Role> optionalRole = roleRepository.findById(Integer
                    .parseInt(params
                            .get("userRole")));
            if (optionalRole.isPresent()){
                speaker.setRole(optionalRole.get());
            }

        }

        if (params.get("rating") != null && !params.get("rating").equals("")) {
            speaker.setRating(Integer
                    .parseInt(params
                            .get("rating")));
        }
        if (params.get("bonuses") != null && !params.get("bonuses").equals("")) {
            speaker.setBonuses(Integer
                    .parseInt(params
                            .get("bonuses")));
        }

        return speaker;
    }

    public Page<Speaker> getAllSpeakers(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Speaker> speakers = speakerRepository.findAll(pageable);
        if (speakers.isEmpty()) {
            LOG.info("Get empty list from database");
        }

        return speakers;
    }
}
