package com.srping.identify_course.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srping.identify_course.Entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
