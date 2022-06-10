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
@RequestMapping("/member") //컨트룰러에 있는 모든 메서드들을 찿아주는 역할
public class MemberController {

    @Autowired //의존성을 줄여주는 역할하는 객체
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
            return "redirect:/board/paging"; // 훗날 "redirect:/board/paging"; 으로 이동 "redirect: Mapping 주소로 이동하는 명령어
        }else {
            return "memberPages/login";
        }
    }

    @GetMapping("/logout") //로그아웃 처리
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }

    @GetMapping("findAll") //관리자용 모든 회원 개인정보
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList",memberDTOList);
        return "memberPages/list";
    }



    @GetMapping("/detail-ajax") //ajax로 처리한 상세조회
    public @ResponseBody MemberDTO findByIdAjax(@RequestParam("id") Long id){
        System.out.println("id = " + id);
        MemberDTO memberDTO = memberService.findById(id);
        return memberDTO;
    }

    @GetMapping("/detail") // 개인신상정보
    public String detail(HttpSession session,Model model){
        Long id = (Long) session.getAttribute("id");
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member",memberDTO);
        return "memberPages/detail";
    }

    @GetMapping("/delete") //회원삭제 처리
    public String delete(@RequestParam("id")Long id){
        System.out.println("id = " + id);
        boolean deleteResult = memberService.delete(id);
        if (deleteResult){
            return "redirect:/member/findAll";
        }else {
            return "delete-fail";
        }
    }

    @GetMapping("/update-form") //회원수정을 위해 회원정보 받는 문법
    public String updateForm(HttpSession session, Model model){
        Long updateId = (Long) session.getAttribute("id");
        System.out.println("updateId=" + updateId);
        MemberDTO memberDTO = memberService.findById(updateId);
        model.addAttribute("updateMember",memberDTO);
        return "memberPages/update";
    }

    @PostMapping("/update") // 회원수정 처리
    public String update(@ModelAttribute MemberDTO memberDTO){
        boolean updateResult = memberService.update(memberDTO);
        if (updateResult){
            return "redirect:/member/detail"; // 개인회원 수정처리 했을때 detail 로 보낸다.
        }else {
            return "memberPages/udate-fail";
        }
    }



}
