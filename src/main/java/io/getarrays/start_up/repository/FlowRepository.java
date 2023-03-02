package io.getarrays.start_up.repository;

import io.getarrays.start_up.entity.Flow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowRepository extends JpaRepository<Flow,Long> {

    List<Flow> findAllByUserId(Long user_id);
    List<Flow> findByUserId(Long user_id);

}
