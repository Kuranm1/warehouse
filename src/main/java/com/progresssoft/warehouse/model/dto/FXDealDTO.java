package com.progresssoft.warehouse.model.dto;

import com.progresssoft.warehouse.model.entity.FXDeal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FXDealDTO {
    private String dealId;
    private String fromCurrency;
    private String toCurrency;
    private Long dealTimestamp;
    private Double dealAmount;

    public static FXDealDTO toDto(FXDeal entity){

        return FXDealDTO.builder().dealId(entity.getDealId()).
                fromCurrency(entity.getFromCurrency()).
                toCurrency(entity.getToCurrency()).
                dealTimestamp(entity.getDealTimestamp()).
                dealAmount(entity.getDealAmount()).build();
    }
}
