package com.atuandev.identityService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atuandev.identityService.dto.request.PermissionRequest;
import com.atuandev.identityService.dto.response.PermissionResponse;
import com.atuandev.identityService.entity.Permission;
import com.atuandev.identityService.mapper.PermissionMapper;
import com.atuandev.identityService.repository.PermissionRepository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
