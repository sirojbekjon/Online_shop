package io.getarrays.start_up.repository;

import io.getarrays.start_up.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
