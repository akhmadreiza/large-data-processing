package com.akhmadreiza.research.largedataprocessing.service.impl;

import com.akhmadreiza.research.largedataprocessing.service.DataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JdbcTemplateDataProcessor implements DataProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplateDataProcessor.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void selectAllData() {
        LOGGER.info("getting data from TBL_MASTER");
        Long queryStart = System.currentTimeMillis();
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT * FROM TBL_MASTER;");
        Long queryFinish = System.currentTimeMillis();
        LOGGER.info("successfully getting {} of data from TBL_MASTER ({}ms)", result.size(), queryFinish - queryStart);
    }
}
