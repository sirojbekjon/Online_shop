package io.getarrays.start_up.entity;

import io.getarrays.start_up.entity.template.AbstractEntity;
import io.getarrays.start_up.entity.template.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.awt.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product extends ProductEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String price;

    private String produced;

    @Column(nullable = false)
    private String brand;

    private String about;

    private String sale;

    private boolean status; //yangi so'ngi mahsulotlar holati

    @ManyToOne
    private TypeProduct typeProduct;

    @OneToMany
    private List<FileUpload> fileUpload;
}
