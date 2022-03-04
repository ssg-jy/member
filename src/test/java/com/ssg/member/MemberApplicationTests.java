package com.ssg.member;

import com.ssg.member.domain.MemberShpLoc;
import com.ssg.member.repository.MemberShpLocRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MemberApplicationTests {

	@Autowired
	private MemberShpLocRepository memberShpLocRepository;

	@Test
	void 기본_배송지_Read() {
		MemberShpLoc msl = memberShpLocRepository.findByMbrIdAndIsDefault(1L, 1);

		// 조회한 배송지가 해당 유저의 배송지인지 확인
		assertEquals(msl.getMbrId(), 1L);

		// 조회한 배송지가 해당 유저의 기본 배송지인지 확인
		assertEquals(msl.getIsDefault(), 1L);
	}

	@Test
	void 해당_유저_전체_배송지_Read() {
		List<MemberShpLoc> msl = memberShpLocRepository.findAllByMbrId(1L);

		// 조회된 배송지 List의 길이 확인
		assertEquals(msl.size(), 2);
	}

	@Test
	void 배송지_존재_여부_check_Read() {
		Optional<MemberShpLoc> msl = memberShpLocRepository.findById(1L);

		msl.ifPresent(selectLocation -> {
			assertEquals(selectLocation.getId(), 1L);
		});
	}

	@Test
	void 배송지_정보_Create() {
		MemberShpLoc ex = MemberShpLoc.builder()
				.mbrId(1L)
				.isDefault(0)
				.address("test 주소")
				.build();

		memberShpLocRepository.save(ex);

		List<MemberShpLoc> msl = memberShpLocRepository.findAllByMbrId(1L);

		// 기존 배송지 List의 길이 = 2 -> 배송지 추가 후 배송지 List의 길이 = 3
		assertEquals(msl.size(), 3);
	}

	@Test
	void 해당_유저_나머지주소_isDefault_0으로_만들기() {
		memberShpLocRepository.updateIsDefault(1L);

		List<MemberShpLoc> msl = memberShpLocRepository.findAllByMbrId(1L);

		int count = 0;

		for (MemberShpLoc loc : msl) {
			if(loc.getIsDefault() == 1) {
				count++;
			}
		}

		// 기본 배송지가 없는 상태 확인
		assertEquals(count, 0);
	}

	@Test
	void 배송지_정보_Update() {
		Optional<MemberShpLoc> location = memberShpLocRepository.findById(2L);

		MemberShpLoc ex = MemberShpLoc.builder()
				.mbrId(1L)
				.isDefault(1)
				.address("2번 배송지 update")
				.build();

		location.ifPresent(selectLocation -> {
			if (ex.getIsDefault() == 1) {
				memberShpLocRepository.updateIsDefault(ex.getMbrId());
			}
			selectLocation.setIsDefault(ex.getIsDefault());
			selectLocation.setAddress(ex.getAddress());

			memberShpLocRepository.save(selectLocation);
		});

		List<MemberShpLoc> msl_list = memberShpLocRepository.findAllByMbrId(1L);

		int count = 0;

		for (MemberShpLoc loc: msl_list) {
			if(loc.getIsDefault() == 1) count++;
		}

		// 기본 배송지 정상 변경 여부
		assertEquals(count, 1);

		MemberShpLoc msl = memberShpLocRepository.findByMbrIdAndIsDefault(ex.getMbrId(), ex.getIsDefault());

		// 2번 배송지 기본 배송지 여부 확인
		assertEquals(msl.getIsDefault(), 1);

		// 2번 배송지 변경 내용 확인
		assertEquals(msl.getAddress(), "2번 배송지 update");
	}

	@Test
	void 기본_배송지_추가() {
		MemberShpLoc ex = MemberShpLoc.builder()
				.mbrId(1L)
				.isDefault(1)
				.address("test 주소")
				.build();

		if (ex.getIsDefault() == 1) memberShpLocRepository.updateIsDefault(ex.getMbrId());

		memberShpLocRepository.save(ex);

		MemberShpLoc msl = memberShpLocRepository.findByMbrIdAndIsDefault(ex.getMbrId(), ex.getIsDefault());

		// 배송지 정상 추가 여부 확인
		assertEquals(msl.getAddress(), "test 주소");

		List<MemberShpLoc> msl_list = memberShpLocRepository.findAllByMbrId(ex.getMbrId());

		int count = 0;

		String location = "";

		for (MemberShpLoc loc: msl_list) {
			if(loc.getIsDefault() == 1) {
				count++;
				location = loc.getAddress();
			}
		}

		// 기존 기본 배송지 -> 일반 배송지 전환 여부 확인
		assertEquals(count, 1);

		// 변경된 기본 배송지 = 추가된 배송지 여부 확인
		assertEquals(location, "test 주소");
	}
}
