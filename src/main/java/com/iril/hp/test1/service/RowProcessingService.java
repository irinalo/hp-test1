package com.iril.hp.test1.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iril.hp.test1.model.McpRow;
import com.iril.hp.test1.model.MessageType;
import com.iril.hp.test1.persistence.McpRowRepository;
import com.iril.hp.test1.service.processor.ProcessorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RowProcessingService {
    private ObjectMapper objectMapper;
    private McpRowRepository mcpRowRepository;
    private ProcessorFactory processorFactory;


    public RowProcessingService(ObjectMapper objectMapper, McpRowRepository mcpRowRepository,
        ProcessorFactory processorFactory) {
        this.objectMapper = objectMapper;
        this.processorFactory = processorFactory;
        this.mcpRowRepository = mcpRowRepository;
    }

    private final static Logger logger = LoggerFactory.getLogger(RowProcessingService.class);

    void processRow(String line, String fileName) {
        McpRow mcpRow = new McpRow();
        JsonNode node = null;
        try {
            node = objectMapper.readTree(line);
        } catch (Exception ex) {
            mcpRow.setInvalidFields(true);
            mcpRow.setFileName(fileName);
            mcpRowRepository.save(mcpRow);
        }

        if (node.get("message_type") != null) {
            try {
                MessageType messageType = MessageType.valueOf(node.get("message_type").textValue());
                processorFactory.getProcessorByMessageType(messageType).processRow(fileName, node, mcpRow);
            } catch (Exception ex) {
                logger.error("Could not process message type " + node.get("message_type"));
            }
        }

        mcpRow.setFileName(fileName);
        mcpRowRepository.save(mcpRow);
    }

}
