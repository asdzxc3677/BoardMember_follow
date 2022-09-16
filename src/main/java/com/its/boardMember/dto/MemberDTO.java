package com.its.boardMember.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String memberId; //사원번호
    private String korName; // 한글이름
    private String engName; // 영문이름
    private String chinaName; // 한문이름
    private String techLevel; // 기술등급
    private String regNo1; //주민등록 앞자리
    private String regNo2; //주민등록 뒷자리
    private String birthDate; //생년월일
    private int year; //연차
    private String phoneNum; //연락처
    private String profileImg;
}
