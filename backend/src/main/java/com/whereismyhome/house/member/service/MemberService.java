package com.whereismyhome.house.member.service;

import java.util.List;

import com.whereismyhome.house.member.dto.MemberDto;
import com.whereismyhome.house.member.entity.Member;
import com.whereismyhome.house.member.request.SignUp;

public interface MemberService {

    public Member getMember(String userId) throws Exception;

    public void signUp(SignUp signUp) throws Exception;

    public List<Member> getMembers() throws Exception;

    public Member loginMember(String userId, String userPassword) throws Exception;
}
