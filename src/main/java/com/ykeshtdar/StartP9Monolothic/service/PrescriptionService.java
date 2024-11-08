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

//    public Prescription addPrescription(Prescription prescription) {
//        if (isAlreadyExist(prescriptionRepository.existsById())){
//            throw new RuntimeException("this user is already exist");
//        }
//       return userInformationRepository.save(userinformation);
//    }

//    public UserInformation findByUsername(String name) {
//        return userInformationRepository.findByFirstname(name)
//                .orElseThrow(()->new RuntimeException("can not find user by this username"));
//    }

//    public UserInformation updateUserInformation(String firstname,
//                                                 String lastname,
//                                                 String gender,
//                                                 LocalDate birthdate,
//                                                 String address,
//                                                 String phoneNumber) {
//        if (!isAlreadyExist(firstname)){
//             throw  new RuntimeException("this user dose not exist");
//        }
//       UserInformation userInformation = findByUsername(firstname);
//        userInformation.setFirstname(firstname);
//        userInformation.setLastname(lastname);
//        userInformation.setGender(gender);
//        userInformation.setBirthdate(birthdate);//format:uuuu-MM-d
//        userInformation.setAddress(address);
//        userInformation.setPhoneNumber(phoneNumber);
//        return userInformationRepository.save(userInformation);
//    }

//    public List<UserInformation> displayAllUserInformation() {
//        return userInformationRepository.findAll();
//    }

//    public void deleteUser(String firstname) {
//        UserInformation userInformation = findByUsername(firstname);
//        userInformationRepository.delete(userInformation);
//    }


//    public Boolean isAlreadyExist(String firstname){
//       return userInformationRepository.existsByFirstname(firstname);
//    }


    public Prescription addPrescriptionToPatient(Integer id,Prescription prescription){

//        int id = prescription.getId();
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
//         prescriptionRepository.save(prescription);
//        return prescriptionRepository.findById(prescription.getId())
//                .orElseThrow(()->new RuntimeException("patient not founded"));
    }

    public List<String> displayPrescriptions(Integer id) {

            Optional<Prescription> prescription = prescriptionRepository.findNotesById(id);
//        System.out.println("prescription is "+prescription);
            return prescription.map(Prescription::getNote).orElse(null);

    }

    public Prescription findPrescriptionById(Integer id) {
       Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("prescription not founded"));
       return prescription;
    }
}
