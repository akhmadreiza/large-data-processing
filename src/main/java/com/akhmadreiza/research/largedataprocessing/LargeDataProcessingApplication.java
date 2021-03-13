package com.akhmadreiza.research.largedataprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@SpringBootApplication
public class LargeDataProcessingApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(LargeDataProcessingApplication.class);

    public static void main(String[] args) {
        printHeapSize();
        SpringApplication.run(LargeDataProcessingApplication.class, args);
    }

    private static void printHeapSize() {
        int mb = 1024 * 1024;
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long xmx = memoryBean.getHeapMemoryUsage().getMax() / mb;
        long xms = memoryBean.getHeapMemoryUsage().getInit() / mb;
        LOGGER.info("Initial/Max Heap Size: {}mb/{}mb", xms, xmx);
    }
}
