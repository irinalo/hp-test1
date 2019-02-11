package com.iril.hp.test1.service.processor;

import com.iril.hp.test1.model.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProcessorFactory {
    @Autowired
    private Map<String, JsonProcessor> processingServices;

    public JsonProcessor getProcessorByMessageType(MessageType messageType) {
        switch (messageType) {
            case CALL:
                return processingServices.get("callProcessor");
            case MSG:
                return processingServices.get("messageProcessor");
            default:
                return processingServices.get("callProcessor");
        }
    }
}


