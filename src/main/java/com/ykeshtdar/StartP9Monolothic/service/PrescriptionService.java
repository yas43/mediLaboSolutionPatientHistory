package com.ykeshtdar.StartP9Monolothic.service;

import com.ykeshtdar.StartP9Monolothic.model.*;
import com.ykeshtdar.StartP9Monolothic.repository.*;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final MongoTemplate mongoTemplate;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, MongoTemplate mongoTemplate) {
        this.prescriptionRepository = prescriptionRepository;
        this.mongoTemplate = mongoTemplate;
    }



    public Prescription addPrescriptionToPatient(Integer id,Prescription prescription){

        if (prescriptionRepository.existsById(id)) {
            Query query = new Query(Criteria.where("id").is(prescription.getId()));
            Update update = new Update().push("note",prescription.getNote());
             mongoTemplate.updateFirst(query, update, Prescription.class);
        }
        else {
            prescriptionRepository.save(prescription);
        }
        return prescriptionRepository.findNotesById(id)
                .orElse(null);

    }

    public List<String> displayPrescriptions(Integer id) {

            Optional<Prescription> prescription = prescriptionRepository.findNotesById(id);

            return prescription.map(Prescription::getNote).orElse(null);

    }

    public Prescription findPrescriptionById(Integer id) {
       Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("prescription not founded"));
       return prescription;
    }
}
