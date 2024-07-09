package com.atuandev.identityService.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.atuandev.identityService.dto.request.RoleRequest;
import com.atuandev.identityService.dto.response.RoleResponse;
import com.atuandev.identityService.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
