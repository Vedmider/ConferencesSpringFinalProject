package com.study.service;

import com.study.persistence.dao.RoleRepository;
import com.study.persistence.dao.UserRepository;
import com.study.persistence.entity.Role;
import com.study.persistence.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements DBActionsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;


    public Optional<User> validateUser(String login, String password) {
        List<User> all = userRepository.findAll();

        return all.stream()
                .filter(u -> u.getLogin().equals(login)
                        && u.getPassword().equals(password))
                .findFirst();
    }

    public Optional<User> findByLogin(String login) {
        List<User> all = userRepository.findAll();

        return all.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
    }

    public boolean isExist(String login) {
        List<User> all = userRepository.findAll();

        return all.stream()
                .anyMatch(u -> u.getLogin().equals(login));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void perform(Map<String, String> params) {
        String type = params.get("type");
        User user = mapUserFromParams(params);

        LOG.info("DB Action type: " + type);
        if (type.equalsIgnoreCase("delete")) {
            if (user.getId() != 0) {
                deleteUser(user);
            } else {
                LOG.info("User ID 0. Could not perform delete");
            }
        }
        if (type.equalsIgnoreCase("update")) {
            if (user.getId() != 0) {
                updateUser(user);
            } else {
                LOG.info("User ID 0. Could not perform update");
            }
        }
        if (type.equalsIgnoreCase("create")) {
            if (user.getId() == 0) {
                createUser(user);
            }
        } else {
            LOG.info("User ID not 0. New Report would not be created");
        }
    }

    private User mapUserFromParams(Map<String, String> params) {
        User user = null;

        if (params.get("id") != null && !params.get("id").equals("")) {
            LOG.info("Setting User ID to {}", params.get("id"));
            Optional<User> optionalUser = userRepository.findById(Integer.parseInt(params.get("id")));
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        } else {
            user = new User();
        }

        if (params.get("login") != null && !params.get("login").equals("")) {
            user.setLogin(params.get("login"));
        }
        if (params.get("password") != null && !params.get("password").equals("")) {
            user.setPassword(params.get("password"));
        }
        if (params.get("firstName") != null && !params.get("firstName").equals("")) {
            user.setFirstName(params.get("firstName"));
        }
        if (params.get("lastName") != null && !params.get("lastName").equals("")) {
            user.setLastName(params.get("lastName"));
        }
        if (params.get("email") != null && !params.get("email").equals("")) {
            user.setEmail(params.get("email"));
        }
        if (params.get("userRole") != null && !params.get("userRole").equals("")) {
            Optional<Role> optionalRole = roleRepository.findById(Integer
                    .parseInt(params
                            .get("userRole")));
            if (optionalRole.isPresent()){
                user.setRole(optionalRole.get());
            }

        }

        return user;
    }

    public Page<User> getAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return userRepository.findAll(pageable);
    }
}
