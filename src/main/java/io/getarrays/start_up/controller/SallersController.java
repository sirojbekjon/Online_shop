package io.getarrays.start_up.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
public class SallersController {

    @Autowired
    SallerService sallerService;


    @PostMapping("/add")
    public HttpEntity<?> addTeacher(@RequestBody SallerDto sallerDto){
        return sallerService.addTeacher(sallerDto);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getTeacher(@PathVariable Long id){
        return sallerService.getTeacherById(id);
    }

       @DeleteMapping("/delete/{id}")
    public HttpEntity<?> DeleteTeacher(@PathVariable Long id){
        return sallerService.deleteTeacherById(id);
    }


    @PutMapping("/edit/{id}")
    public HttpEntity<?> editTeacher(@PathVariable Long id,@RequestBody SallerDto sallerDto){
        return sallerService.editTeacher(id, sallerDto);
    }



}
