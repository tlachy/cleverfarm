package cz.cleverfarm.service;

import cz.cleverfarm.entity.Farm;
import cz.cleverfarm.entity.Field;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static cz.cleverfarm.CZPolygon.CZ_POLYGON;
import static java.lang.Long.MAX_VALUE;

@Service
public class FarmService {

    //TODO replace with DB
    public static final List<Farm> farms = new ArrayList<>();


    
    public Optional<Farm> getFarm(@NotNull UUID uuid) {
        return farms.stream()
                .filter(farm -> farm.getUuid().equals(uuid))
                .findFirst();
    }
    
    public Farm saveFarm(Farm farm) {
        farm.setUuid(UUID.randomUUID());
        farm.setId(ThreadLocalRandom.current().nextLong(MAX_VALUE));
        farms.add(farm);
        return farm;
    }

    public void addFieldToFarm(Field field) {
        Farm farm = findFarmByUuid(field.getFarm().getUuid());
        farm.getFields().add(field);
    }

    private Farm findFarmByUuid(UUID uuid) {
        Optional<Farm> farm = farms.stream()
                .filter(farmInList -> farmInList.getUuid().equals(uuid))
                .findFirst();

        if (farm.isEmpty()) {
            throw new IllegalArgumentException("Farm with UUID does not exists" + uuid.toString());
        }
        return farm.get();
    }

    public boolean overlapsOtherField(Polygon polygon){
        for(Farm farm : farms){
            for(Field field : farm.getFields()){
                if(polygon.overlaps(field.getPolygon())){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInCzechRepublic(Polygon polygon){
        return CZ_POLYGON.contains(polygon);
    }
}
