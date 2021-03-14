package com.akhmadreiza.research.largedataprocessing.service.impl;

import com.akhmadreiza.research.largedataprocessing.domain.MasterTableDto;
import com.akhmadreiza.research.largedataprocessing.service.DataProcessor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPADataProcessor implements DataProcessor {
    @Override
    public List<MasterTableDto> selectAllData() {
        throw new UnsupportedOperationException("JPA not yet supported!");
    }
}
