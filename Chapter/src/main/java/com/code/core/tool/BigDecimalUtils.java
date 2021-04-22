package com.code.core.tool;


import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class BigDecimalUtils {
    public static final MathContext _SCALE = new MathContext(100, RoundingMode.HALF_UP);

    /**
     * 将传入字符串转化成指定精度的BigDecimal
     * @param src   传入字符串
     * @param scale 指定精度
     * @return
     */
    public static BigDecimal setBigDecimalWithScale(String src, int scale){
        if(StringUtils.isBlank(src)){
            return BigDecimal.ZERO.setScale(scale);
        }

        BigDecimal ret =  new BigDecimal(src);
        return ret.setScale(scale,BigDecimal.ROUND_DOWN);
    }

    /**
     * 将传入BigDecimal作四舍五入，精确两位小数
     * @param b
     * @return
     */
    public static BigDecimal toMoneyScale(BigDecimal b){
        return b.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 舍去末尾的0
     * @param b
     * @return
     */
    public static String toStringFlexibleScale(BigDecimal b){
        if(b == null){
            return null;
        }
        String bStr = b.toString();
        if(bStr.contains(".")){
            String[] a = bStr.split("\\.");
            String decimalPart = a[1];
            String integerPart = a[0];
            decimalPart = decimalPart.replaceAll("0+$","");

            return new BigDecimal(integerPart + "." + decimalPart).toString();
        }
        return b.toString();
    }

    /**
     * 将double数作四舍五入，精确两位小数
     * @param f
     * @return
     */
    public static double formatDouble(double f)
    {
        //double f = 10.66666667;
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    /**
     * 获取两个BigDecimal的较大者
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal max(BigDecimal a, BigDecimal b){
        if(a.compareTo(b)>0){
            return a;
        }
        return b;
    }

    /**
     * 获取两个BigDecimal的较小者
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal min(BigDecimal a,BigDecimal b){
        if(a.compareTo(b)>0){
            return b;}
        return a;
    }

    /**
     * 兼容null的BigDecimal减法
     * @param minuend      被减数
     * @param subtrahend   减数
     * @return
     */
    public static BigDecimal subtractIncludingNull(BigDecimal minuend, BigDecimal subtrahend){
        if(minuend == null){ minuend = BigDecimal.ZERO.setScale(2);}
        return minuend.subtract(subtrahend);
    }
}

