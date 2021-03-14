package com.akhmadreiza.research.largedataprocessing.service.impl;

import com.akhmadreiza.research.largedataprocessing.domain.MasterTableDto;
import com.akhmadreiza.research.largedataprocessing.service.DataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.akhmadreiza.research.largedataprocessing.constants.QueryConstant.SELECT_ALL_FROM_MASTER_TABLE;

@Service
public class JdbcTemplateQueryForListDataProcessor implements DataProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplateQueryForListDataProcessor.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<MasterTableDto> selectAllData() {
        LOGGER.info("getting data from TBL_MASTER");
        long queryStart = System.currentTimeMillis();
        List<Map<String, Object>> result = jdbcTemplate.queryForList(SELECT_ALL_FROM_MASTER_TABLE);
        long queryFinish = System.currentTimeMillis();
        LOGGER.info("successfully getting {} of data from TBL_MASTER ({}ms)", result.size(), queryFinish - queryStart);

        LOGGER.info("fetch data from TBL_MASTER into array list");
        long constructTimeStart = System.currentTimeMillis();
        List<MasterTableDto> resultList = constructMasterTableDtos(result);
        long constructTimeFinish = System.currentTimeMillis();
        LOGGER.info("successfully fetch data from TBL_MASTER into array list ({}ms)", constructTimeFinish - constructTimeStart);
        return resultList;
    }

    private List<MasterTableDto> constructMasterTableDtos(List<Map<String, Object>> result) {
        List<MasterTableDto> resultList = new ArrayList<>();
        for (Map row : result) {
            MasterTableDto masterTableDto = new MasterTableDto();
            masterTableDto.setId((String) row.get("ID"));
            masterTableDto.setName((String) row.get("NAME"));
            masterTableDto.setSalary((BigDecimal) row.get("SALARY"));
            resultList.add(masterTableDto);
        }
        return resultList;
    }
}
