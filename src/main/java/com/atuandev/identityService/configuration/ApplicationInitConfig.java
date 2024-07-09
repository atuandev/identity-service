package com.atuandev.identityService.configuration;

import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.atuandev.identityService.entity.Role;
import com.atuandev.identityService.entity.User;
import com.atuandev.identityService.exception.AppException;
import com.atuandev.identityService.exception.ErrorCode;
import com.atuandev.identityService.repository.RoleRepository;
import com.atuandev.identityService.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class ApplicationInitConfig {
    private static final String DEFAULT_USERNAME = "admin";

    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername(DEFAULT_USERNAME).isEmpty()) {
                var roles = new HashSet<Role>();
                roles.add(
                        roleRepository.findById("ADMIN").orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND)));
                User user = User.builder()
                        .username(DEFAULT_USERNAME)
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();

                userRepository.save(user);
                log.warn("Admin user created with username: 'admin' and password: 'admin', please change it!");
            }
        };
    }
}
