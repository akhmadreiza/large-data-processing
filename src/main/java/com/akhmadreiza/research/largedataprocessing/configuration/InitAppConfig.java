package com.akhmadreiza.research.largedataprocessing.configuration;

import com.akhmadreiza.research.largedataprocessing.entity.MasterTable;
import com.akhmadreiza.research.largedataprocessing.service.MasterDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InitAppConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitAppConfig.class);
    private static final int DATA_COUNT = 300000;

    @Value("${system.config.data.initialization.enabled}")
    boolean dataInitializationEnabled;

    @Autowired
    private MasterDataService masterDataService;

    @PostConstruct
    public void initApp() {
        LOGGER.info("data initialization: {}", dataInitializationEnabled);
        if (dataInitializationEnabled) {
            LOGGER.info("deleting all data to get fresh table");
            Long delStart = System.currentTimeMillis();
            masterDataService.flushData();
            Long delFinish = System.currentTimeMillis();
            LOGGER.info("done deleting all data to get fresh table ({}ms)", delFinish - delStart);

            LOGGER.info("inserting {} data to the table", DATA_COUNT);
            Long insStart = System.currentTimeMillis();
            saveLargeData(DATA_COUNT);
            Long insFinish = System.currentTimeMillis();
            LOGGER.info("done inserting {} data to the table ({}ms)", DATA_COUNT, insFinish - insStart);
        } else {
            LOGGER.info("data initialization is not enabled. proceed to start application.");
        }
    }

    private void saveLargeData(int dataCount) {
        List<MasterTable> listToInsert = new ArrayList<>();
        for (int i = 0; i < dataCount; i++) {
            MasterTable masterTable = new MasterTable();
            masterTable.setId(UUID.randomUUID().toString());
            masterTable.setCreatedDate(LocalDateTime.now());
            masterTable.setName("Data-" + i);
            masterTable.setSalary(new BigDecimal(10000000));
            listToInsert.add(masterTable);
        }
        masterDataService.initData(listToInsert);
    }
}
