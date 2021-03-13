package com.akhmadreiza.research.largedataprocessing.service;

import com.akhmadreiza.research.largedataprocessing.entity.MasterTable;

import java.util.List;

public interface MasterDataService {
    void initData(List<MasterTable> masterTableList);

    void flushData();
}
