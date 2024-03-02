package com.progresssoft.warehouse.service;

import com.progresssoft.warehouse.model.dto.FXDealDTO;
import com.progresssoft.warehouse.model.entity.FXDeal;
import com.progresssoft.warehouse.repository.FxDealRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FXDealService {

    final  FxDealRepository fxDealRepository;
    Set<String> currencies = Set.of("USD", "JOD", "EUR");

    public FXDealService(FxDealRepository fxDealRepository) {
        this.fxDealRepository = fxDealRepository;
    }

    public FXDealDTO getFXDealDTObyDealId(String dealId){
        Optional<FXDeal> fxDeal = this.fxDealRepository.findByDealId(dealId);
        return fxDeal.map(this::toDto).orElse(null);
    }

    public FXDealDTO saveFXDealDto(FXDealDTO dto){
        String status = validateNewFXDealDTO(dto);
        if (status.equals("Success")){
            FXDeal entity = this.fxDealRepository.save(toEntity(dto));
            FXDealDTO savedDto = toDto(entity);
            savedDto.setStatus(status);
            return savedDto;
        }
        dto.setStatus(status);
        return dto;
    }

    public List<FXDealDTO> listFXDealDTOs(){
        List<FXDealDTO> dtos = new ArrayList<>();
        for(FXDeal entity : this.fxDealRepository.findAll() ){
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public FXDealDTO updateFXDealDto(FXDealDTO dto){
        if (!isFoundDealId(dto.getDealId())){
            dto.setStatus("Deal ID not exist");
        }
        else {
            String status = validateUpdateFXDealDTO(dto);
            if (status.equals("Success")){
                FXDeal updatedEntity = toEntity(dto);
                Optional<FXDeal> entity = this.fxDealRepository.findByDealId(dto.getDealId());
                updatedEntity.setId(entity.get().getId());

                FXDealDTO updatedDTO = toDto(this.fxDealRepository.save(updatedEntity));
                updatedDTO.setStatus(status);
                return updatedDTO;
            }
            dto.setStatus(status);
        }
        return dto;
    }

    private String validateNewFXDealDTO(FXDealDTO dto){
        if (isFoundDealId(dto.getDealId())) return "Duplicate Deal ID";
        return validateUpdateFXDealDTO(dto);
    }

    private String validateUpdateFXDealDTO(FXDealDTO dto){
        if (!isValidCurrency(dto.getToCurrency())) return "Not Valid ToCurrency";
        if (!isValidCurrency(dto.getFromCurrency())) return "Not Valid FromCurrency";
        if (!isValidTimestamp(dto.getDealTimestamp())) return "Not Valid Time stamp format yyyy-MM-dd'T'HH:mm:ss";
        if (!isValidDealAmount(dto.getDealAmount())) return "Not Valid Deal Amount";
        return "Success";
    }

    private boolean isFoundDealId(String dealId){
        return fxDealRepository.existsByDealId(dealId);
    }

    private boolean isValidCurrency(String currency){
        return currencies.contains(currency);
    }

    private boolean isValidDealAmount(Double dealAmount){
        return dealAmount > 0 ;
    }

    private boolean isValidTimestamp(String timestampString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime.parse(timestampString, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private FXDealDTO toDto(FXDeal entity){

        return FXDealDTO.builder().dealId(entity.getDealId()).
                fromCurrency(entity.getFromCurrency()).
                toCurrency(entity.getToCurrency()).
                dealTimestamp(entity.getDealTimestamp()).
                dealAmount(entity.getDealAmount()).build();
    }

    private FXDeal toEntity (FXDealDTO dto){

        return FXDeal.builder().dealId(dto.getDealId()).
                fromCurrency(dto.getFromCurrency()).
                toCurrency(dto.getToCurrency()).
                dealTimestamp(dto.getDealTimestamp()).
                dealAmount(dto.getDealAmount()).build();
    }

}
