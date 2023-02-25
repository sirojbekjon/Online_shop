package io.getarrays.start_up.entity;

import io.getarrays.start_up.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flow extends AbstractEntity {

    private String name;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    private boolean archived=false;

    private boolean canceled = false;

    private boolean delivered = false;

    private boolean hold = false;

    private boolean neww = false;

    private boolean onway = false;

    private boolean ready = false;

    private Long visits_count;

}
