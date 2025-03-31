package com.srping.identify_course.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.srping.identify_course.Repository.PermissionRepository;
import com.srping.identify_course.Repository.RoleRepository;
import com.srping.identify_course.dto.request.RoleRequets;
import com.srping.identify_course.dto.response.RoleResponse;
import com.srping.identify_course.mapper.RoleMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequets requets) {
        var role = roleMapper.toRole(requets);
        var permission = permissionRepository.findAllById(requets.getPermissions());
        role.setPermissions(new HashSet<>(permission));

        roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
