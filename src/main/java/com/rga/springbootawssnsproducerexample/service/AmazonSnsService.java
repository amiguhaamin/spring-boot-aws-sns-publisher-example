package com.rga.springbootawssnsproducerexample.service;

import com.rga.springbootawssnsproducerexample.model.SchoolDetails;
import com.rga.springbootawssnsproducerexample.model.entity.SchoolEntity;
import com.rga.springbootawssnsproducerexample.repository.AmazonRdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AmazonSnsService {

    @Autowired
    AmazonRdsRepository rdsRepository;

    @Transactional
    public void saveData(SchoolDetails schoolDetails) throws Exception {
        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setName(schoolDetails.getName());
        schoolEntity.setType(schoolDetails.getType());
        schoolEntity.setDescription(schoolDetails.getDescription());
        rdsRepository.save(schoolEntity);
    }

}
