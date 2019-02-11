package com.iril.hp.test1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Configuration
@EnableSolrRepositories("com.iril.hp.test1.repository")
public class SolrConfiguration {

    @Bean
    EmbeddedSolrServerFactory solrServerFactory() throws  IOException, ParserConfigurationException, SAXException {
        String solrHome = getClass().getClassLoader().getResource("solr-home").getPath();
        return new EmbeddedSolrServerFactory(solrHome);
    }

    @Bean
    public SolrTemplate solrTemplate() throws ParserConfigurationException, SAXException, IOException {
        return new SolrTemplate(solrServerFactory());
    }
}
