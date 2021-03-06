package com.mehdi.storemanagement.repository;

import com.mehdi.storemanagement.model.Product;
import com.mehdi.storemanagement.model.dto.StockSumQuantity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByProductCodeStartsWith(String productCode, Pageable pageable);

}
