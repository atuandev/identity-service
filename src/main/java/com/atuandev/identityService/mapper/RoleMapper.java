package com.atuandev.identityService.mapper;

import com.atuandev.identityService.dto.request.RoleRequest;
import com.atuandev.identityService.dto.response.RoleResponse;
import com.atuandev.identityService.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
