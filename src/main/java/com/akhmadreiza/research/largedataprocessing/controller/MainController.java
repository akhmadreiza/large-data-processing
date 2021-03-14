package com.akhmadreiza.research.largedataprocessing.controller;

import com.akhmadreiza.research.largedataprocessing.constants.DataProcessorType;
import com.akhmadreiza.research.largedataprocessing.domain.MasterTableDto;
import com.akhmadreiza.research.largedataprocessing.service.DataProcessorFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private DataProcessorFactory dataProcessorFactory;

    @GetMapping("/fetch/jdbc/queryForList")
    public ResponseEntity<String> getAllDataByQueryForList() throws JsonProcessingException {
        long startTime = System.currentTimeMillis();
        List<MasterTableDto> result = dataProcessorFactory.getDataProcessor(DataProcessorType.JDBC_TEMPLATE_QUERY_FOR_LIST).selectAllData();
        return getStringResponseEntity(startTime, result);
    }

    @GetMapping("/fetch/jdbc/query")
    public ResponseEntity<String> getAllDataByQuery() throws JsonProcessingException {
        long startTime = System.currentTimeMillis();
        List<MasterTableDto> result = dataProcessorFactory.getDataProcessor(DataProcessorType.JDBC_TEMPLATE_QUERY).selectAllData();
        return getStringResponseEntity(startTime, result);
    }

    private ResponseEntity<String> getStringResponseEntity(long startTime, List<MasterTableDto> result) throws JsonProcessingException {
        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        LOGGER.info(objectMapper.writeValueAsString(result));
        return new ResponseEntity<>("OK (row selected: " + result.size() + " timeTaken: " + timeTaken + " ms)", HttpStatus.OK);
    }
}
