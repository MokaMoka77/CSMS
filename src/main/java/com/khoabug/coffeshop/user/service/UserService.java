package com.khoabug.coffeshop.user.service;


import com.khoabug.coffeshop.common.paging.Pageable;
import com.khoabug.coffeshop.common.services.GMailService;
import com.khoabug.coffeshop.common.services.GeneratePasswordService;
import com.khoabug.coffeshop.common.services.PBKDF2Hasher;
import com.khoabug.coffeshop.user.infrastructure.repository.UserRepository;
import com.khoabug.coffeshop.user.model.User;
import com.khoabug.coffeshop.user.model.UserSignupRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

/**
 * @author : DangKhoa
 * @since : 3/6/2023, Mon
 **/
@ApplicationScoped
public class UserService {

    private final String WELL_COME = "Chào mừng bạn đến với tiệm cà phê CSMS";

    @Inject
    private PBKDF2Hasher pbkdf2Hasher;
    @Inject
    private UserRepository userRepository;

    public void addUser(UserSignupRequest registerUses) {

        String password = GeneratePasswordService.generateSecureRandomPassword();

        String hashedPassword = pbkdf2Hasher.hash(password.toCharArray());

        registerUses.setPassword(hashedPassword);

        registerUses.setRoleId(registerUses.getRoleId());

        int index = userRepository.count() + 1;

        registerUses.setId("A" + index);

        userRepository.save(registerUses);

        GMailService.sendEmail(registerUses.getEmail(), WELL_COME, "Mật khẩu nè: " + password);
    }

    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public void updateUser(String id, UserSignupRequest changedUser) {
        User user = userRepository.findById(id).orElseThrow();

        user.setName(changedUser.getName());
        user.getRole().setId(changedUser.getRoleId());
        user.setEmail(changedUser.getEmail());

        userRepository.save(user);
    }

    public void changeActiveUser(String id, Boolean isActive) {
        User user = userRepository.findById(id).orElseThrow();

        user.setActive(isActive);

        userRepository.save(user);
    }

    public int count() {
        return userRepository.count();
    }

}
