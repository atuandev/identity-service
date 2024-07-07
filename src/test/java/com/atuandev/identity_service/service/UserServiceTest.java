package com.atuandev.identity_service.service;

import com.atuandev.identity_service.dto.request.UserCreationRequest;
import com.atuandev.identity_service.dto.response.UserResponse;
import com.atuandev.identity_service.entity.User;
import com.atuandev.identity_service.exception.AppException;
import com.atuandev.identity_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(2003, 1, 16);

        request = UserCreationRequest.builder()
                .username("boiboi")
                .firstName("Anh")
                .lastName("Tuấn")
                .password("zxczxczxc")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("7edc48b15e2b")
                .username("boiboi")
                .firstName("Anh")
                .lastName("Tuấn")
                .dob(dob)
                .build();

        user = User.builder()
                .id("7edc48b15e2b")
                .username("boiboi")
                .firstName("Anh")
                .lastName("Tuấn")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var result = userService.createUser(request);

        // THEN
        assertThat(result.getId()).isEqualTo("7edc48b15e2b");
        assertThat(result.getUsername()).isEqualTo("boiboi");
        assertThat(result.getFirstName()).isEqualTo("Anh");
        assertThat(result.getLastName()).isEqualTo("Tuấn");
        assertThat(result.getDob()).isEqualTo(dob);
    }

    @Test
    void createUser_existingUsername_fail() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN, THEN
       var exception = assertThrows(AppException.class, () -> userService.createUser(request));

       assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }
}
