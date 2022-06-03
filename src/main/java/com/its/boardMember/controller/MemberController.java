package com.its.boardMember.controller;

import com.its.boardMember.dto.MemberDTO;
import com.its.boardMember.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/save-form") //회원가입 페이지 출력
    public String saveForm(){
        return "memberPages/save";
    }

    @PostMapping("/save")  //회원가입 처리
    public String save(@ModelAttribute MemberDTO memberDTO){
        boolean saveResult = memberService.save(memberDTO);
        if(saveResult){
            System.out.println("저장성공");
            return "/memberPages/login";
        }else {
            System.out.println("저장실패");
            return "memberPages/save-fail";
        }
    }

    @PostMapping("/duplicate-check") //아이디 중복체크
    public @ResponseBody String duplicateCheck(@RequestParam("memberId") String memberId){
        System.out.println("memberId = " + memberId);
        String checkResult = memberService.duplicateCheck(memberId);
        return checkResult;
    }
}
