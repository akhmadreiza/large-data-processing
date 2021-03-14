package com.akhmadreiza.research.largedataprocessing.service;

import com.akhmadreiza.research.largedataprocessing.constants.DataProcessorType;
import com.akhmadreiza.research.largedataprocessing.service.impl.JPADataProcessor;
import com.akhmadreiza.research.largedataprocessing.service.impl.JdbcTemplateQueryDataProcessor;
import com.akhmadreiza.research.largedataprocessing.service.impl.JdbcTemplateQueryForListDataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataProcessorFactory {

    @Autowired
    private JdbcTemplateQueryForListDataProcessor jdbcTemplateQueryForListDataProcessor;

    @Autowired
    private JdbcTemplateQueryDataProcessor jdbcTemplateQueryDataProcessor;

    @Autowired
    private JPADataProcessor jpaDataProcessor;

    public DataProcessor getDataProcessor(DataProcessorType dataProcessorType) {
        if (dataProcessorType.equals(DataProcessorType.JDBC_TEMPLATE_QUERY_FOR_LIST)) {
            return jdbcTemplateQueryForListDataProcessor;
        } else if (dataProcessorType.equals(DataProcessorType.JDBC_TEMPLATE_QUERY)) {
            return jdbcTemplateQueryDataProcessor;
        } else if (dataProcessorType.equals(DataProcessorType.JPA)) {
            return jpaDataProcessor;
        } else {
            throw new UnsupportedOperationException("Data Processor undefined!");
        }
    }
}
