package com.rga.springbootawssnsproducerexample.repository;

import com.rga.springbootawssnsproducerexample.model.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmazonRdsRepository extends JpaRepository<SchoolEntity, Long> {

}
