package com.coockcoock.shop.member.repository;

import com.coockcoock.shop.member.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandGradeRepository extends JpaRepository<Grade, Long> {
}
