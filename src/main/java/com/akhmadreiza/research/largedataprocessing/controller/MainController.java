package com.akhmadreiza.research.largedataprocessing.controller;

import com.akhmadreiza.research.largedataprocessing.constants.DataProcessorType;
import com.akhmadreiza.research.largedataprocessing.service.DataProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private DataProcessorFactory dataProcessorFactory;

    @GetMapping("/fetch/jdbc")
    public ResponseEntity<String> getAllData() {
        long startTime = System.currentTimeMillis();
        dataProcessorFactory.getDataProcessor(DataProcessorType.JDBC_TEMPLATE).selectAllData();
        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        return new ResponseEntity<>("OK (" + timeTaken + "ms)", HttpStatus.OK);
    }
}
