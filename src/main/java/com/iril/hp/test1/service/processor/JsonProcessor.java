package com.iril.hp.test1.service.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.iril.hp.test1.model.McpRow;

public interface JsonProcessor {
    void processRow(String fileName, JsonNode node, McpRow row);
}
