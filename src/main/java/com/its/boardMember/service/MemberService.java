package com.its.boardMember.service;

import com.its.boardMember.dto.MemberDTO;
import com.its.boardMember.dto.PageDTO;
import com.its.boardMember.repository.MemberRepository;
import com.its.boardMember.util.MemberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public String insertMember(MemberDTO memberDTO) {
        String memberId = MemberUtil.getRandomVal();
        memberDTO.setMemberId(memberId); //여기까지 서비스에서 memberId를 담는 과정

        if(memberDTO.getProfileImg() != null && !memberDTO.getProfileImg().equals("")){
            FileService fileService =  new FileService();
            String[] profileImg = memberDTO.getProfileImg().split(":");
            String imgUrl = fileService.moveFileToModuleDir(profileImg[0], profileImg[1]);
            memberDTO.setProfileImg(imgUrl+"/"+profileImg[1]);
        }

        memberRepository.insertMember(memberDTO);
        return "SUCCESS";
    }


    public List<MemberDTO> findAll() {
        return memberRepository.findAll();
    }

    private static final int PAGE_LIMIT = 9; // 한 페이지에 보여줄 글 갯수 선언
    private static final int BLOCK_LIMIT = 11; // 보여줄 필요 페이지 수 선언


    public List<MemberDTO> pagingList(int page) { // 글목록을(페이징리스트) db로부터 가져오는 부분이다.
        int pagingStart = (page-1) * PAGE_LIMIT;
        Map<String,Integer> pagingParam = new HashMap<>();
        pagingParam.put("start",pagingStart);
        pagingParam.put("limit",PAGE_LIMIT);
        List<MemberDTO> pagingList = memberRepository.pagingList(pagingParam);
        return pagingList;
    }

    public PageDTO paging(int page) { // 전체 페이지수 및 페이지번호 처리부분 이다.
        int boardCount = memberRepository.boardCount();
        int maxPage = (int)(Math.ceil((double) boardCount / PAGE_LIMIT));
        int startPage = (((int)(Math.ceil((double)page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
        int endPage = startPage + BLOCK_LIMIT -1;
        if (endPage > maxPage)
            endPage = maxPage;

        PageDTO paging = new PageDTO();
        paging.setPage(page);
        paging.setStartPage(startPage);
        paging.setEndPage(endPage);
        paging.setMaxPage(maxPage);
        return paging;
    }

    public MemberDTO findById(Long id) {
        memberRepository.updateHits(id); // 조회수 증가
        return memberRepository.findById(id); // 상세정보 가져오기
    }

    public void delete(Long id) { //글삭제 처리
        memberRepository.delete(id);
    } //글 삭제(댓글삭제 포함)


    public void update(MemberDTO memberDTO) { //수정처리
    memberRepository.update(memberDTO);
    }  //글수정처리

    public List<MemberDTO> search(String searchType, String q) { //검색처리
        Map<String,String> searchParam = new HashMap<>();
        searchParam.put("type",searchType);
        searchParam.put("q",q);
        List<MemberDTO> searchList = memberRepository.search(searchParam);
        return searchList;
    }
}


