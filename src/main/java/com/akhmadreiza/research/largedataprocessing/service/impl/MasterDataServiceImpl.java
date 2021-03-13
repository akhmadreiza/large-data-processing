package com.akhmadreiza.research.largedataprocessing.service.impl;

import com.akhmadreiza.research.largedataprocessing.entity.MasterTable;
import com.akhmadreiza.research.largedataprocessing.repository.MasterTableJPARepository;
import com.akhmadreiza.research.largedataprocessing.service.MasterDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterDataServiceImpl implements MasterDataService {

    private MasterTableJPARepository masterTableJPARepository;

    public MasterDataServiceImpl(MasterTableJPARepository masterTableJPARepository) {
        this.masterTableJPARepository = masterTableJPARepository;
    }

    @Override
    public void initData(List<MasterTable> masterTableList) {
        masterTableJPARepository.saveAll(masterTableList);
    }

    @Override
    public void flushData() {
        masterTableJPARepository.deleteAll();
    }
}
