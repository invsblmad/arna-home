package com.inai.arna.repository;

import com.inai.arna.dto.response.DeliveryView;
import com.inai.arna.model.delivery.DeliveryCompany;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryCompanyRepository extends JpaRepository<DeliveryCompany, Integer> {
    @EntityGraph(value = "DeliveryCompany.deliveries")
    List<DeliveryView> findAllBy();
}
