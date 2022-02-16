package com.mehdi.storemanagement.repository;

import com.mehdi.storemanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    int countAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    @Query("SELECT COALESCE(sum(o.amount), 0) FROM orders o where o.date between ?1 and ?2")
    double sumAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
