package io.getarrays.start_up.repository;

import io.getarrays.start_up.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    List<Client> findByFlowId(Long flow_id);

}
