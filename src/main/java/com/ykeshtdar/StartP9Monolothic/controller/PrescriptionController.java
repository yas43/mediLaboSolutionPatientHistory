package com.ykeshtdar.StartP9Monolothic.controller;

import com.ykeshtdar.StartP9Monolothic.model.*;
import com.ykeshtdar.StartP9Monolothic.service.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
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
    public List<String> displayPrescriptions(@PathVariable("id")Integer id, HttpServletRequest request){
        System.out.println("inside history and token received in request.headers.auth.7 is "+request.getHeader(HttpHeaders.AUTHORIZATION.substring(7)));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principle = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principle;
        System.out.println("inside history and authentication(securitycontext.getcontext.getauthentication.principle)) is: "+principle);
        System.out.println("username is "+userDetails.getUsername());
        System.out.println("password is "+userDetails.getPassword());
        return prescriptionService.displayPrescriptions(id);
    }
}
