package com.atuandev.identityService.mapper;

import org.mapstruct.Mapper;

import com.atuandev.identityService.dto.request.PermissionRequest;
import com.atuandev.identityService.dto.response.PermissionResponse;
import com.atuandev.identityService.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
