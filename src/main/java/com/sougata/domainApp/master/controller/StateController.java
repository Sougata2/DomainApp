package com.sougata.domainApp.master.controller;

import com.sougata.domainApp.master.dto.StateDto;
import com.sougata.domainApp.master.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/state")
@RequiredArgsConstructor
public class StateController {
    private final StateService stateService;

    @GetMapping
    public ResponseEntity<List<StateDto>> getStates() {
        List<StateDto> states = stateService.findAll();
        return ResponseEntity.ok(states);
    }

    @PostMapping
    public ResponseEntity<StateDto> createState(@RequestBody StateDto stateDto) {
        return ResponseEntity.ok(stateService.create(stateDto));
    }

    @PutMapping
    public ResponseEntity<StateDto> updateState(@RequestBody StateDto stateDto) {
        return ResponseEntity.ok(stateService.update(stateDto));
    }

    @DeleteMapping
    public ResponseEntity<StateDto> deleteState(@RequestBody StateDto stateDto) {
        stateService.delete(stateDto);
        return ResponseEntity.noContent().build();
    }
}
