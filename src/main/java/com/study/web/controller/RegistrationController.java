package com.study.web.controller;

import com.study.persistence.entity.User;
import com.study.service.UserService;
import com.study.web.data.AjaxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

import static com.study.web.constants.PathConstants.SLASH_REGISTRATION;

@Controller
public class RegistrationController {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);
    @Resource
    private UserService userService;

    @GetMapping(value = SLASH_REGISTRATION)
    public String getRegistrationPage(){
        return "registration";
    }

    @PostMapping(value = SLASH_REGISTRATION)
    @ResponseBody
    public AjaxResponse performRegistration(@RequestBody Map<String, String> params){
        String login = params.get("login");
        String password = params.get("password");
        String email = params.get("email");
        String firstName = params.get("first-name");
        String lastName = params.get("last-name");

        LOG.info("Try to login : {}, password : {} ", login, password);
        AjaxResponse ajaxResponse = new AjaxResponse();

        if (login == null || password == null || email == null || firstName == null || lastName == null ) {
            ajaxResponse.setMessage("Empty values! Please, try  again.");
            return ajaxResponse;
        }

        Optional<User> user = userService.validateUser(login, password);
        if(user.isPresent()){
            ajaxResponse.setUrl("");
            ajaxResponse.setSuccess(true);
            return ajaxResponse;
        }

        ajaxResponse.setMessage("Invalid credential! Please, try again.");
        return ajaxResponse;
    }
}
