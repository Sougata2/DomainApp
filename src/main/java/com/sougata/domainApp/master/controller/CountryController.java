package com.sougata.domainApp.master.controller;

import com.sougata.domainApp.master.dto.CountryDto;
import com.sougata.domainApp.master.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @PostMapping
    public ResponseEntity<CountryDto> createCountry(@RequestBody CountryDto countryDto) {
        return ResponseEntity.ok(countryService.createCountry(countryDto));
    }

    @PutMapping("/update")
    public ResponseEntity<CountryDto> updateCountry(@RequestBody CountryDto countryDto) {
        return ResponseEntity.ok(countryService.updateCountry(countryDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCountry(@RequestBody UUID id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}
