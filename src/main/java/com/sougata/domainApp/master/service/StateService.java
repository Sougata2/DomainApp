package com.sougata.domainApp.master.service;

import com.sougata.domainApp.master.dto.StateDto;
import com.sougata.domainApp.master.entity.StateEntity;
import com.sougata.domainApp.master.repository.StateRepository;
import com.sougata.domainApp.shared.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StateService {
    private final StateRepository stateRepository;

    public List<StateDto> findAll() {
        return stateRepository.findAll()
                .stream()
                .map(
                        state -> Mapper.mapToDto(state, StateDto.class)
                ).collect(Collectors.toList());
    }

    public StateDto findById(UUID id) {
        return stateRepository.findById(id)
                .map(
                        state -> Mapper.mapToDto(state, StateDto.class)
                ).orElse(null);
    }

    public StateDto create(StateDto stateDto) {
        StateEntity entity = Mapper.mapToEntity(stateDto, StateEntity.class);
        if (entity == null) {
            return null;
        }
        return Mapper.mapToDto(stateRepository.save(entity), StateDto.class);
    }

    public StateDto update(StateDto stateDto) {
        Optional<StateEntity> entity = stateRepository.findById(stateDto.getId());
        if (entity.isEmpty()) {
            return null;
        }
        StateEntity updated = Mapper.merge(entity.get(), stateDto);
        StateEntity savedEntity = stateRepository.save(updated);
        return Mapper.mapToDto(savedEntity, StateDto.class);
    }

    public void delete(StateDto dto) {
        Optional<StateEntity> entity = stateRepository.findById(dto.getId());
        if (entity.isEmpty()) {
            return;
        }
        stateRepository.delete(entity.get());
    }
}
