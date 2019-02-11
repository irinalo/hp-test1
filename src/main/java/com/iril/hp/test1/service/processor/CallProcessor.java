package com.iril.hp.test1.service.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.iril.hp.test1.model.McpRow;
import com.iril.hp.test1.service.RowValidationService;
import org.springframework.stereotype.Service;

@Service
public class CallProcessor extends AbstractJsonProcessor implements JsonProcessor {

    public CallProcessor(RowValidationService rowValidationService){
        super(rowValidationService);
        fields.put("duration", "int");
        fields.put("status_code", "status_code");
        fields.put("status_description", "text");
    }

    public void processRow(String fileName, JsonNode node, McpRow mcpRow) {
        super.processRow(fileName, node, mcpRow);

        if (node.get("status_code") != null) {
            mcpRow.setStatusCode(node.get("status_code").textValue());
        }
        if (node.get("duration") != null) {
            mcpRow.setDuration(node.get("duration").longValue());
        }
    }

}
