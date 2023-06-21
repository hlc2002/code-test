package com.runjing.learn_runjing.dataSource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author forestSpringH
 * @date 2023/6/20
 * @project learn_runjing
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDynamicDataSourceKey();
    }
}
