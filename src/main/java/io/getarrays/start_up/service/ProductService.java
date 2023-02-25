package io.getarrays.start_up.service;

import io.getarrays.start_up.entity.Product;
import io.getarrays.start_up.entity.TypeProduct;
import io.getarrays.start_up.payload.ProductDto;
import io.getarrays.start_up.repository.ProductRepository;
import io.getarrays.start_up.repository.TypeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TypeProductRepository typeProductRepository;

    public HttpEntity<?> addProduct(ProductDto productDto) {
        Optional<TypeProduct> optionalTypeProduct = typeProductRepository.findById(productDto.getTypeProductId());
        if (optionalTypeProduct.isPresent()){
            TypeProduct typeProduct = optionalTypeProduct.get();
//            Product product1 = new Product(
//                  productDto.getName(),
//                    productDto.getPrice(),
//                    productDto.getProduced(),
//                    productDto.getBrand(),
//                    productDto.getAbout(),
//                    productDto.getSale(),
//                    productDto.getTypeProductId()
//            );
//            Product savedSubject = productRepository.save(product1);
//            return ResponseEntity.status(202).body(savedSubject+" saved successfully");
        }
        return ResponseEntity.status(404).body("Not found");
    }


    public HttpEntity<?> getProduct() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.status(202).body(products);
    }


    public HttpEntity<?> getProductById(Long id) {
        Optional<Product> optionalSubjects = productRepository.findById(id);
        if (optionalSubjects.isPresent()){
            Product subject = optionalSubjects.get();
            return ResponseEntity.status(202).body(subject);
        }else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }


    public HttpEntity<?> editProduct(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Optional<TypeProduct> optionalTypeProduct = typeProductRepository.findById(productDto.getTypeProductId());
        if (optionalProduct.isPresent() && optionalTypeProduct.isPresent()){
            TypeProduct typeProduct = optionalTypeProduct.get();
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setTypeProduct(typeProduct);
            Product savedSubject = productRepository.save(product);
            return ResponseEntity.status(202).body(savedSubject +" edit Successfully");
        }return ResponseEntity.status(404).body("Not Found");
    }
}
