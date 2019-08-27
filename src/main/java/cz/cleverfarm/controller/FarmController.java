package cz.cleverfarm.controller;

import cz.cleverfarm.entity.Farm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FarmController {

    public static final List<Farm> farms = new ArrayList<>();


    @PostMapping("/farm")
    public void saveFarm(@Valid Farm farm){

    }

    @PostMapping("/farm/{guid}/")
    public void addFieldToFarm(@Valid Farm farm){

    }
}
