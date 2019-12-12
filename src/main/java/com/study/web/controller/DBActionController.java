package com.study.web.controller;

import com.study.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import java.util.Map;

import static com.study.web.constants.PathConstants.SLASH_ADMINISTRATION;
import static com.study.web.constants.PathConstants.SLASH_DB_ACTION;

@Controller
public class DBActionController {
    private static final Logger LOG = LoggerFactory.getLogger(DBActionController.class);
    @Resource
    private AdministrationService administrationService;
    @Resource
    private SpeakerService speakerService;
    @Resource
    private ReportService reportService;
    @Resource
    private UserService userService;

    @GetMapping(value = SLASH_DB_ACTION)
    public String getDBAction(@RequestParam Map<String, String> params, ModelMap modelMap){
        performDBAction(params);
        return "redirect:" + SLASH_ADMINISTRATION;
    }

    @PostMapping(value = SLASH_DB_ACTION)
    public String postDBAction(@RequestParam Map<String, String> params, ModelMap modelMap){
        performDBAction(params);
        return "redirect:" + SLASH_ADMINISTRATION;
    }

    private void performDBAction(Map<String, String> params) {
        DBActionsService service = getService(params.get("entity"));
        service.perform(params);
    }

    private DBActionsService getService(String serviceType) {
        if (serviceType.equalsIgnoreCase("conference")) {
            LOG.info("Returning administration Service in DB Action Command");
            return administrationService;
        } else if (serviceType.equalsIgnoreCase("report")) {
            LOG.info("Returning report Service in DB Action Command");
            return reportService;
        } else if (serviceType.equalsIgnoreCase("user")) {
            LOG.info("Returning user Service in DB Action Command");
            return userService;
        } else if (serviceType.equalsIgnoreCase("speaker")) {
            LOG.info("Returning speaker Service in DB Action Command");
            return speakerService;
        }
        return null;
    }
}
