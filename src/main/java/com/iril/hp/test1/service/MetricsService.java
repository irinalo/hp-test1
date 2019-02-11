package com.iril.hp.test1.service;

import com.iril.hp.test1.model.Metrics;
import com.iril.hp.test1.persistence.McpRowRepository;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetricsService {

    private McpRowRepository repository;
    private SolrTemplate solrTemplate;

    public MetricsService(McpRowRepository repository, SolrTemplate solrTemplate) {
        this.repository = repository;
        this.solrTemplate = solrTemplate;
    }

    public Metrics getMetrics(String fileName, List<String> words) throws org.apache.solr.client.solrj.SolrServerException, IOException{
        return new Metrics(fileName, repository.countByFileNameAndMissingFieldsTrue(fileName),
            repository.countByFileNameAndInvalidFieldsTrue(fileName),
            repository.countByFileNameAndBlankContentTrue(fileName),
            repository.countByOriginAndDestination(fileName),
            ((double) repository.countByFileNameAndStatusCode(fileName, "KO") / repository.countByFileNameAndStatusCode(fileName, "OK")),
            getWordsFrequency(fileName, words));

    }

    private Map<String, Integer> getWordsFrequency(String fileName,
        List<String> words) throws org.apache.solr.client.solrj.SolrServerException, IOException {
        SolrQuery query = new SolrQuery("doc_s:" + fileName);
        words.forEach(word -> query.addField(word + "Freq:termfreq(\"value_txt\",\"" + word + "\")"));
        //TODO: read page by page
        query.setRows(100);
        QueryRequest request = new QueryRequest(query);
        QueryResponse response = request.process(solrTemplate.getSolrClient(), "message");
        Map<String, Integer> wordFrequency = new HashMap<>();
        response.getResults().forEach(document -> document.getFieldNames().forEach(fieldName -> {
            Integer freq = wordFrequency.get(fieldName);
            if (freq == null) {
                wordFrequency.put(fieldName, (Integer) document.getFieldValue(fieldName));
            } else {
                wordFrequency.put(fieldName, freq + (Integer) document.getFieldValue(fieldName));
            }
        }));
        return wordFrequency;
    }
}
