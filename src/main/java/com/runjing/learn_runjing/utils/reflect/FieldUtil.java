package com.runjing.learn_runjing.utils.reflect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author forestSpringH
 * @date 2023/7/14
 * @project learn_runjing
 */
public class FieldUtil {

    public static Map<String, String> getFieldMap(Class<?> dataType) {
        Assert.notNull(dataType, "入参字节码文件为null，拒绝访问");
        Map<String, String> result = new HashMap<>();
        List<Field> declaredFields = List.of(dataType.getDeclaredFields());
        if (CollectionUtils.isEmpty(declaredFields)) {
            return result;
        }
        declaredFields.forEach(field ->
                result.put(field.getName(), field.getType().getSimpleName())
        );
        return result;
    }

    public static Class<?> getDataType(String dataType) {
        Assert.notNull(dataType, "入参描述数据类型字符串为nll，拒绝访问");
        return DataType.ENUM_MAP.getOrDefault(dataType, null);
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    enum DataType {
        //POI内部设置单元格数据类型的方式是定义数据类型的枚举值，所以只需要在这个枚举里设置成与POI一致的枚举值即可。
        STRING(String.class, "String"),
        INTEGER(Integer.class, "Integer"),
        LONG(Long.class, "Long"),
        BOOLEAN(Boolean.class, "Boolean"),
        DOUBLE(Double.class, "Double"),
        LOCAL_DATE_TIME(LocalDateTime.class, "LocalDataTime");
        private Class<?> type;
        private String name;
        public static final Map<String, Class<?>> ENUM_MAP = Arrays.stream(DataType.values()).collect(Collectors.toMap(DataType::getName, DataType::getType));
    }
}
