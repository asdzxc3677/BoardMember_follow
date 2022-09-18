package com.its.boardMember.service;

import com.its.boardMember.dto.MemberDTO;
import com.its.boardMember.dto.PageDTO;
import com.its.boardMember.repository.MemberRepository;
import com.its.boardMember.util.MemberConstants;
import com.its.boardMember.util.MemberUtil;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public String insertMember(HttpServletRequest request, MemberDTO memberDTO) {
        String memberId = MemberUtil.getRandomVal();
        memberDTO.setMemberId(memberId); //여기까지 서비스에서 memberId를 담는 과정
        String realPath = request.getSession().getServletContext().getRealPath(MemberConstants.SAVE_FOLDER_DIR);
        boolean result = false;
        try {
            MultipartRequest multipartRequest = null;
            multipartRequest = new MultipartRequest(request, realPath, MemberConstants.FILE_SIZE, "UTF-8", new DefaultFileRenamePolicy());
            memberDTO.setProfileImg(multipartRequest.getFilesystemName((String) multipartRequest.getFileNames().nextElement()));
            memberDTO.setKorName(multipartRequest.getParameter("korName"));
            memberDTO.setEngName(multipartRequest.getParameter("engName"));
            memberDTO.setChinaName(multipartRequest.getParameter("chinaName"));
            memberDTO.setRegNo1(multipartRequest.getParameter("regNo1"));
            memberDTO.setRegNo2(multipartRequest.getParameter("regNo2"));
            memberDTO.setBirthDate(multipartRequest.getParameter("birthDate"));
            memberDTO.setPhoneNum(multipartRequest.getParameter("phoneNum"));
            memberDTO.setTechLevel(multipartRequest.getParameter("techLevel"));
            memberDTO.setYear(Integer.parseInt(multipartRequest.getParameter("year")));
            memberRepository.insertMember(memberDTO);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "SUCCESS";
    }

    public int selectMemberListCount() {
        return memberRepository.selectMemberListCount();
    }

    public List<MemberDTO> selectMemberList(PageDTO pageDTO) {
        return memberRepository.selectMemberList(pageDTO);
    }

}


