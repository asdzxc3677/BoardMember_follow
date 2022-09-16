package com.its.boardMember.util;

import java.util.Random;

public class MemberUtil {

    public static String getRandomVal() {
        Random random = new Random();
        long temp = (long) (random.nextDouble() * 1000000000) + 999999999L;
        return String.valueOf(temp);
    }
}
