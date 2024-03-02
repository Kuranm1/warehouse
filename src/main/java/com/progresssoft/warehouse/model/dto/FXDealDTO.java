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
    private String dealTimestamp;
    private Double dealAmount;
    private String status;

}
