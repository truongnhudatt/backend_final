package com.example.backend_final.repository;

import com.example.backend_final.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail,Long> {
//    @Modifying
//    @Query("DELETE FROM OrderDetail ord WHERE ord.id = ?1 AND ord.order.user.username = ?2")
//    void deleteOrderDetail(Long orderDetailId, String username);
    @Modifying
    @Query("DELETE FROM OrderDetail ord WHERE ord.id = ?1")
    void deleteOrderDetail(Long orderDetailId);
}
