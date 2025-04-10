package com.sougata.domainApp.master.service;

import com.sougata.domainApp.master.dto.StateDto;

import java.util.List;

public interface StateService {
    List<StateDto> findAllStates();

    StateDto createState(StateDto stateDto);
}
