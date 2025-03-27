package com.srping.identify_course.service;

import com.srping.identify_course.Entity.Permission;
import com.srping.identify_course.Repository.PermissionRepository;
import com.srping.identify_course.dto.request.PermissionRequets;
import com.srping.identify_course.dto.response.PermissionResponse;
import com.srping.identify_course.mapper.PermissionMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequets requets){
        Permission permission = permissionMapper.toPermission(requets);
        permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> findAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String name){
        permissionRepository.deleteById(name);
    }
}
