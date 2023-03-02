package io.getarrays.start_up.service;

import io.getarrays.start_up.entity.FileUpload;
import io.getarrays.start_up.entity.Product;
import io.getarrays.start_up.entity.TypeProduct;
import io.getarrays.start_up.payload.ProductDto;
import io.getarrays.start_up.repository.FileUploadRepository;
import io.getarrays.start_up.repository.ProductRepository;
import io.getarrays.start_up.repository.TypeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TypeProductRepository typeProductRepository;

    @Autowired
    FileUploadRepository fileUploadRepository;

    public HttpEntity<?> addProduct(ProductDto productDto) {
        Optional<TypeProduct> optionalTypeProduct = typeProductRepository.findById(productDto.getTypeProductId());
        List<FileUpload> files = new ArrayList<>();
        for (Long fileUpload : productDto.getFileUploads()) {
            files.add(fileUploadRepository.findById(fileUpload).get());
        }
        if (optionalTypeProduct.isPresent()){
            TypeProduct typeProduct = optionalTypeProduct.get();
            Product product1 = new Product(
                  productDto.getName(),
                    productDto.getPrice(),
                    productDto.getProduced(),
                    productDto.getBrand(),
                    productDto.getAbout(),
                    productDto.getSale(),
                    productDto.isStatus(),
                    typeProduct,
                    files

            );
            Product savedSubject = productRepository.save(product1);
            return ResponseEntity.status(202).body(savedSubject+" saved successfully");
        }
        return ResponseEntity.status(404).body("Not found");
    }


    public HttpEntity<?> getProduct() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.status(202).body(products);
    }


    public HttpEntity<?> getProductById(UUID id) {
        Optional<Product> optionalSubjects = productRepository.findById(id);
        if (optionalSubjects.isPresent()){
            Product subject = optionalSubjects.get();
            return ResponseEntity.status(202).body(subject);
        }else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }


    public HttpEntity<?> editProduct(UUID id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Optional<TypeProduct> optionalTypeProduct = typeProductRepository.findById(productDto.getTypeProductId());

        List<FileUpload> files = new ArrayList<>();
        for (Long fileUpload : productDto.getFileUploads()) {
            files.add(fileUploadRepository.findById(fileUpload).get());
        }

        if (optionalProduct.isPresent() && optionalTypeProduct.isPresent()){
            TypeProduct typeProduct = optionalTypeProduct.get();
            Product product = optionalProduct.get();

                    product.setName(productDto.getName());
                    product.setPrice(productDto.getPrice());
                    product.setProduced(productDto.getProduced());
                    product.setBrand(productDto.getBrand());
                    product.setAbout(productDto.getAbout());
                    product.setSale(productDto.getSale());
                    product.setStatus(productDto.isStatus());
                    product.setTypeProduct(typeProduct);
                    product.setFileUpload(files);

            Product savedSubject = productRepository.save(product);
            return ResponseEntity.status(202).body(savedSubject +" edit Successfully");
        }return ResponseEntity.status(404).body("Not Found");
    }

    public HttpEntity<?> deleteById(UUID id) {
        productRepository.deleteById(id);
        return ResponseEntity.status(202).body("Product Deleted");
    }
}
