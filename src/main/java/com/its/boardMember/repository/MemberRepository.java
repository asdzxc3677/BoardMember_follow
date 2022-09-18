package com.its.boardMember.repository;

import com.its.boardMember.dto.MemberDTO;
import com.its.boardMember.dto.PageDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {

    @Autowired
    private SqlSessionTemplate sql;

    public void insertMember(MemberDTO memberDTO) {
        sql.insert("Member.insertMember", memberDTO);
    } //사원등록

    public int selectMemberListCount() {
        return sql.selectOne("Member.selectMemberListCount");
    }

    public List<MemberDTO> selectMemberList(PageDTO pageDTO) {
        return sql.selectList("Member.selectMemberList", pageDTO);
    }
}
