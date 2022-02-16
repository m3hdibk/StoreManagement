package com.mehdi.storemanagement.repository;

import com.mehdi.storemanagement.model.Stock;
import com.mehdi.storemanagement.model.dto.StockSumQuantity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("select s from stock s where s.product.id = ?1 and s.location.id = ?2")
    Optional<Stock> findByProductAndLocation(long productId, long locationId);

    @Query("select sum(s.quantity) as sumQuantity, p as product from stock s join product p on p.id = s.product.id " +
            "group by p.id")
    Page<StockSumQuantity> findStockByWithQuantitySum(Pageable pageable);

    @Query("select sum(s.quantity) as sumQuantity, p as product from stock s join product p on p.id = s.product.id " +
            "where p.productCode like ?1% or p.upc like ?1%  or p.name like ?1% group by p.id")
    Page<StockSumQuantity> findStockByProductWithQuantitySum(String searchInput, Pageable pageable);

    @Query("select s from stock s where s.product.id = ?1")
    Page<Stock> findStocksByProductId(long productId, Pageable pageable);

    @Query("select s from stock s where " +
            "s.location.id = (select l.id from scheme l where l.defaultItem = true and l.type = ?2) and " +
            "s.product.productCode like ?1% or s.product.upc like ?1%  or s.product.name like ?1%")
    Page<Stock> findStocksByProductAndDefaultType(String searchInput, int type, Pageable pageable);

    @Query("select sum(s.quantity) as sumQuantity from stock s where s.product.id = ?1")
    Integer getQuantitySum(long productId);

}
