package com.example.demo.infrastructure.Utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//生成班课号
//生成当天年月日时秒加两位随机数
public class CnoFactory {

    private static final int[] r = new int[]{7, 9, 6, 2, 8, 1, 3, 0, 5, 4};
    /**
     * 用户id和随机数总长度
     */
    private static final int maxLength = 4;

    private static String toCode(Long id) {
        String idStr = id.toString();
        StringBuilder idsbs = new StringBuilder();
        for (int i = idStr.length() - 1; i >= 0; i--) {
            idsbs.append(r[idStr.charAt(i) - '0']);
        }
        return idsbs.append(getRandom(maxLength - idStr.length())).toString();
    }

    /**
     *     * 生成时间戳
     *     
     */
    private static String getDateTime() {
        //HHmmssSSS
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        return sdf.format(new Date());
    }

    /**
     *      * 生成固定长度随机码
     *      * @param n    长度
     *      
     */
    private static long getRandom(long n) {
        long min = 1, max = 9;
        for (int i = 1; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        return (((long) (new Random().nextDouble() * (max - min)))) + min;

    }

    /**
     *      * 生成不带类别标头的编码
     *      * @param userId
     *      
     */
    private static synchronized String getCode(Long userId) {
        userId = userId == null ? 10000 : userId;
        return getDateTime() + toCode(userId);
    }
    public static String Code(int userId){
        return getCode(Integer.toUnsignedLong(userId));
    }

}
