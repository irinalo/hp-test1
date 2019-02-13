package com.iril.hp.test1.controller;

import com.iril.hp.test1.service.FileProcessingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/file")
@Api(tags = "File Processing Endpoint")
public class FileProcessingController {

    @Autowired
    private FileProcessingService fileProcessingService;

    @PostMapping("/process")
    public void processFile(@RequestParam String date) throws Exception {
        String fileName = "MCP_" + date + ".json";
        try {
            fileProcessingService.processFile(fileName);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("file.not.found: " + ex.getMessage());
        }
    }


}
