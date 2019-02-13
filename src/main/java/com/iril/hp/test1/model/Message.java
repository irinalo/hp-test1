package com.iril.hp.test1.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument
public class Message {

    @Id
    @Field("id")
    private String id;

    @Field("value_txt")
    private String value;

    @Field("doc_s")
    private String doc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setId(String currentTimeMillis) {
        id = currentTimeMillis;
    }

    public String getId() {
        return id;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

}
