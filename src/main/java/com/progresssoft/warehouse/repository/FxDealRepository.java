package com.progresssoft.warehouse.repository;
import com.progresssoft.warehouse.model.entity.FXDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FxDealRepository extends JpaRepository<FXDeal,Integer> {
    Optional<FXDeal> findByDealId(String dealId);
}
