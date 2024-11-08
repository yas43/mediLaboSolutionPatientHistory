package com.ykeshtdar.StartP9Monolothic.repository;

import com.ykeshtdar.StartP9Monolothic.model.*;
import org.springframework.data.mongodb.repository.*;

import java.util.*;

public interface PrescriptionRepository extends MongoRepository<Prescription,Integer> {


    @Query(value = "{ '_id': ?0 }", fields = "{ 'note': 1 }")
    Optional<Prescription> findNotesById(Integer id);
}
