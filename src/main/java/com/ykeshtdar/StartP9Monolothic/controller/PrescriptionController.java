package com.ykeshtdar.StartP9Monolothic.controller;

import com.ykeshtdar.StartP9Monolothic.model.*;
import com.ykeshtdar.StartP9Monolothic.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("prescription")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }



    @GetMapping("/prescription/{id}")
    public Prescription findPrescriptionById(@PathVariable("id")Integer id){
        return prescriptionService.findPrescriptionById(id);
    }




//    @GetMapping
//    public UserInformation displayUserByName(@RequestParam("firstname")String name){
//        return userInformationService.findByUsername(name);
//    }

//    @GetMapping("/all")
//    public List<UserInformation> displayAllUser(){
//        return userInformationService.displayAllUserInformation();
//    }

//    @PostMapping
//    public UserInformation addUser(@RequestBody UserInformation userInformation){
//         return userInformationService.addUserInformation(userInformation);
//    }

//    @PutMapping("/update")
//    public UserInformation update(@RequestParam("firstname")String firstname,
//                                  @RequestParam("lastname")String lastname,
//                                  @RequestParam("gender")String gender,
//                                  @RequestParam("birthdate") LocalDate birthdate,
//                                  @RequestParam("address")String address,
//                                  @RequestParam("phoneNumber")String phoneNumber
//                                  ){
//
//        return userInformationService.updateUserInformation(firstname,lastname,gender,birthdate,address,phoneNumber);
//    }

//    @DeleteMapping
//    public void deleteUser(@RequestParam("firstname")String firstname){
//         userInformationService.deleteUser(firstname);
//    }



    @PostMapping("/addPrescription/{id}")
    public Prescription addPrescription(@PathVariable("id")Integer id,@RequestBody Prescription prescription){
//        System.out.println("prescription is "+ prescription);
       return  prescriptionService.addPrescriptionToPatient(id,prescription);
    }


    @GetMapping("/prescriptions/{id}")
    public List<String> displayPrescriptions(@PathVariable("id")Integer id){
        return prescriptionService.displayPrescriptions(id);
    }
}
