package com.whereismyhome.house.member.controller;

import com.whereismyhome.house.member.dto.MemberDto;
import com.whereismyhome.house.member.entity.Member;
import com.whereismyhome.house.member.request.SignUp;
import com.whereismyhome.house.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name="Member API", description = "유저 정보 CRUD를 위한 API")
@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

	@Operation(summary = "HouseAPI 연결 test", description = "컨트롤러 연결 test")
    @GetMapping("/")
    public void login(){
		log.info("컨트롤러 연결 test");
    }

	@Operation(summary = "login을 위한 API", description = "입력받은 사용자 정보를 통해 login합니다.")
	@Parameter(name = "memberDto", description = "사용자 정보 login + password")
	@PostMapping("/login")
	public Member login(@RequestBody MemberDto memberDto) throws Exception {
		String userId = memberDto.getUserId();
		String userPassword = memberDto.getPassword();
		log.info("userId={}, userPassword={}", userId, userPassword);
		return memberService.loginMember(userId, userPassword);
	}

	@Operation(summary = "회원가입", description = "입력받은 사용자 정보를 바탕으로 회원가입을 진행한다.")
	@Parameter(name = "memberDto", description = "사용자 정보")
	@PostMapping("/register")
	public void registerMember(@RequestBody MemberDto memberDto) throws Exception {
		SignUp signUp = SignUp.builder()
				.userId(memberDto.getUserId())
				.name(memberDto.getName())
				.password(memberDto.getPassword())
				.email(memberDto.getEmail())
				.address(memberDto.getAddress())
				.createdAt(memberDto.getCreatedAt())
				.phoneNumber(memberDto.getPhoneNumber())
				.build();
		memberService.signUp(signUp);
	}

	@Operation(summary = "사용자 상세 정보 조회", description = "사용자의 상세 유저 정보를 조회합니다.")
	@Parameter(name = "id", description = "사용자 정보의 Key 값")
	@GetMapping("/detail/{id}")
	public Member memberDetail(@PathVariable String id) throws Exception {
		return memberService.getMember(id);
	}

}
