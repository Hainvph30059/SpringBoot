package com.srping.identify_course.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srping.identify_course.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
