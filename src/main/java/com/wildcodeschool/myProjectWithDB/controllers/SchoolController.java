package com.wildcodeschool.myProjectWithDB.controllers;

import com.wildcodeschool.myProjectWithDB.entities.School;
import com.wildcodeschool.myProjectWithDB.entities.Wizard;
import com.wildcodeschool.myProjectWithDB.repositories.SchoolRepository;
import com.wildcodeschool.myProjectWithDB.repositories.WizardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class SchoolController {

    @PostMapping("/api/schools")
    @ResponseStatus(HttpStatus.CREATED)
    public com.wildcodeschool.myProjectWithDB.entities.School store(
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam String country
    ){
        int idGeneratedByInsertion = SchoolRepository.insert(
                name,
                capacity,
                country
        );
        return SchoolRepository.selectById(idGeneratedByInsertion);
    }

    @DeleteMapping("/api/schools/{id}")
    public void delete(@PathVariable int id) {
        SchoolRepository.delete(id);
    }
}
