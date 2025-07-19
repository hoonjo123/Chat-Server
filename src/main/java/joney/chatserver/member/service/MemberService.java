package joney.chatserver.member.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import joney.chatserver.member.domain.Member;
import joney.chatserver.member.dto.MemberListResDto;
import joney.chatserver.member.dto.MemberLoginReqDto;
import joney.chatserver.member.dto.MemberSaveReqDto;
import joney.chatserver.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member create(MemberSaveReqDto memberSaveReqDto) {
        if (memberRepository.findByEmail(memberSaveReqDto.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        Member newMember = Member.builder()
                .name(memberSaveReqDto.getName())
                .email(memberSaveReqDto.getEmail())
                .password(passwordEncoder.encode(memberSaveReqDto.getPassword()))
                .build();
        Member member = memberRepository.save(newMember);

        return member;
    }

    public Member login(MemberLoginReqDto memberLoginReqDto) {
        Member member = memberRepository.findByEmail(memberLoginReqDto.getEmail()).orElseThrow(()->
                new EntityNotFoundException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(memberLoginReqDto.getPassword(), member.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }

    public List<MemberListResDto> findAll(){
        List<Member> members = memberRepository.findAll();
        List<MemberListResDto> memberListResDtos = new ArrayList<>();
        for (Member m : members){
            MemberListResDto memberListResDto = new MemberListResDto();
            memberListResDto.setId(m.getId());
            memberListResDto.setEmail(m.getEmail());
            memberListResDto.setName(m.getName());
            memberListResDtos.add(memberListResDto);
        }
        return memberListResDtos;
    }
}
