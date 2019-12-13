package com.study.service;

import com.study.persistence.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);
    @Resource
    private AdministrationService administrationService;

    public User performLogin (String login, String password){
        User user = getUser(login, password);

        if (user != null){
            LOG.info("userEntity login {} password {} id {}", user.getLogin(), user.getPassword(), user.getId());
            return user;
        }
        return null;
    }

    private User getUser(String login, String password){
        return  administrationService.getAllUsers()
                .stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(login))
                .filter(user -> user.getPassword().equals(password))
                .findAny().orElse(null);
    }

}
