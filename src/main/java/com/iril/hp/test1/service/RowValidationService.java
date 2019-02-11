package com.iril.hp.test1.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.iril.hp.test1.model.McpRow;
import com.iril.hp.test1.model.MessageStatus;
import com.iril.hp.test1.model.StatusCode;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RowValidationService {

    public RowValidationService() {

    }

    public void validateRow(JsonNode row, McpRow result, Map<String, String> fields) {

        fields.forEach((fieldName, fieldType) -> {
            JsonNode fieldValue = row.get(fieldName);
            if (fieldValue == null) {
                result.setMissingFields(true);
            } else {
                result.setInvalidFields(!isValidateType(fieldValue, fieldType));
            }
        });

    }

    private boolean isValidateType(JsonNode fieldValue, String fieldType) {
        switch (fieldType) {
            case "int":
                if (fieldValue.isInt()) {
                    return true;
                }
                break;
            case "long":
                if (fieldValue.isLong()) {
                    return true;
                }
                break;
            case "text":
                if (fieldValue.isTextual()) {
                    return true;
                }
                break;
            case "status_code":
                try {
                    StatusCode.valueOf(fieldValue.textValue());
                } catch (Exception ex) {
                    return false;
                }
                return true;
            case "message_status":
                try {
                    MessageStatus.valueOf(fieldValue.textValue());
                } catch (Exception ex) {
                    return false;
                }
                return true;
        }
        return false;
    }
}
