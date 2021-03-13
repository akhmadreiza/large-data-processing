package com.akhmadreiza.research.largedataprocessing.service.impl;

import com.akhmadreiza.research.largedataprocessing.service.DataProcessor;
import org.springframework.stereotype.Service;

@Service
public class JPADataProcessor implements DataProcessor {
    @Override
    public void selectAllData() {
        throw new UnsupportedOperationException("JPA not yet supported!");
    }
}
