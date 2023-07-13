package com.runjing.learn_runjing.utils;

import com.runjing.learn_runjing.erp.config.RunJingException;
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
            log.error("入参workHouseNameAndCode不符合处理格式:{}", workHouseNameAndCode);
            throw new RunJingException("入参workHouseNameAndCode不符合处理格式！");
        } else {
            String trimHandle = workHouseNameAndCode.replace(" ","");
            if ((trimHandle.contains(SHI) && (trimHandle.contains(XIAN) || trimHandle.contains(QU))) || (trimHandle.contains(ZHOU))) {
                int i = 1;
                if (trimHandle.contains(SHI)) {
                    i += trimHandle.indexOf(SHI);
                } else {
                    i += trimHandle.indexOf(ZHOU);
                }
                return trimHandle.substring(i);
            }
            if (!trimHandle.contains(JSJ)){
                log.error("入参workHouseNameAndCode无意义门店地址名称字符串:{}",workHouseNameAndCode);
                throw new RunJingException("入参workHouseNameAndCode无意义门店地址名称字符串");
            }
            return trimHandle.replace(JSJ, "");
        }
    }
}
