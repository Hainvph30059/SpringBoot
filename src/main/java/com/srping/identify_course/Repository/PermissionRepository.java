package com.srping.identify_course.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srping.identify_course.Entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
