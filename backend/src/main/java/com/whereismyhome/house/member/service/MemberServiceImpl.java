package com.whereismyhome.house.member.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.whereismyhome.house.exception.DuplicateUserIdException;
import com.whereismyhome.house.exception.InvalidSignIn;
import com.whereismyhome.house.member.entity.Member;
import com.whereismyhome.house.member.repository.MemberRepository;
import com.whereismyhome.house.member.request.SignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public void signUp(SignUp signUp) {
        // 중복 아이디 체크
        Optional<Member> foundId = memberRepository.findByUserId(signUp.getUserId());
        if (foundId.isPresent()) {
            throw new DuplicateUserIdException();
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);

        Member member = Member.builder()
                .userId(signUp.getUserId())
                .name(signUp.getName())
                .email(signUp.getEmail())
                .password(signUp.getPassword())
                .createdAt(formattedDate)
                .address(signUp.getAddress())
                .phoneNumber(signUp.getPhoneNumber())
                .build();

        memberRepository.save(member);
    }

    @Override
    public Member getMember(String userId) throws Exception {
        return memberRepository.findById(Long.parseLong(userId))
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public List<Member> getMembers() throws Exception {
        return (List<Member>) memberRepository.findAll();
    }

    @Override
    public Member loginMember(String userId, String userPassword) throws Exception {
        Member member = memberRepository.findByUserIdAndPassword(userId, userPassword)
                .orElseThrow(InvalidSignIn::new);
        return member;
    }

}
