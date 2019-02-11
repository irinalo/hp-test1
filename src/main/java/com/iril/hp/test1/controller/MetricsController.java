package com.iril.hp.test1.controller;

import com.iril.hp.test1.model.Metrics;
import com.iril.hp.test1.service.MetricsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/metrics")
@Api(tags = "Metrics Endpoint")
public class MetricsController {

    @Autowired
    private MetricsService metricsService;

    @GetMapping
    public Metrics getMetrics(@RequestParam String date,
        @RequestParam List<String> words) throws org.apache.solr.client.solrj.SolrServerException, IOException {
        String fileName = "MCP_" + date + ".json";
        return metricsService.getMetrics(fileName, words);
    }


}
