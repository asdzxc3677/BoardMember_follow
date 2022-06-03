package com.its.boardMember.service;

import com.its.boardMember.dto.MemberDTO;
import com.its.boardMember.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public boolean save(MemberDTO memberDTO) {
        int saveResult = memberRepository.save(memberDTO);
        if(saveResult > 0){
            return true;
        }else {
            return false;
        }
    }

    public String duplicateCheck(String memberId) {
        String checkResult = memberRepository.duplicateCheck(memberId);
        if(checkResult == null){
            return "ok";
        }else {
            return "no";
        }
    }
}
