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

    private boolean archived;

    private boolean canceled;

    private boolean delivered;

    private boolean hold ;

    private boolean neww;

    private boolean onway;

    private boolean ready;

    private Long visits_count;
}
