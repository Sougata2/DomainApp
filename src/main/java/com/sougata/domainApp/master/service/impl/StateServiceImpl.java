package com.sougata.domainApp.master.service.impl;

import com.sougata.domainApp.master.dto.StateDto;
import com.sougata.domainApp.master.entity.StateEntity;
import com.sougata.domainApp.master.mapper.EntityDtoMapping;
import com.sougata.domainApp.master.repository.StateRepository;
import com.sougata.domainApp.master.service.StateService;
import com.sougata.domainApp.shared.RelationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {
    private final StateRepository repository;
    private final EntityDtoMapping entityDtoMapping;

    @Override
    public List<StateDto> findAllStates() {
        return repository.findAllActiveStates().stream()
                .map(e ->
                        (StateDto) RelationMapper.mapToDto(e, entityDtoMapping.getEntityDtoMap())
                ).toList();
    }

    @Override
    @Transactional
    public StateDto createState(StateDto stateDto) {
        StateEntity entity = (StateEntity) RelationMapper.mapToEntity(stateDto, entityDtoMapping.getDtoEntityMap());
        StateEntity savedEntity = repository.save(entity);
        return (StateDto) RelationMapper.mapToDto(savedEntity, entityDtoMapping.getEntityDtoMap());
    }
}
