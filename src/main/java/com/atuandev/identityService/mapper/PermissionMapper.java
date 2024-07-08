package com.atuandev.identityService.mapper;

import com.atuandev.identityService.dto.request.PermissionRequest;
import com.atuandev.identityService.dto.response.PermissionResponse;
import com.atuandev.identityService.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
