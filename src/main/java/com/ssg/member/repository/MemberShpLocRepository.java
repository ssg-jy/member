package com.ssg.member.repository;

import com.ssg.member.domain.MemberShpLoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MemberShpLocRepository extends JpaRepository<MemberShpLoc, Long> {

    // 배송지 정보 Create -> save() (v)
//    @Query(value = "insert into MemberShpLoc m (m.mbrId, m.isDefault, m.address) values (:mbrId, :isDefault, :address)", nativeQuery = true)
//    MemberShpLoc createMemberShpLoc(Long mbrId, int isDefault, String address);

    // 기본 배송지 Read (v)
    MemberShpLoc findByMbrIdAndIsDefault(Long mbrId, int isDefault);

//    @Query(value = "select msl.mbr_id, m.mbr_login_id, msl.address from member m left join member_shp_loc msl on m.id = msl.mbr_id where msl.mbr_id = :mbrId and msl.is_default = 1", nativeQuery = true)
//    MemberShpLoc selectDefaultLoc(@Param("mbrId") Long mbrId);

    // 해당 유저 전체 배송지 Read (v)
    List<MemberShpLoc> findAllByMbrId(Long mbrId);

    // 배송지 존재 여부 check Read (v)
    Optional<MemberShpLoc> findById(Long id);

    // 배송지 정보 Update -> save() (v)

    // 배송지 정보 Delete -> delete() (v)

    // 해당 유저 나머지 주소 isDefault 0으로 만들기 (v)
    @Transactional
    @Modifying
    @Query(value = "update member_shp_loc msl set msl.is_default = 0 where msl.mbr_id = :mbrId", nativeQuery = true)
    void updateIsDefault(@Param("mbrId") Long mbrId);
}
