package com.its.boardMember.repository;

import com.its.boardMember.dto.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @Autowired
    private SqlSessionTemplate sql;

    public int save(MemberDTO memberDTO) {
        return sql.insert("Member.save",memberDTO);
    } //회원가입

    public String duplicateCheck(String memberId) { // 아이디 중복체크
        return sql.selectOne("Member.duplicate",memberId);
    } //아이디 중복체크

    public MemberDTO login(MemberDTO memberDTO) {
        return sql.selectOne("Member.login",memberDTO);
    } //로그인

    public List<MemberDTO> findAll() {
        return sql.selectList("Member.findAll");
    } //관리자용 회원정보

    public MemberDTO findById(Long id) {
        return sql.selectOne("Member.findById",id);
    } // ajax로 처리된 상세조회

    public int delete(Long id) {
        return sql.delete("Member.delete",id);
    }

    public int update(MemberDTO memberDTO) {
        return sql.update("Member.update",memberDTO);
    }
}
