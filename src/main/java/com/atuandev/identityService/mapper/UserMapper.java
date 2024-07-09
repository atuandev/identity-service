package com.atuandev.identityService.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.atuandev.identityService.dto.request.UserCreationRequest;
import com.atuandev.identityService.dto.request.UserUpdateRequest;
import com.atuandev.identityService.dto.response.UserResponse;
import com.atuandev.identityService.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
