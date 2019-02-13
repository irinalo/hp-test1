package com.iril.hp.test1.service;

import com.iril.hp.test1.model.MessageType;
import com.iril.hp.test1.persistence.McpRowRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RowProcessingServiceTest {

    @Autowired
    private RowProcessingService rowProcessingService;

    @Autowired
    private McpRowRepository mcpRowRepository;

    private final static String FILE_NAME = "test.json";

    @Test
    public void testProcessCallWithMissingFields() {
        String call = "{\"message_type\":\"CALL\"}";
        rowProcessingService.processRow(call, FILE_NAME);
        assertEquals(1, mcpRowRepository.countByFileNameAndMissingFieldsTrue(FILE_NAME));
        assertEquals(1, mcpRowRepository.countByMessageType(MessageType.CALL.name()));

    }

    @Test
    public void testProcessInvalidMessageType() {
        String call = "{\"message_type\":\"\"}";
        rowProcessingService.processRow(call, FILE_NAME);
        assertEquals(1, mcpRowRepository.countByFileNameAndInvalidFieldsTrue(FILE_NAME));
    }

}
