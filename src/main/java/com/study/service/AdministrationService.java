package com.study.service;

import com.study.persistence.dao.ConferenceRepository;
import com.study.persistence.entity.Conference;
import com.study.persistence.entity.Report;
import com.study.persistence.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AdministrationService implements DBActionsService {
    private static final Logger LOG = LoggerFactory.getLogger(AdministrationService.class);
    @Resource
    private ConferenceRepository conferenceRepository;
    @Resource
    private ReportService reportService;
    @Resource
    private UserService userService;

    public List<Conference> getAllConferences() {
        List<Conference> conferences = conferenceRepository.findAll();
        if (conferences.isEmpty()) {
            LOG.info("Get empty conferences list from database");
            return Collections.EMPTY_LIST;
        }
        return conferences;
    }


    public List<User> getAllUsers() {
        return userService.getAll();
    }

    public void deleteConference(int id) {
        LOG.info("Starting deleting conference entity with ID {}", id);
        conferenceRepository.deleteById(id);
    }

    public void deleteConference(Conference conference) {
        LOG.info("Starting deleting conference entity");
        conferenceRepository.delete(conference);
    }

    public Conference updateConference(Conference conference) {
        if (conference.getId() == 0) {
            LOG.error("Conference entity has ID = 0");
        }
         return conferenceRepository.save(conference);
    }

    public  Conference createConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    @Override
    public void perform(Map<String, String> params) {
        Conference conference = mapConferenceFromParams(params);
        String type = params.get("type");

        LOG.info("DB Action type: " + type);
        if (type.equalsIgnoreCase("delete")) {
            if (conference.getId() != 0) {
                deleteConference(conference);
            } else {
                LOG.info("Conference ID 0. Could not perform delete");
            }
        }
        if (type.equalsIgnoreCase("update")) {
            if (conference.getId() != 0) {
                updateConference(conference);
            } else {
                LOG.info("Conference ID 0. Could not perform update");
            }
        }
        if (type.equalsIgnoreCase("create")) {
            if (conference.getId() == 0) {
                createConference(conference);
            } else {
                LOG.info("Conference ID not 0. New Report would not be created");
            }
        }
    }

    private Conference mapConferenceFromParams(Map<String, String> params) {
        Conference conference = null;

        if (params.get("id") != null && !params.get("id").equals("")) {
            Optional<Conference> optionalConference = conferenceRepository.findById(Integer
                    .parseInt(params.get("id")));
            if (optionalConference.isPresent()){
                conference = optionalConference.get();
            } else {
                conference = new Conference();
            }

        }

        if (params.get("theme") != null && !params.get("theme").equals("")) {
            conference.setTheme(params.get("theme"));
        }

        if (params.get("plannedDate") != null && !params.get("plannedDate").equals("")) {
            String[] date = params.get("plannedDate").trim().split("\\D");
            String[] time = null;
            if (params.get("plannedTime") != null && !params.get("plannedTime").equals("")) {
                time = getTimeFromParameter(params.get("plannedTime"));
            } else {
                time = new String[]{"00", "00"};
            }

            conference.setPlannedDateTime(LocalDateTime.of(Integer.parseInt(date[2]),
                    Integer.parseInt(date[1]),
                    Integer.parseInt(date[0]),
                    Integer.parseInt(time[0]),
                    Integer.parseInt(time[1])));
        }

        if (params.get("happenedDate") != null && !params.get("happenedDate").equals("")) {
            String[] date = params.get("happenedDate").trim().split("\\D");
            String[] time = null;
            if (params.get("happenedTime") != null && !params.get("happenedTime").equals("")) {
                time = getTimeFromParameter(params.get("happenedTime"));
            } else {
                time = new String[]{"00", "00"};
            }

            conference.setPlannedDateTime(LocalDateTime.of(Integer.parseInt(date[2]),
                    Integer.parseInt(date[1]),
                    Integer.parseInt(date[0]),
                    Integer.parseInt(time[0]),
                    Integer.parseInt(time[1])));
        }

        if (params.get("address") != null && !params.get("address").equals("")) {
            conference.setAddress(params.get("address"));
        }

        return conference;
    }

    private String[] getTimeFromParameter(String timeParameter) {
        String[] time;
        if (timeParameter.contains("AM") || timeParameter.contains("PM")) {
            SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");
            try {
                time = date24Format.format(date12Format.parse(timeParameter)).trim().split(":");
            } catch (ParseException e) {
                time = new String[]{"00", "00"};
                LOG.error("Could not parse time parameter", e);
            }
            return time;
        }
        time = timeParameter.trim().split("\\D");
        return time;
    }

    public Page<Conference> getAllConferences(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return conferenceRepository.findAll(pageable);
    }

    public Page<User> getAllUsers(int page, int limit) {
        return userService.getAll(page, limit);
    }
}
