package com.study.service;

import com.study.persistence.dao.ConferenceRepository;
import com.study.persistence.dao.ReportRepository;
import com.study.persistence.dao.SpeakerRepository;
import com.study.persistence.entity.Conference;
import com.study.persistence.entity.Report;
import com.study.persistence.entity.Speaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReportService implements DBActionsService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportService.class);
    @Resource
    private ReportRepository reportRepository;
    @Resource
    private SpeakerRepository speakerRepository;
    @Resource
    private ConferenceRepository conferenceRepository;

    public List<Report> getAll() {
        LOG.info("Start getting all entities");
        return reportRepository.findAll();
    }

    public void deleteReport(Report report) {
        LOG.info("Starting deleting report entity");
        reportRepository.delete(report);
    }

    public Report createReport(Report report) {
        LOG.info("Starting creating report entity");
        return reportRepository.save(report);
    }

    public void updateReport(Report report) {
        LOG.info("Start updating report");
        reportRepository.save(report);
    }

    @Override
    public void perform(Map<String, String> params) {
        Report report = mapReportFromParams(params);
        String type = params.get("type");

        LOG.info("DB Action type: " + type);
        if (type.equalsIgnoreCase("delete")) {
            if (report.getId() != 0) {
                deleteReport(report);
            } else {
                LOG.info("Report ID 0. Could not perform delete");
            }
        }
        if (type.equalsIgnoreCase("update")) {
            if (report.getId() != 0) {
                updateReport(report);
            } else {
                LOG.info("Report ID 0. Could not perform update");
            }
        }
        if (type.equalsIgnoreCase("create")) {
            if (report.getId() == 0) {
                createReport(report);
            } else {
                LOG.info("Report ID not 0. New Report would not be created");
            }
        }

    }

    private Report mapReportFromParams(Map<String, String> params) {
        Report report = null;

        if (params.get("id") != null && !params.get("id").equals("")) {
            Optional<Report> optionalReport = reportRepository.findById(Integer
                    .parseInt(params.get("id")));
            if (optionalReport.isPresent()) {
                report = optionalReport.get();
            } else {
                report = new Report();
            }
        }
        if (params.get("title") != null && !params.get("title").equals("")) {
            report.setTitle(params
                    .get("title"));
        }
        if (params.get("timeStart") != null && !params.get("timeStart").equals("")) {
            String[] time = getTimeFromParameter(params.get("timeStart"));
            report.setTimeStart(LocalTime
                    .of(Integer.parseInt(time[0]), Integer.parseInt(time[1])));
        }
        if (params.get("speakerId") != null && !params.get("speakerId").equals("")) {
            Optional<Speaker> optionalSpeaker = speakerRepository.findById(Integer
                    .parseInt(params.get("speakerId")));
            if (optionalSpeaker.isPresent()) {
                report.setSpeaker(optionalSpeaker.get());
            }
        }
        if (params.get("conferenceId") != null && !params.get("conferenceId").equals("")) {
            Optional<Conference> optionalConference = conferenceRepository.findById(Integer
                    .parseInt(params.get("conferenceId")));
            if (optionalConference.isPresent()){
                report.setConference(optionalConference.get());
            }
        }

        if (params.get("registered") != null && !params.get("registered").equals("")) {
            report.setRegistered(Integer
                    .parseInt(params
                            .get("registered")));
        }
        if (params.get("attended") != null && !params.get("attended").equals("")) {
            report.setRegistered(Integer
                    .parseInt(params
                            .get("attended")));
        }

        return report;
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
}
