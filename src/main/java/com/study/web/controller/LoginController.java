package com.study.web.controller;

import com.study.persistence.entity.User;
import com.study.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.study.web.constants.ContentConstants.*;
import static com.study.web.constants.PathConstants.SLASH_LOGIN;

@Controller
public class LoginController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private LoginService loginService;

    @GetMapping(value = SLASH_LOGIN)
    public String getLoginPage() {
        return "login";
    }

    @PostMapping(value = SLASH_LOGIN)
    public String performLogin(@RequestParam Map<String, String> params, HttpSession session, ModelMap modelMap){
        String login = params.get("login");
        String password = params.get("password");
        User user = loginService.performLogin(login, password);

        if (user != null) {
            LOG.info("User ID: {} successfully logged in. User has ROLE: {}", user.getId(), user.getRole().toString());
            session.setAttribute(USER, user);
            session.setAttribute(ROLE, user.getRole().getRoleTitle().toUpperCase());
            return "redirect:/";
        }

        modelMap.addAttribute(REQUEST_ALERT, "Wrong login or password. Please try again");
        return SLASH_LOGIN;
    }
}
