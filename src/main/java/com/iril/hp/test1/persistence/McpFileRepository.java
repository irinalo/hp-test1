package com.iril.hp.test1.persistence;

import com.iril.hp.test1.model.McpFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface McpFileRepository extends JpaRepository<McpFile, Long> {

    @Transactional
    Long deleteByFileName(String fileName);

}