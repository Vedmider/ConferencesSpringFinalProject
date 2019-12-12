package com.study.web.controller;

import com.study.persistence.entity.Conference;
import com.study.persistence.entity.Speaker;
import com.study.service.AdministrationService;
import com.study.service.SpeakerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

import java.util.List;

import static com.study.web.constants.PathConstants.SLASH_SPEAKERS;

@Controller
public class SpeakerPageController {
    private static final Logger LOG = LoggerFactory.getLogger(SpeakerPageController.class);
    @Resource
    private SpeakerService speakerService;
    @Resource
    private AdministrationService administrationService;

    @GetMapping(value = SLASH_SPEAKERS)
    public String getSpeakersPage(ModelMap modelMap){
        List<Speaker> speakers = speakerService.getAllSpeakers();
        List<Conference> conferences = administrationService.getAllConferences();
        if (!speakers.isEmpty()) {
            modelMap.addAttribute("speakers", speakers);
        }
        if (!conferences.isEmpty()) {
            modelMap.addAttribute("conferences", conferences);
        }

        return "speakers";
    }
}
