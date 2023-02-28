package io.getarrays.start_up.payload;

import io.getarrays.start_up.entity.Product;
import io.getarrays.start_up.entity.User;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class FlowDto {

    private String name;

    private Long product;

    private Long visits_count;
}
