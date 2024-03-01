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
    private String dealId;
    private String fromCurrency;
    private String toCurrency;
    private Long dealTimestamp;
    private Double dealAmount;

    public static FXDeal toEntity (FXDealDTO dto){

        return FXDeal.builder().dealId(dto.getDealId()).
                fromCurrency(dto.getFromCurrency()).
                toCurrency(dto.getToCurrency()).
                dealTimestamp(dto.getDealTimestamp()).
                dealAmount(dto.getDealAmount()).build();
    }
}
