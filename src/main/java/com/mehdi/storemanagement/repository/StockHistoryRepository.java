package com.mehdi.storemanagement.repository;

import com.mehdi.storemanagement.model.StockHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;


public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {

    @Query(value = "select sh from stockHistory sh where sh.stock.id = ?1")
    Page<StockHistory> findByStockId(long stockId, Pageable pageable);


    Page<StockHistory> findByDate(LocalDate date, Pageable pageable);

    Page<StockHistory> findByDateAndTime(LocalDate date, LocalTime time, Pageable paging);
}
