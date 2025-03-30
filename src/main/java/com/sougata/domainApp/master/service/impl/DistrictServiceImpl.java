package com.sougata.domainApp.master.service.impl;

import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.entity.CityEntity;
import com.sougata.domainApp.master.entity.DistrictEntity;
import com.sougata.domainApp.master.mapper.CityMapper;
import com.sougata.domainApp.master.mapper.DistrictMapper;
import com.sougata.domainApp.master.repository.CityRepository;
import com.sougata.domainApp.master.repository.DistrictRepository;
import com.sougata.domainApp.master.service.CityService;
import com.sougata.domainApp.master.service.DistrictService;
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
        DistrictEntity districtEntity = DistrictMapper.toEntity(districtDto, null);

        // Save district first
        DistrictEntity savedDistrict = districtRepository.save(districtEntity);

        // Save cities and associate with the saved district
        List<CityEntity> cities = districtDto.cities().stream()
                .map(cityDto -> {
                    CityEntity city = CityMapper.toEntity(cityDto, savedDistrict);
                    return cityRepository.save(city);
                })
                .toList();

        savedDistrict.setCities(cities);

        return DistrictMapper.toDto(savedDistrict);
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
        if (dbDistEntity.isEmpty()) return null;

        // updating the fields not the related entities
        if (districtDto.strDistName() != null) {
            dbDistEntity.get().setStrDistName(districtDto.strDistName());
        }
        if (districtDto.isActive() != null) {
            dbDistEntity.get().setIsActive(districtDto.isActive());
        }

        // update the cities
        if (districtDto.cities() != null) {
            mergeCityList(districtDto.cities(), dbDistEntity.get().getCities(), dbDistEntity.get());
        }

        // save the district
        DistrictEntity savedDistrict = districtRepository.save(dbDistEntity.get());

        return DistrictMapper.toDto(savedDistrict);
    }

    protected void mergeCityList(List<DistrictDto.CityDto> cityDtoList, List<CityEntity> dbCityList, DistrictEntity district) {
        // map for faster look up
        Map<UUID, DistrictDto.CityDto> map = new HashMap<>();
        for (DistrictDto.CityDto cityDto : cityDtoList) {
            if (cityDto.cityId() == null) {
                // create city if not present
                DistrictDto.CityDto createdCityDto = cityService.createCity(cityDto, district);
                addCity(createdCityDto, dbCityList, district);
                map.put(createdCityDto.cityId(), createdCityDto);
            } else {
                map.put(cityDto.cityId(), cityDto);
            }
        }

        for (CityEntity cityEntity : dbCityList) {
            if (map.containsKey(cityEntity.getCityId())) {
                DistrictDto.CityDto cityDto = map.get(cityEntity.getCityId());
                cityEntity.setStrCityName(cityDto.strCityName());
                cityEntity.setIsActive(cityDto.isActive());
            } else {
                cityService.deleteCity(cityEntity.getCityId());
            }
        }

        // set the updated list to the district entity
        district.setCities(dbCityList);
    }

    private void addCity(DistrictDto.CityDto cityDto, List<CityEntity> dbCityList, DistrictEntity district) {
        CityEntity entity = CityMapper.toEntity(cityDto, district);
        dbCityList.add(entity);
    }

    @Override
    @Transactional
    public DistrictDto delete(DistrictDto district) {
        Optional<DistrictEntity> entity = districtRepository.findById(district.distId());
        if (entity.isEmpty()) return null;
        entity.get().setIsActive(0);
        DistrictEntity saved = districtRepository.save(entity.get());
        return DistrictMapper.toDto(saved);
    }
}