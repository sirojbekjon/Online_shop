package io.getarrays.start_up.controller;

import io.getarrays.start_up.payload.ProductDto;
import io.getarrays.start_up.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @GetMapping("/get")
    public HttpEntity<?> getProduct(){
        return productService.getProduct();
    }


    @GetMapping("/get/{id}")
    public HttpEntity<?> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }


    @PutMapping("/edit/{id}")
    public HttpEntity<?>editProduct(@PathVariable Long id,@RequestBody ProductDto productDto){
        return productService.editProduct(id, productDto);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteById(@PathVariable Long id){
        return productService.deleteById(id);
    }
}
