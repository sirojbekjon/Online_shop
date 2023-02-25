package io.getarrays.start_up.controller;

import io.getarrays.start_up.entity.TypeProduct;
import io.getarrays.start_up.repository.TypeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/typeSubject")
public class TypeProductController {

    @Autowired
    TypeProductRepository typeProductRepository;


    @PostMapping("/add")
    public HttpEntity<?> addTypeSubject(@RequestBody TypeProduct typeProduct){
        TypeProduct typeProduct1 = new TypeProduct(
                typeProduct.getName()
        );
        TypeProduct savedType = typeProductRepository.save(typeProduct1);
    return ResponseEntity.status(202).body(savedType +" Saved");
    }



    @GetMapping("/get")
    public HttpEntity<?> getTypeSubject(){
        List<TypeProduct> typeProducts = typeProductRepository.findAll();
        return ResponseEntity.status(202).body(typeProducts);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getTypeSubjectById(@PathVariable Long id){
        Optional<TypeProduct> optionalTypeSubject = typeProductRepository.findById(id);
        if (optionalTypeSubject.isPresent()){
            TypeProduct typeProduct = optionalTypeSubject.get();
            return ResponseEntity.status(202).body(typeProduct);
        }else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }

    @GetMapping("/delete/{id}")
    public HttpEntity<?> deleteTypeSubject(@PathVariable Long id){
        Optional<TypeProduct> optionalTypeSubject = typeProductRepository.findById(id);
        if (optionalTypeSubject.isPresent()){
            typeProductRepository.deleteById(id);
            return ResponseEntity.status(202).body("Deleted");
        }else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }


    @PutMapping("/edit/{id}")
    public HttpEntity<?> editTypeSubject(@PathVariable Long id,@RequestBody TypeProduct typeProduct){
        Optional<TypeProduct> optionalTypeSubject = typeProductRepository.findById(id);
        if (optionalTypeSubject.isPresent()){
            TypeProduct typeProduct1 = optionalTypeSubject.get();
            typeProduct1.setName(typeProduct.getName());
            TypeProduct savedTypeProduct = typeProductRepository.save(typeProduct1);
            return ResponseEntity.status(202).body(savedTypeProduct);
        }else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }
}
