package com.iril.hp.test1.service;

import com.iril.hp.test1.model.Kpis;
import com.iril.hp.test1.model.McpFile;
import com.iril.hp.test1.model.MessageType;
import com.iril.hp.test1.persistence.McpFileRepository;
import com.iril.hp.test1.persistence.McpRowRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class KpisService {

    private McpRowRepository mcpRowRepository;
    private McpFileRepository mcpFileRepository;

    public KpisService(McpRowRepository mcpRowRepository, McpFileRepository mcpFileRepository) {
        this.mcpRowRepository = mcpRowRepository;
        this.mcpFileRepository = mcpFileRepository;
    }

    public Kpis getKpis() {
        return new Kpis(mcpFileRepository.count(),
            mcpRowRepository.count(),
            mcpRowRepository.countByMessageType(MessageType.CALL.toString()),
            mcpRowRepository.countByMessageType(MessageType.MSG.toString()),
            mcpRowRepository.countDistinctOrigin(),
            mcpRowRepository.countDistinctDestination(),
            mcpFileRepository.findAll().stream().collect(Collectors.toMap(McpFile::getFileName,
                McpFile::getProcessTime, (oldV, newV) -> newV)));
    }
}
