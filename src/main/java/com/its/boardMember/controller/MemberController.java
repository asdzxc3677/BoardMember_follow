package com.its.boardMember.controller;

import com.its.boardMember.dto.MemberDTO;
import com.its.boardMember.dto.PageDTO;
import com.its.boardMember.service.MemberService;
import com.its.boardMember.util.MemberConstants;
import com.its.boardMember.util.MemberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member") //컨트룰러에 있는 모든 메서드들을 찿아주는 역할
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/memberReg") //회원가입 페이지 출력
    public String memberReg(HttpServletRequest request, HttpServletResponse response) {
        return "boardPages/memberReg";
    }

    @PostMapping("/insertMember")  //회원가입 처리
    public String insertMember(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberDTO memberDTO){
        memberService.insertMember(request, memberDTO);
        return "redirect:/member/list";
    }

    @RequestMapping(value="list", method = { RequestMethod.GET, RequestMethod.POST})
    public String list(HttpServletRequest request, HttpServletResponse response) {
        PageDTO pageDTO = new PageDTO();
        List<MemberDTO> memberList = new ArrayList<MemberDTO>();
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int pagingStart = (page-1) * MemberConstants.PAGE_LIMIT;
        int listcount = memberService.selectMemberListCount();
        pageDTO.setPage(pagingStart);
        pageDTO.setLimit(MemberConstants.PAGE_LIMIT);
        memberList = memberService.selectMemberList(pageDTO);
        int maxpage = (int)(Math.ceil((double) listcount / MemberConstants.PAGE_LIMIT));
        int startpage = (((int)(Math.ceil((double)page / MemberConstants.BLOCK_LIMIT))) - 1) * MemberConstants.BLOCK_LIMIT + 1;
        int endpage = startpage + MemberConstants.BLOCK_LIMIT -1;
        if (endpage > maxpage) {
            endpage = maxpage;
        }
        request.setAttribute("page", page);
        request.setAttribute("maxpage", maxpage);
        request.setAttribute("startpage", startpage);
        request.setAttribute("endpage", endpage);
        request.setAttribute("listcount", listcount);
        request.setAttribute("memberList", memberList);
        return "boardPages/list";
    }
}
