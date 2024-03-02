package com.progresssoft.warehouse.model.entity;

import com.progresssoft.warehouse.model.dto.FXDealDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class FXDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String dealId;
    @Column(nullable = false)
    private String fromCurrency;
    @Column(nullable = false)
    private String toCurrency;
    @Column(nullable = false)
    private String dealTimestamp;
    @Column(nullable = false)
    private Double dealAmount;

}
