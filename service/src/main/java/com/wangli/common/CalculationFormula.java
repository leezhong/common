package com.wangli.common;

import java.math.BigDecimal;

/**
 * @Author wjx
 * @Date 2017/10/9.
 * @description:债券匹配价值公式
 */
public class CalculationFormula {

    /**
     * @param monthlyInterestRate 月利率
     *                            月利率转年利率（1+b%）12-1=Y%
     * @return 月利率
     */
    public static double monthInterest2Year(double monthlyInterestRate) {
        monthlyInterestRate += 1;
        return Arith.sub(StrictMath.pow(monthlyInterestRate, 12), 1);
    }

    public static double pow(double powed, int pow) {

        BigDecimal result = BigDecimal.valueOf(powed);
        for (int i = 0; i < pow; i++) {
            result = result.multiply(result);
            System.out.println("powed:" + result.doubleValue());
        }
        return result.doubleValue();
    }

    /**
     * 年利率转月利率
     * B%=(y%+1) -12 -1
     *
     * @param yearInterestRate 年利率
     * @return 年利率
     */
    public static double yearInterest2Month(double yearInterestRate) {
        double result = yearInterestRate + 1;
        for (int i = 0; i < 12; i++) {
            result = StrictMath.sqrt(result);
            System.out.println("sqrt:" + result);
        }
        System.out.println("before sub:" + result);
        return Arith.sub(result, 1);
    }

    /**
     * 指根据定价公式计算出的债权当前的公允价值
     * 到期一次性还清
     *
     * @param dduc            上期还款对应的应还款日期
     * @param dnow            成交日期
     *                        应计利息天数 = ddnow - dduc
     * @param monthInterest   月利率
     * @param remainPrincipal 剩余未还本金
     * @param x               产品折让参数 value: 1 本息保障类产品  0.本金保障类产品
     * @param n               当前期数
     * @return 债权公允价值
     */
    public static double allReturnOnce(double remainPrincipal, int dnow, int dduc, double monthInterest, int x, int n, double yearInterest) {
        double result;
        int day = dnow - dduc;//应计利息天数
        day = getDay(day);
        int pow = 12 * (1 - n);
        if (pow >= 0) {
            double powResult = Math.pow(yearInterest+1,pow);
            result = remainPrincipal * powResult + day / 30.0d * monthInterest * x;
        } else {
            double sqrtResult = sqrt(1 + yearInterest, Math.abs(pow));
            result = remainPrincipal * sqrtResult + day / 30.0d * monthInterest * x;
        }

        return result;
    }

    public static double sqrt(double sqrted, int sqrt) {
        double result = sqrted;
        for (int i = 0; i < sqrt; i++) {
            result = StrictMath.sqrt(result);
//            System.out.println("sqrt:"+result);
        }
        return result;
    }

    /**
     * 指根据定价公式计算出的债权当前的公允价值
     * 1.当期还款处于未还款状态
     *
     * @param dduc            上期还款对应的应还款日期
     * @param dnow            成交日期
     *                        应计利息天数 = ddnow - dduc
     * @param monthInterest   月利率
     * @param remainPrincipal 剩余未还本金
     * @param x               产品折让参数 value: 1 本息保障类产品  0.本金保障类产品
     * @return 债权公允价值
     */
    public static double fairValueUnreturn(double remainPrincipal, int dnow, int dduc, double monthInterest, int x) {
        int day = dnow - dduc;//应计利息天数
        day = getDay(day);
        double result = remainPrincipal + day / 30 * remainPrincipal * monthInterest * x;
        return result;
    }

    /**
     * 指根据定价公式计算出的债权当前的公允价值
     * 2.当期还款处于已还款状态，但下一期处于未还款状态
     *
     * @param dduc            上期还款对应的应还款日期
     * @param dnow            成交日期
     *                        应计利息天数 = ddnow - dduc
     * @param monthInterest   月利率
     * @param remainPrincipal 剩余未还本金
     * @param x               产品折让参数 value: 1 本息保障类产品  0.本金保障类产品
     * @return 债权公允价值
     */
    public static double fairValueReturned(double remainPrincipal, int dnow, int dduc, double monthInterest, int x) {
        int day = dnow - dduc;//利息补偿天数
        day = getDay(day);
        double result = remainPrincipal - (30 - day) / 30 * remainPrincipal * monthInterest * x;
        return result;
    }

    private static int getDay(int day) {
        if (day > 30) //应计利息天数超过30天按30天计算
            day = 30;
        return day;
    }

    /**
     * 指根据定价公式计算出的债权当前的公允价值
     * 3.当期还款处于已还款状态，且下面的N期处于已还款状态，但还未完全还清
     *
     * @param dduc            上期还款对应的应还款日期
     * @param dnow            成交日期
     *                        应计利息天数 = ddnow - dduc
     * @param monthInterest   月利率
     * @param yearInterest    年利率
     * @param remainPrincipal 剩余未还本金
     * @param t               最后还款所在期数与成交日期所在期数之差
     * @return 债权公允价值
     */
    public static double fairValueReturnedWithoutAll(double remainPrincipal, int dnow, int dduc, double monthInterest, double yearInterest, int t) {
        int day = dnow - dduc;//利息补偿天数,即 D =30-D
        day = getDay(day);
        double yearInterestT = pow(1 + yearInterest, 12 * t);
        double result = remainPrincipal * yearInterestT - (30 - day) / 30 * remainPrincipal * yearInterestT * monthInterest;
        return result;
    }

}
