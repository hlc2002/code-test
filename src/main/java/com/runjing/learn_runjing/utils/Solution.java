package com.runjing.learn_runjing.utils;

/**
 * @author : huanglinchun
 * @description:
 * @date : Created in 2023/10/27
 * @modified By: huanglinchun
 * @project: learn_runjing
 */
class Solution {

    private static int CIRCLE = 0;
    private static int SQUARE = 0;
    private static int FLOWER = 0;

    public boolean isValid(String s) {
        if(!effectivr(s)){
            return false;
        }
        char[] chars = s.toCharArray();
        for(char i: chars){
            if(!judgeType(i)){
                return false;
            }
        }

        return CIRCLE == 0 && SQUARE == 0 && FLOWER == 0;
    }

    public boolean effectivr(String judgeStr){
        return judgeStr.length() % 2 == 0;
    }

    public boolean judgeType(char c){
        if(c == '('){
            CIRCLE ++;
        }
        if(c == ')'){
            if(CIRCLE <= 0){
                return false;
            }
            CIRCLE --;
        }
        if(c == '['){
            SQUARE ++;
        }
        if(c == ']'){
            if(SQUARE <= 0){
                return false;
            }
            SQUARE --;
        }
        if(c == '{'){
            FLOWER ++;
        }
        if(c == '}'){
            if(FLOWER <= 0){
                return false;
            }
            FLOWER --;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "([)]";
        Solution solution = new Solution();
        System.out.println(solution.isValid(s));
    }
}
