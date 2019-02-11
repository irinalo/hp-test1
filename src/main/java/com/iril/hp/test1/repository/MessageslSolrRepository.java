package com.iril.hp.test1.repository;

import com.iril.hp.test1.model.Message;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageslSolrRepository extends PagingAndSortingRepository<Message, String> {
}     