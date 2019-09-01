package cz.cleverfarm.controller;

import cz.cleverfarm.dto.FarmDtoIn;
import cz.cleverfarm.dto.FarmDtoOut;
import cz.cleverfarm.dto.FieldDtoIn;
import cz.cleverfarm.entity.Farm;
import cz.cleverfarm.entity.Field;
import cz.cleverfarm.mapper.FarmMapper;
import cz.cleverfarm.mapper.FieldMapper;
import cz.cleverfarm.service.FarmService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.Optional;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@RestController
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
public class FarmController {

    FarmService farmService;
    FarmMapper farmMapper;
    FieldMapper fieldMapper;

    @GetMapping("/farm/{uuid}")
    public FarmDtoOut getFarm(@PathVariable @NotNull UUID uuid) {

        Optional<Farm> farm = farmService.getFarm(uuid);
        return farm.isPresent()
                ? farmMapper.toDto(farm.get())
                : null;
    }

    @PostMapping("/farm")
    public FarmDtoOut saveFarm(@Valid @RequestBody FarmDtoIn farmDtoIn) {
        Farm farm = farmMapper.toEntity(farmDtoIn);
        Farm savedFarm = farmService.saveFarm(farm);
        return farmMapper.toDto(savedFarm);
    }

    @PostMapping("/farm/{uuid}/field")
    public void addFieldToFarm(@Valid @RequestBody FieldDtoIn fieldDtoIn) {
        Field field = fieldMapper.toEntity(fieldDtoIn);
        farmService.addFieldToFarm(field);
    }

}
