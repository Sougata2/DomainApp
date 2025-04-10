package com.sougata.domainApp.master.controller;

import com.sougata.domainApp.master.dto.StateDto;
import com.sougata.domainApp.master.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/state")
public class StateController {
    private final StateService service;

    @GetMapping
    public ResponseEntity<List<StateDto>> findAllStates() {
        try {
            return ResponseEntity.ok(service.findAllStates());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<StateDto> createState(@RequestBody StateDto stateDto) {
        try {
            return ResponseEntity.ok(service.createState(stateDto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
