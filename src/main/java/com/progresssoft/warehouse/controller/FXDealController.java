package com.progresssoft.warehouse.controller;

import com.progresssoft.warehouse.model.dto.FXDealDTO;
import com.progresssoft.warehouse.service.FXDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fxdeals")
public class FXDealController {

    @Autowired
    FXDealService fxDealService;

    @GetMapping
    public List<FXDealDTO> getFxDeals(){
        return this.fxDealService.listFXDealDTOs();
    }

    @GetMapping("/{dealId}")
    public FXDealDTO getFxDeal(@PathVariable String dealId){
        return this.fxDealService.getFXDealDTObyDealId(dealId);
    }

    @PostMapping
    public FXDealDTO saveFxDeals(@RequestBody FXDealDTO dto){
        return this.fxDealService.saveFXDealDto(dto);
    }

    @PutMapping
    public FXDealDTO updateFxDeals(@RequestBody FXDealDTO dto){
        return this.fxDealService.updateFXDealDto(dto);
    }


}
