package com.akhmadreiza.research.largedataprocessing.repository;

import com.akhmadreiza.research.largedataprocessing.entity.MasterTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterTableJPARepository extends JpaRepository<MasterTable, String> {
}
