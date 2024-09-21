package com.telusko.controller;

import com.telusko.helper.PersonHelper;
import com.telusko.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;


    @PostMapping("/uploadExcelFile")
    public ResponseEntity<?> uploadExcelFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (PersonHelper.checkExcelFormat(multipartFile)) {
            this.personService.saveDataFromExcelToDB(multipartFile);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an excel file");
    }


    @GetMapping("/persons")
    public ResponseEntity<?> getAllPersons() {
        return ResponseEntity.status(HttpStatus.OK).body(this.personService.getAllPersons());
    }
}
