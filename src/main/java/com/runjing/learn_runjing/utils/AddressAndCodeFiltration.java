package com.runjing.learn_runjing.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author forestSpringH
 * @date 2023/7/12
 * @project learn_runjing
 */
@Slf4j
public class AddressAndCodeFiltration {
    private final static String SHI = "市";
    private final static String XIAN = "县";
    private final static String QU = "区";
    private final static String ZHOU = "州";
    private final static String JSJ = "京东酒世界";

    public static String getResultString(String workHouseNameAndCode) {
        if (StringUtils.isBlank(workHouseNameAndCode)) {
            log.error("入参workHouseNameAndCode:{}", workHouseNameAndCode);
            throw new RuntimeException("入参workHouseNameAndCode不符合处理格式！");
        } else {
            String trimHandle = workHouseNameAndCode.replace(" ","");
            if ((trimHandle.contains(SHI) && (trimHandle.contains(XIAN) || trimHandle.contains(QU))) || (trimHandle.contains(ZHOU))) {
                int i;
                if (trimHandle.contains(SHI)) {
                    i = trimHandle.indexOf(SHI) + 1;
                } else {
                    i = trimHandle.indexOf(ZHOU) + 1;
                }
                return trimHandle.substring(i);
            }
            return trimHandle.replace(JSJ, "");
        }
    }

    public static void main(String[] args) {
        System.out.println(getResultString("北京市朝阳区BJS000001京东酒世界"));
        System.out.println(getResultString("北京朝阳区BJS000001京东酒世界"));
        System.out.println(getResultString("北京市朝阳县BJS000001京东酒世界"));
        System.out.println(getResultString("北京州BJS000001京东酒世界"));
        System.out.println(getResultString("BJS000001京东酒世界"));
        System.out.println(getResultString("     BJS000001京东酒世界   "));
        System.out.println(getResultString("BJS000001   京  东酒  世界"));
        System.out.println(getResultString("        "));
        System.out.println(getResultString("  ss   sss    "));

    }
}
