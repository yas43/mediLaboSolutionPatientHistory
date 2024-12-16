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




    @PostMapping("/addPrescription/{id}")
    public Prescription addPrescription(@PathVariable("id")Integer id,@RequestBody Prescription prescription){
//        System.out.println("prescription is "+ prescription);
       return  prescriptionService.addPrescriptionToPatient(id,prescription);
    }


    @GetMapping("/prescriptions/{id}")
    public List<String> displayPrescriptions(@PathVariable("id")Integer id, HttpServletRequest request){
        System.out.println("inside history and token received in request.headers.auth.7 is "+request.getHeader(HttpHeaders.AUTHORIZATION.substring(7)));


        System.out.println("inside patient history and prescriptions/id endpoint list is:  "+prescriptionService.displayPrescriptions(id));
        return prescriptionService.displayPrescriptions(id);
    }
}
