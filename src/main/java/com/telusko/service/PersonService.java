package com.telusko.service;

import com.telusko.helper.PersonHelper;
import com.telusko.model.Person;
import com.telusko.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PersonService {


    @Autowired
    private PersonRepo personRepo;

    public void saveDataFromExcelToDB(MultipartFile multipartFile) throws IOException {
        List<Person> persons = PersonHelper.convertExcelToPerson(multipartFile.getInputStream());
        this.personRepo.saveAll(persons);
    }


    public List<Person> getAllPersons() {
        return this.personRepo.findAll();
    }
}
