package com.ecombackend.ecombackend.repository;

import com.ecombackend.ecombackend.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    // Find orders by userId
    List<Order> findByUserId(String userId);

    // Find orders by buyer (User object)
    List<Order> findByBuyer_Id(String userId);

}
