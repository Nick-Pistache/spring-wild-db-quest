package com.wildcodeschool.myProjectWithDB.controllers;

import com.wildcodeschool.myProjectWithDB.entities.School;
import com.wildcodeschool.myProjectWithDB.repositories.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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



    @PutMapping("/api/schools/{id}")
    public School update(
            // TODO récupérer les données (étape 2)
            @PathVariable int id,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) int capacity,
            @RequestParam (required = false) String country
    ) {
        // TODO enregistrer les données (étape 3)
        School school = SchoolRepository.selectById(id);
        SchoolRepository.update(
                id,
                name != null ? name : school.getName(),
                new Integer(capacity) != null ? capacity : school.getCapacity(),
                country != null ? country : school.getCoutry()

        );
        return SchoolRepository.selectById(id);
    }
}
