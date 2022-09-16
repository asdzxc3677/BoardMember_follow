package com.its.boardMember.controller;

import com.its.boardMember.dto.MemberDTO;
import com.its.boardMember.dto.PageDTO;
import com.its.boardMember.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value="{sitemesh}/member", method = {RequestMethod.GET, RequestMethod.POST})
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value="memberReg", method = { RequestMethod.GET, RequestMethod.POST})
    public String memberReg(HttpServletRequest request, HttpServletResponse response) {
        return "boardPages/memberReg";
    }

    @RequestMapping(value="insertMember", method = { RequestMethod.GET, RequestMethod.POST})
    public void insertMember(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberDTO memberDTO) {
        try {
            String result = "";
            result = memberService.insertMember(memberDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value="findAll", method = { RequestMethod.GET, RequestMethod.POST})
    public String findAll(Model model) {
//        List<BoardDTO> boardDTOList = boardService.findAll();
//        model.addAttribute("boardList", boardDTOList);
        return "boardPages/list";
    }


}
