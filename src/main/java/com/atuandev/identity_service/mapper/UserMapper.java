package com.atuandev.identity_service.mapper;

import com.atuandev.identity_service.dto.request.UserCreationRequest;
import com.atuandev.identity_service.dto.request.UserUpdateRequest;
import com.atuandev.identity_service.dto.response.UserResponse;
import com.atuandev.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);
    List<UserResponse> toUserResponseList(List<User> users);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
