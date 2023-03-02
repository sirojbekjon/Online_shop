package io.getarrays.start_up.payload;

import io.getarrays.start_up.entity.Product;
import io.getarrays.start_up.entity.User;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
public class FlowDto {

    private String name;

    private UUID product;

    private boolean archived;

    private boolean canceled;

    private boolean delivered;

    private boolean hold ;

    private boolean neww;

    private boolean onway;

    private boolean ready;
}
