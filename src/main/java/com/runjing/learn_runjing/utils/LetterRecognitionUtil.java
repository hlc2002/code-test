package com.runjing.learn_runjing.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author : forestSpringH
 * @description: 字母识别工具
 * @date : Created in 2023/9/26
 * @modified By:
 * @project: erp
 */
public class LetterRecognitionUtil {


    /**
     * 截取字符串中的连续大写字母序列并返回
     * A~Z的ascll码的值范围是65到90，a~z的ascll码的值范围是97到122
     *
     * @param includeLetterString the include letter string
     * @return the string
     */
    public static String interceptUpperCaseLetterFromString(String includeLetterString) {
        if (StringUtils.isEmpty(includeLetterString)) {
            throw new IllegalArgumentException("连续大写字母截取方法入参" + includeLetterString + "异常");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < includeLetterString.length(); i++) {
            if (!judgeLetter(includeLetterString.charAt(i))) {
                return result.toString();
            }
            result.append(includeLetterString.charAt(i));
            if (i == (includeLetterString.length() - 1)) {
                return result.toString();
            }
        }
        return result.toString();
    }

    /**
     * 判断大写字母
     *
     * @param letter 字母字符
     * @return
     */
    public static Boolean judgeLetter(char letter) {
        return (int) letter >= 65 && (int) letter <= 90;
    }

    /**
     * 截取字符串中的连续字母并返回（可选择是否需要大写）
     *
     * @param includeLetterString the include letter string
     * @param requireUpperCase    the required upper case
     * @return the string
     */
    public static String interceptLetterFromString(String includeLetterString, Boolean requireUpperCase) {
        if (StringUtils.isEmpty(includeLetterString)) {
            throw new IllegalArgumentException("连续字母截取方法入参" + includeLetterString + "异常");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < includeLetterString.length(); i++) {
            char currentChar = includeLetterString.charAt(i);
            if (!Character.isLetter(currentChar)) {
                break;
            }
            result.append(currentChar);
        }
        return requireUpperCase ? result.toString().toUpperCase() : result.toString();
    }


    public static void main(String[] args) {
        System.out.println(interceptUpperCaseLetterFromString("JcPU137788989202"));
        System.out.println(interceptUpperCaseLetterFromString("JCPU1377889202"));
        System.out.println(interceptUpperCaseLetterFromString("JCPU13U7889202"));
        System.out.println(interceptUpperCaseLetterFromString("JCPU13KK7889202"));
        System.out.println(interceptUpperCaseLetterFromString("JCPUi377889202"));
        System.out.println(interceptUpperCaseLetterFromString("ppJCPU1377889202"));
        System.out.println(interceptUpperCaseLetterFromString("PpJCPU1377889202"));
        System.out.println(interceptLetterFromString("ppjcpu127899999", true));
    }
}
