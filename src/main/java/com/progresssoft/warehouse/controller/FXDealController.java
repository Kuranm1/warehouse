package com.progresssoft.warehouse.controller;

import com.progresssoft.warehouse.model.dto.FXDealDTO;
import com.progresssoft.warehouse.service.FXDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fxdeals")
public class FXDealController {

    @Autowired
    FXDealService fxDealService;

    @GetMapping
    public ResponseEntity<List<FXDealDTO>> getFxDeals(){
        List<FXDealDTO> dtos = new ArrayList<>();
        try{
            dtos = this.fxDealService.listFXDealDTOs();
            return new ResponseEntity<>(dtos, HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(dtos, HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    @GetMapping("/{dealId}")
    public ResponseEntity<FXDealDTO> getFxDeal(@PathVariable String dealId){
        FXDealDTO dto = new FXDealDTO();
        try {
            dto = this.fxDealService.getFXDealDTObyDealId(dealId);
            HttpStatus status = (dto == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
            return new ResponseEntity<>(dto, status);
        }
        catch (Exception e){
            dto.setStatus("Error occurred on DB operation");
            return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    @PostMapping
    public ResponseEntity<FXDealDTO> saveFxDeals(@RequestBody FXDealDTO dto){
        try {
            FXDealDTO savedDto = fxDealService.saveFXDealDto(dto);
            HttpStatus status = (savedDto.getStatus().equals("Success")) ? HttpStatus.OK : HttpStatus.UNPROCESSABLE_ENTITY;
            return new ResponseEntity<>(savedDto, status) ;
        }
        catch (Exception e)
        {
            dto.setStatus("Error occurred on DB operation");
            return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    @PutMapping
    public ResponseEntity<FXDealDTO> updateFxDeals(@RequestBody FXDealDTO dto){
        try {
            FXDealDTO updatedDto = fxDealService.updateFXDealDto(dto);
            HttpStatus status = (updatedDto.getStatus().equals("Success")) ? HttpStatus.OK : HttpStatus.UNPROCESSABLE_ENTITY;
            return new ResponseEntity<>(updatedDto, status) ;
        }
        catch (Exception e)
        {
            dto.setStatus("Error occurred on DB operation");
            return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }


}
