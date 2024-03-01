package com.progresssoft.warehouse.service;

import com.progresssoft.warehouse.model.dto.FXDealDTO;
import com.progresssoft.warehouse.model.entity.FXDeal;
import com.progresssoft.warehouse.repository.FxDealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FXDealService {

    final  FxDealRepository fxDealRepository;

    public FXDealService(FxDealRepository fxDealRepository) {
        this.fxDealRepository = fxDealRepository;
    }

    public FXDealDTO getFXDealDTObyDealId(String dealId){
        Optional<FXDeal> fxDeal = this.fxDealRepository.findByDealId(dealId);
        return fxDeal.map(FXDealDTO::toDto).orElse(null);
    }

    public FXDealDTO saveFXDealDto(FXDealDTO dto){
        FXDeal entity = this.fxDealRepository.save(FXDeal.toEntity(dto));
        return FXDealDTO.toDto(entity);
    }

    public List<FXDealDTO> listFXDealDTOs(){
        List<FXDealDTO> dtos = new ArrayList<>();
        for(FXDeal entity : this.fxDealRepository.findAll() ){
            dtos.add(FXDealDTO.toDto(entity));
        }
        return dtos;
    }

    public FXDealDTO updateFXDealDto(FXDealDTO dto){
        Optional<FXDeal> entity = this.fxDealRepository.findByDealId(dto.getDealId());
        if (entity.isPresent()){
            FXDeal updatedEntity = FXDeal.toEntity(dto);
            updatedEntity.setId(entity.get().getId());
            return FXDealDTO.toDto(this.fxDealRepository.save(updatedEntity));
        }
        return null;
    }
}
