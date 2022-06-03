package com.its.boardMember.controller;

import com.its.boardMember.dto.MemberDTO;
import com.its.boardMember.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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
        System.out.println("memberId = " + memberId); //@ResponseBody 는 ajax문법을 쓰기 위한 맵핑 이고
        String checkResult = memberService.duplicateCheck(memberId);
        return checkResult;
    }

    @GetMapping("/login-form") //로그인 페이지
    public String loginForm(){
        return "memberPages/login";
    }

    @PostMapping("/login") //로그인 처리
    public String login(@ModelAttribute MemberDTO memberDTO, Model model, HttpSession session){
        System.out.println("컨트롤러 디티오"+memberDTO);
        MemberDTO loginMember = memberService.login(memberDTO);
        System.out.println("컨트롤러"+loginMember);
        if (loginMember != null){
            model.addAttribute("loginMember",loginMember);
            session.setAttribute("loginId",loginMember.getMemberId());
            session.setAttribute("id",loginMember.getId());
            return "memberPages/main"; // 훗날 "redirect:/board/paging"; 으로 이동 "redirect: Mapping 주소로 이동하는 명령어
        }else {
            return "memberPages/login";
        }
    }

    @GetMapping("findAll") //회원정보
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList",memberDTOList);
        return "memberPages/list";
    }
}
