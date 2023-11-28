package com.whereismyhome.house.member.repository;

import com.whereismyhome.house.member.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {


    Optional<Member> findByUserId(String userId);

    Optional<Member> findByUserIdAndPassword(String email, String password);

    Optional<Member> findByEmail(String email);
}
