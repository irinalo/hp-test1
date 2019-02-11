package com.iril.hp.test1.persistence;

import com.iril.hp.test1.model.CountryCodesMetrics;
import com.iril.hp.test1.model.McpRow;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface McpRowRepository extends JpaRepository<McpRow, Long> {
    long countByFileNameAndMissingFieldsTrue(String fileName);

    long countByFileNameAndInvalidFieldsTrue(String fileName);

    long countByFileNameAndBlankContentTrue(String fileName);

    @Transactional
    Long deleteByFileName(String fileName);

    @Query("select new  com.iril.hp.test1.model.CountryCodesMetrics(r.origin , r.destination, count(r.id), avg(r.duration)) from McpRow r " +
        "where messageType = 'CALL' and r.origin>0 and r.destination>0 and fileName = :fileName group by  r.origin, r.destination")
    List<CountryCodesMetrics> countByOriginAndDestination(@Param("fileName") String fileName);

    long countByFileNameAndStatusCode(String fileName, String statusCode);

    long countByMessageType(String messageType);

    @Query("Select count(distinct r.origin) from McpRow r")
    Integer countDistinctOrigin();

    @Query("Select count(distinct r.destination) from McpRow r")
    Integer countDistinctDestination();
}