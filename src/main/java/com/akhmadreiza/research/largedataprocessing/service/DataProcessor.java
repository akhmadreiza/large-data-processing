package com.akhmadreiza.research.largedataprocessing.service;

import com.akhmadreiza.research.largedataprocessing.domain.MasterTableDto;

import java.util.List;

public interface DataProcessor {
    List<MasterTableDto> selectAllData();
}
