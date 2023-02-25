package io.getarrays.start_up.payload;

import lombok.Data;

import java.awt.*;
import java.util.List;

@Data
public class ProductDto {

    private String name;

    private String price;

    private String produced;

    private String brand;

    private TextArea about;

    private String sale;

    private boolean status;

    private Long typeProductId;

    private List<Long> fileUploads;
}
