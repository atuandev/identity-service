package com.atuandev.identityService.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import com.atuandev.identityService.dto.request.UserCreationRequest;
import com.atuandev.identityService.dto.response.UserResponse;
import com.atuandev.identityService.entity.User;
import com.atuandev.identityService.exception.AppException;
import com.atuandev.identityService.repository.UserRepository;

@SpringBootTest
@TestPropertySource("/test.properties")
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
    @WithMockUser(username = "boiboi")
    void createUser_validRequest_success() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);

        // THEN
        assertThat(response.getId()).isEqualTo("7edc48b15e2b");
        assertThat(response.getUsername()).isEqualTo("boiboi");
        assertThat(response.getFirstName()).isEqualTo("Anh");
        assertThat(response.getLastName()).isEqualTo("Tuấn");
        assertThat(response.getDob()).isEqualTo(dob);
    }

    @Test
    @WithMockUser(username = "boiboi")
    void createUser_existingUsername_fail() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN, THEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));

        assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }

    @Test
    @WithMockUser(username = "boiboi")
    void getMyInfo_valid_success() {
        // GIVEN
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        // WHEN
        var response = userService.getMyInfo();

        // THEN
        assertThat(response.getId()).isEqualTo("7edc48b15e2b");
        assertThat(response.getUsername()).isEqualTo("boiboi");
        assertThat(response.getFirstName()).isEqualTo("Anh");
        assertThat(response.getLastName()).isEqualTo("Tuấn");
        assertThat(response.getDob()).isEqualTo(dob);
    }
}
