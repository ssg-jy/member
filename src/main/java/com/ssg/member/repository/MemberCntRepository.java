package com.ssg.member.repository;

import com.ssg.member.domain.MemberCnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCntRepository extends JpaRepository<MemberCnt, Long> {
}
