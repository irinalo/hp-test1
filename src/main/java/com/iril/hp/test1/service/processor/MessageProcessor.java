package com.iril.hp.test1.service.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.iril.hp.test1.repository.MessageslSolrRepository;
import com.iril.hp.test1.model.McpRow;
import com.iril.hp.test1.model.Message;
import com.iril.hp.test1.service.RowValidationService;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessor extends AbstractJsonProcessor implements JsonProcessor {

    private MessageslSolrRepository messageRepository;

    public MessageProcessor(RowValidationService rowValidationService,
        MessageslSolrRepository messageRepository) {
        super(rowValidationService);
        this.messageRepository = messageRepository;
        fields.put("message_status", "message_status");
        fields.put("message_content", "text");
    }

    public void processRow(String fileName, JsonNode node, McpRow mcpRow) {
        super.processRow(fileName, node, mcpRow);

        if (node.get("message_content") != null) {
            String value = node.get("message_content").textValue();
            if (value.isEmpty()) {
                mcpRow.setBlankContent(true);
            } else {
                Message message = new Message();
                message.setValue(value);
                message.setDoc(fileName);
                message.setId(System.currentTimeMillis());
                messageRepository.save(message);
            }
        }
    }

}
