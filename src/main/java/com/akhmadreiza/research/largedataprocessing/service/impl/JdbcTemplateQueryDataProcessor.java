package com.akhmadreiza.research.largedataprocessing.service.impl;

import com.akhmadreiza.research.largedataprocessing.domain.MasterTableDto;
import com.akhmadreiza.research.largedataprocessing.service.DataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcTemplateQueryDataProcessor implements DataProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplateQueryForListDataProcessor.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MasterTableDto> selectAllData() {
        LOGGER.info("getting data from TBL_MASTER and fetch into array list");
        long queryStart = System.currentTimeMillis();
        List<MasterTableDto> result = jdbcTemplate.query("SELECT * FROM TBL_MASTER", (rs, rowNum) -> {
            MasterTableDto masterTableDto = new MasterTableDto();
            masterTableDto.setId(rs.getString("ID"));
            masterTableDto.setName(rs.getString("NAME"));
            masterTableDto.setSalary(rs.getBigDecimal("SALARY"));
            return masterTableDto;
        });
        long queryFinish = System.currentTimeMillis();
        LOGGER.info("successfully getting {} data from TBL_MASTER and fetch into array list ({}ms)", result.size(), queryFinish - queryStart);
        return result;
    }
}
