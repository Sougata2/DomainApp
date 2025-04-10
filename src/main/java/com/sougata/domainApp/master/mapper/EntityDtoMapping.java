package com.sougata.domainApp.master.mapper;

import com.sougata.domainApp.master.dto.CityDto;
import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.dto.StateDto;
import com.sougata.domainApp.master.entity.CityEntity;
import com.sougata.domainApp.master.entity.DistrictEntity;
import com.sougata.domainApp.master.entity.StateEntity;
import com.sougata.domainApp.shared.MasterDto;
import com.sougata.domainApp.shared.MasterEntity;
import lombok.Getter;

import java.util.Map;

@Getter
public class EntityDtoMapping {
    private final Map<Class<? extends MasterEntity>, Class<? extends MasterDto>> entityDtoMap;
    private final Map<Class<? extends MasterDto>, Class<? extends MasterEntity>> dtoEntityMap;

    public EntityDtoMapping() {
        entityDtoMap = Map.ofEntries(
                Map.entry(StateEntity.class, StateDto.class),
                Map.entry(DistrictEntity.class, DistrictDto.class),
                Map.entry(CityEntity.class, CityDto.class)
        );

        dtoEntityMap = Map.ofEntries(
                Map.entry(StateDto.class, StateEntity.class),
                Map.entry(DistrictDto.class, DistrictEntity.class),
                Map.entry(CityDto.class, CityEntity.class)
        );
    }

}
