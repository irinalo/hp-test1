package com.iril.hp.test1.service.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.iril.hp.test1.model.McpRow;
import com.iril.hp.test1.service.RowValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public abstract class AbstractJsonProcessor implements JsonProcessor {

     Map<String, String> fields = new HashMap<>();

    private RowValidationService rowValidationService;

    public AbstractJsonProcessor(RowValidationService rowValidationService) {
        this.rowValidationService = rowValidationService;
        fields.put("timestamp", "int");
        fields.put("origin", "long");
        fields.put("destination", "long");
    }

    private final static Logger logger = LoggerFactory.getLogger(AbstractJsonProcessor.class);

    public void processRow(String fileName, JsonNode node, McpRow mcpRow) {

        mcpRow.setMessageType(node.get("message_type").textValue());

        rowValidationService.validateRow(node, mcpRow, fields);

        processOrigin(mcpRow, node);

        processDestination(mcpRow, node);

    }

    private void processDestination(McpRow mcpRow, JsonNode node) {
        JsonNode destination = node.get("destination");
        if (destination != null) {
            try {
                mcpRow.setDestination(Integer.valueOf(Long.toString(destination.longValue()).substring(0, 2)));
            } catch (Exception ex) {
                logger.debug("Destination not processable");
            }
        }
    }

    private void processOrigin(McpRow mcpRow, JsonNode node) {
        JsonNode origin = node.get("origin");
        if (origin != null) {
            try {
                mcpRow.setOrigin(Integer.valueOf(Long.toString(origin.longValue()).substring(0, 2)));
            } catch (Exception ex) {
                logger.debug("Origin not processable");
            }
        }
    }

}
