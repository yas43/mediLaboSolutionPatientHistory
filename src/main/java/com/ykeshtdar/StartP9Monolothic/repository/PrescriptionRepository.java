package com.ykeshtdar.StartP9Monolothic.repository;

import com.ykeshtdar.StartP9Monolothic.model.*;
import org.springframework.data.mongodb.repository.*;

public interface PrescriptionRepository extends MongoRepository<Prescription,Integer> {
}
