package com.iril.hp.test1.service;

import com.iril.hp.test1.model.McpFile;
import com.iril.hp.test1.persistence.McpFileRepository;
import com.iril.hp.test1.persistence.McpRowRepository;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileProcessingService {
    private static final int CONNECTION_TIMEOUT = 3000;
    private static final int READ_TIMEOUT = 3000;
    @Value("${file.repo}")
    String fileRepo;

    private RowProcessingService rowProcessingService;
    private McpRowRepository mcpRowRepository;
    private SolrTemplate solrTemplate;
    private McpFileRepository mcpFileRepository;

    public FileProcessingService(RowProcessingService rowProcessingService, McpRowRepository mcpRowRepository,
        SolrTemplate solrTemplate, McpFileRepository mcpFileRepository) {
        this.rowProcessingService = rowProcessingService;
        this.mcpRowRepository = mcpRowRepository;
        this.mcpFileRepository = mcpFileRepository;
        this.solrTemplate = solrTemplate;
    }

    private final static Logger logger = LoggerFactory.getLogger(FileProcessingService.class);

    public void processFile(String fileName) throws Exception {
        String fileUrl = fileRepo + fileName;
        long startTime = System.currentTimeMillis();
        FileUtils.copyURLToFile(
            new URL(fileUrl),
            new File(fileName),
            CONNECTION_TIMEOUT,
            READ_TIMEOUT);

        mcpFileRepository.deleteByFileName(fileName);
        mcpRowRepository.deleteByFileName(fileName);
        solrTemplate.getSolrClient().deleteByQuery("message", "doc_s:" + fileName);

        Files.readAllLines(Paths.get(fileName)).parallelStream().forEach(line -> {
                logger.info(line);
                rowProcessingService.processRow(line, fileName);
            }
        );
        long stopTime = System.currentTimeMillis();
        mcpFileRepository.save(new McpFile(fileName, stopTime - startTime));
    }
}
