package com.study.web.controller;

import com.study.persistence.entity.Conference;
import com.study.persistence.entity.Speaker;
import com.study.persistence.entity.User;
import com.study.service.AdministrationService;
import com.study.service.ReportService;
import com.study.service.SpeakerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.study.web.constants.PathConstants.ADMINISTRATION;
import static com.study.web.constants.PathConstants.SLASH_ADMINISTRATION;

@Controller
public class AdministrationPageController {
    private static final Logger LOG = LoggerFactory.getLogger(AdministrationPageController.class);
    private static final int LIMIT = 10;
    @Resource
    private AdministrationService administrationService;
    @Resource
    private SpeakerService speakerService;
    @Resource
    private ReportService reportService;

    @GetMapping(value = SLASH_ADMINISTRATION)
    public String administrationPage(@RequestParam Map<String, String> requestParams,ModelMap modelMap){
        processRequest(modelMap, requestParams);
        return ADMINISTRATION;
    }

    private void processRequest(ModelMap modelMap, Map<String, String> request){
        int gotoPage;
        int maxRecords;
        int maxPages;
        List<Integer> recordsInTables  = new ArrayList<>();

        recordsInTables.add(administrationService.getAllConferences().size());
        recordsInTables.add(speakerService.getAllSpeakers().size());
        recordsInTables.add(administrationService.getAllUsers().size());
        recordsInTables.add(reportService.getAll().size());

        maxRecords = Collections.max(recordsInTables);
        maxPages = maxRecords/LIMIT;

        if (maxRecords % LIMIT > 0){
            maxPages++;
        }

        if(request.get("gotoPage") == null){
            gotoPage = 1;
        } else {
            gotoPage = Integer.valueOf(request.get("gotoPage"));
        }

        if (gotoPage > maxPages || gotoPage < 1){
            return;
        }

        if (gotoPage > 1){
            modelMap.addAttribute("previous", gotoPage - 1);
        }

        if (gotoPage < maxPages){
            modelMap.addAttribute("next", gotoPage + 1);
        }

        addPageAttributes(gotoPage, modelMap);
    }

    private void addPageAttributes(int page, ModelMap modelMap){
        List<Conference> conferences = administrationService.getAllConferences(page - 1, LIMIT).getContent();
        List<Speaker> speakers = speakerService.getAllSpeakers(page - 1, LIMIT).getContent();
        List<User> users = administrationService.getAllUsers(page - 1, LIMIT).getContent();

        if (!conferences.isEmpty()) {
            LOG.info("Adding conferences list to administration page");
            modelMap.addAttribute("conferences", conferences);
        }

        if (!speakers.isEmpty()) {
            LOG.info("Adding speakers list to administration page");
            modelMap.addAttribute("speakers", speakers);
        }

        if (!users.isEmpty()) {
            LOG.info("Adding users list to administration page");
            modelMap.addAttribute("users", users);
        }
    }
}
