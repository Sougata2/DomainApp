package com.sougata.domainApp.master.service;

import com.sougata.domainApp.master.dto.CountryDto;
import com.sougata.domainApp.master.entity.CountryEntity;
import com.sougata.domainApp.master.repository.CountryRepository;
import com.sougata.domainApp.shared.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<CountryDto> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(
                        entity ->
                                Mapper.mapToDto(entity, CountryDto.class)
                ).toList();
    }

    public CountryDto getCountryById(UUID id) {
        return countryRepository.findById(id)
                .map(
                        entity -> Mapper.mapToDto(entity, CountryDto.class)
                ).orElse(null);
    }

    public CountryDto updateCountry(CountryDto countryDto) {
        Optional<CountryEntity> dbEntity = countryRepository.findById(countryDto.getId());
        if (dbEntity.isPresent()) {
            CountryEntity mergedEntity = Mapper.merge(dbEntity.get(), countryDto);
            CountryEntity savedEntity = countryRepository.save(mergedEntity);
            return Mapper.mapToDto(savedEntity, CountryDto.class);
        }
        return null;
    }

    public CountryDto createCountry(CountryDto dto) {
        CountryEntity entity = Mapper.mapToEntity(dto, CountryEntity.class);
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }
        return Mapper.mapToDto(countryRepository.save(entity), CountryDto.class);
    }

    public void deleteCountry(UUID id) {
        countryRepository.deleteById(id);
    }
}
