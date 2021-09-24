package com.mehdi.storemanagement.repository;

import com.mehdi.storemanagement.model.StockHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {

    @Query(value = "select sh.* from stock_history sh " +
            "inner join stock s on sh.stock_id = s.id " +
            "where s.product_id = ?1 and  sh.stock_id in " +
            "(select distinct sh1.stock_id from stock_history sh1 order by sh1.stock_id limit ?2)" +
            "order by sh.date, sh.time desc limit ?3", nativeQuery = true)
    List<StockHistory> findByProductId(long productId, int locationLimit, int stockHistoryLimit);


    Page<StockHistory> findByDate(LocalDate date, Pageable pageable);

    Page<StockHistory> findByDateAndTime(LocalDate date, LocalTime time, Pageable paging);

    @Query("select sh from stockHistory sh where sh.stock.product.id = ?1 and sh.stock.location.id = ?2 " +
            "order by sh.date, sh.time desc")
    Page<StockHistory> findByProductIdAndLocation(long productId, long locationId, Pageable pageable);

}
