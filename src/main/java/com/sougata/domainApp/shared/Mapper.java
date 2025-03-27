package com.sougata.domainApp.shared;

import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Mapper {
    public static <D, E> D mapToDto(E entity, Class<D> dtoClass) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            for (Field entityField : entity.getClass().getDeclaredFields()) {
                entityField.setAccessible(true);
                try {
                    Field dtoField = dtoClass.getDeclaredField(entityField.getName());
                    dtoField.setAccessible(true);

                    // ✅ FIX: Get value from the entity, not the DTO
                    Object value = entityField.get(entity);
                    dtoField.set(dto, value);

                } catch (NoSuchFieldException ignored) {
                    // Ignore fields not present in DTO
                }
            }
            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <D, E> E mapToEntity(D dto, Class<E> entityClass) {
        if (ObjectUtils.isEmpty(dto)) {
            return null;
        }

        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            for (Field dtoField : dto.getClass().getDeclaredFields()) {
                dtoField.setAccessible(true);
                Object value = dtoField.get(dto);

                try {
                    Field entityField = entityClass.getDeclaredField(dtoField.getName());
                    entityField.setAccessible(true);
                    entityField.set(entity, value);
                } catch (NoSuchFieldException ignored) {

                }
            }
            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <D, E> E merge(E entity, D dto) {
        if (ObjectUtils.isEmpty(dto)) {
            return entity;
        }

        for (Field dtoField : dto.getClass().getDeclaredFields()) {
            dtoField.setAccessible(true);
            try {

                Object dtoFieldValue = dtoField.get(dto);
                Field entityField = entity.getClass().getDeclaredField(dtoField.getName());

                if (dtoFieldValue != null) {
                    entityField.setAccessible(true);
                    entityField.set(entity, dtoFieldValue);
                }

            } catch (NoSuchFieldException | IllegalAccessException ignored) {
            }
        }
        return entity;
    }
}
