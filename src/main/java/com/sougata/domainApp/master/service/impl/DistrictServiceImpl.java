package com.sougata.domainApp.master.service.impl;

import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.dto.MasterDTO;
import com.sougata.domainApp.master.entity.CityEntity;
import com.sougata.domainApp.master.entity.DistrictEntity;
import com.sougata.domainApp.master.entity.MasterEntity;
import com.sougata.domainApp.master.mapper.DistrictMapper;
import com.sougata.domainApp.master.repository.CityRepository;
import com.sougata.domainApp.master.repository.DistrictRepository;
import com.sougata.domainApp.master.service.CityService;
import com.sougata.domainApp.master.service.DistrictService;
import com.sougata.domainApp.shared.Mapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;
    private final CityService cityService;
    private final EntityManager entityManager;

    private final Map<Class<? extends MasterDTO>, Class<? extends MasterEntity>> relationMap = Map.ofEntries(
            Map.entry(DistrictDto.class, DistrictEntity.class),
            Map.entry(DistrictDto.CityDto.class, CityEntity.class)
    );

    @Override
    public List<DistrictDto> findAllDistrictsWithCity() {
        return districtRepository
                .findAllDistrictsWithCity()
                .stream()
                .map(DistrictMapper::toDto).toList();
    }

    @Override
    @Transactional
    public DistrictDto findDistrictWithActiveCities(UUID id) {
        Optional<DistrictEntity> districtEntity = districtRepository.findDistrictWithCity(id);
        return districtEntity.map(DistrictMapper::toDto)
                .orElse(null);
    }


    @Override
    @Transactional
    public DistrictDto create(DistrictDto districtDto) {
//        DistrictEntity districtEntity = DistrictMapper.toEntity(districtDto, null);
//
//        // Save district first
//        DistrictEntity savedDistrict = districtRepository.save(districtEntity);
//
//        // Save cities and associate with the saved district
//        List<CityEntity> cities = districtDto.cities().stream()
//                .map(cityDto -> {
//                    CityEntity city = CityMapper.toEntity(cityDto, savedDistrict);
//                    return cityRepository.save(city);
//                })
//                .toList();
//
//        savedDistrict.setCities(cities);

        // NEW CODE
        DistrictEntity entity = Mapper.mapToEntity(districtDto, DistrictEntity.class, relationMap);

        // saving the related entities.
        cityRepository.saveAll(entity.getCities());
        DistrictEntity saved = districtRepository.save(entity);

        return DistrictMapper.toDto(saved);
    }

    /**
     * Main idea :
     * <br/>
     * 1. update the fields that are not a relationship
     * <br/>
     * 2. edit the list
     * <br/>
     * **** a. create a map<UUID, city-dto> [for faster look up purpose]
     * <br/>
     * **** b. loop through city-dto-list
     * <br/>
     * **** c. if city-dto does not have id then create a new city with that name and associate district
     * <br/>
     * **** d. add the new city to district's city list
     * <br/>
     * **** e. if city-dto does have id then put it in map
     * <br/>
     * **** f. loop through city-entity-list
     * <br/>
     * **** g. if id of city-entity is present in the map, then only update fields not the relationship
     * <br/>
     * **** h. if not set the isActive field of the city-entity to 0
     * <br/>
     * **** i. finally set the new list to district city-list.
     * <br/>
     * 3. save the entity
     */
    @Override
    @Transactional
    public DistrictDto update(DistrictDto districtDto) {
        // get the existing district
        Optional<DistrictEntity> dbDistEntity = districtRepository.findById(districtDto.distId());
        if (dbDistEntity.isEmpty()) {
            throw new RuntimeException("District not found!");
        }

        Collection<DistrictDto.CityDto> dtoCities = districtDto.cities();
        Collection<CityEntity> entityCities = dbDistEntity.get().getCities();

        DistrictEntity mergedDistrictEntity = Mapper.merge(districtDto, dbDistEntity.get(), (dto, entity) -> {
            // merge logic for one relation.
            return mergeCityList(dtoCities, entityCities, dbDistEntity.get());
        });

        // save the district
        DistrictEntity savedDistrict = districtRepository.save(mergedDistrictEntity);

        // clear the persistence cache for getting the updated district entity
        // not the cached one.
        entityManager.flush();
        entityManager.clear();

        return DistrictMapper.toDto(savedDistrict);
    }

    // special function to merge city relation list.
    private List<CityEntity> mergeCityList(Collection<DistrictDto.CityDto> dtoCities, Collection<CityEntity> entityCities, DistrictEntity districtEntity) {
        Map<UUID, DistrictDto.CityDto> map = new HashMap<>();
        List<CityEntity> updatedEntityCities = new ArrayList<>();
        for (DistrictDto.CityDto cityDto : dtoCities) {
            if (cityDto.cityId() == null) {
                cityDto = cityService.createCity(cityDto, districtEntity);
                map.put(cityDto.cityId(), cityDto);
            } else {
                map.put(cityDto.cityId(), cityDto);
            }
            updatedEntityCities.add(Mapper.mapToEntity(cityDto, CityEntity.class, relationMap));
        }

        for (CityEntity cityEntity : entityCities) {
            if (map.containsKey(cityEntity.getCityId())) {
                DistrictDto.CityDto cityDto = map.get(cityEntity.getCityId());
                if (cityDto.strCityName() != null) {
                    cityEntity.setStrCityName(cityDto.strCityName());
                }
                if (cityDto.isActive() != null) {
                    cityEntity.setIsActive(cityDto.isActive());
                }
            } else {
                cityService.deleteCity(cityEntity.getCityId());
            }
            updatedEntityCities.add(cityEntity);
        }
        return updatedEntityCities;
    }

    @Override
    @Transactional
    public DistrictDto delete(DistrictDto district) {
        Optional<DistrictEntity> entity = districtRepository.findById(district.distId());
        if (entity.isEmpty()) {
            throw new RuntimeException("District with id " + district.distId() + " not found");
        }
        entity.get().setIsActive(0);
        DistrictEntity saved = districtRepository.save(entity.get());
        return DistrictMapper.toDto(saved);
    }
}