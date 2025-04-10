//package com.sougata.domainApp.shared;
//
//import com.sougata.domainApp.master.entity.MasterEntity;
//import org.springframework.util.ObjectUtils;
//
//import java.lang.reflect.Field;
//import java.util.*;
//import java.util.function.BiFunction;
//
//

/*
 * Old mapping only for simple field.
 * */

//public class Mapper {
//    public static <D, E> D mapToDto(E entity, Class<D> dtoClass) {
//        if (ObjectUtils.isEmpty(entity)) {
//            return null;
//        }
//        try {
//            D dto = dtoClass.getDeclaredConstructor().newInstance();
//            for (Field entityField : entity.getClass().getDeclaredFields()) {
//                entityField.setAccessible(true);
//                try {
//                    Field dtoField = dtoClass.getDeclaredField(entityField.getName());
//                    dtoField.setAccessible(true);
//
//                    // ✅ FIX: Get value from the entity, not the DTO
//                    Object value = entityField.get(entity);
//                    dtoField.set(dto, value);
//
//                } catch (NoSuchFieldException ignored) {
//                    // Ignore fields not present in DTO
//                }
//            }
//            return dto;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static <D, E> E mapToEntity(D dto, Class<E> entityClass) {
//        if (ObjectUtils.isEmpty(dto)) {
//            return null;
//        }
//
//        try {
//            E entity = entityClass.getDeclaredConstructor().newInstance();
//            for (Field dtoField : dto.getClass().getDeclaredFields()) {
//                dtoField.setAccessible(true);
//                Object value = dtoField.get(dto);
//
//                try {
//                    Field entityField = entityClass.getDeclaredField(dtoField.getName());
//                    entityField.setAccessible(true);
//                    entityField.set(entity, value);
//                } catch (NoSuchFieldException ignored) {
//
//                }
//            }
//            return entity;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static <D, E> E merge(E entity, D dto) {
//        if (ObjectUtils.isEmpty(dto)) {
//            return entity;
//        }
//
//        for (Field dtoField : dto.getClass().getDeclaredFields()) {
//            dtoField.setAccessible(true);
//            try {
//
//                Object dtoFieldValue = dtoField.get(dto);
//                Field entityField = entity.getClass().getDeclaredField(dtoField.getName());
//
//                if (dtoFieldValue != null) {
//                    entityField.setAccessible(true);
//                    entityField.set(entity, dtoFieldValue);
//                }
//
//            } catch (NoSuchFieldException | IllegalAccessException ignored) {
//            }
//        }
//        return entity;
//    }
//
//    private static Field findMatchingField(Class<?> entityClass, Class<?> relatedClass) {
//        for (Field field : entityClass.getDeclaredFields()) {
//            if (field.getType().isAssignableFrom(relatedClass)) {
//                return field;
//            }
//        }
//        return null;
//    }
//
//    public static <D, E, R> E mapToEntity(D dto, Class<E> entityClass, R relatedEntity) {
//        if (ObjectUtils.isEmpty(dto)) {
//            throw new RuntimeException("Error in mapping : dto is null");
//        }
//        try {
//            E entity = entityClass.getDeclaredConstructor().newInstance();
//            for (Field dtoField : dto.getClass().getDeclaredFields()) {
//                dtoField.setAccessible(true);
//                Object dtoFieldValue = dtoField.get(dto);
//                Field entityField = entityClass.getDeclaredField(dtoField.getName());
//                entityField.setAccessible(true);
//                entityField.set(entity, dtoFieldValue);
//            }
//
//            // search for the appropriate field.
//            Field relatedEntityField = findMatchingField(entityClass, relatedEntity.getClass());
//            if (relatedEntityField != null) {
//                // set the relation field.
//                relatedEntityField.setAccessible(true);
//                relatedEntityField.set(entity, relatedEntity);
//            } else {
//                throw new RuntimeException("Error in mapping : relatedEntity not found");
//            }
//            return entity;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static <D, E> E mapToEntity(D dto, Class<E> entityClass,
//                                       Map<Class<? extends MasterDTO>, Class<? extends MasterEntity>> dtoEntityMap) {
//        if (ObjectUtils.isEmpty(dto)) {
//            throw new RuntimeException("dto is null");
//        }
//
//        try {
//            E entity = entityClass.getDeclaredConstructor().newInstance();
//
//            for (Field dtoField : dto.getClass().getDeclaredFields()) {
//                dtoField.setAccessible(true);
//
//                // check for relation list.
//                if (Collection.class.isAssignableFrom(dtoField.getType())) {
//                    // convert all the list item to dto.
//
//                    Object relationList = dtoField.get(dto);
//                    // casting to collection as relation list is not iterable.
//                    Collection<?> dtoList = (Collection<?>) relationList;
//
//                    // assign proper collection.
//                    Collection<Object> entityList;
//                    if (dtoField.getType().isAssignableFrom(Arrays.class)) {
//                        entityList = new ArrayList<>();
//                    } else if (dtoField.getType().isAssignableFrom(Set.class)) {
//                        entityList = new HashSet<>();
//                    } else if (dtoField.getType().isAssignableFrom(LinkedList.class)) {
//                        entityList = new LinkedList<>();
//                    } else {
//                        entityList = new LinkedHashSet<>();
//                    }
//
//
//                    for (Object dtoListItem : dtoList) {
//                        Class<?> dtoListItemClass = dtoListItem.getClass();
//                        Class<?> entityListItemClass = dtoEntityMap.get(dtoListItemClass);
//                        Object dtoListItemEntity = mapToEntity(dtoListItem, entityListItemClass, entity);
//                        entityList.add(dtoListItemEntity);
//                    }
//
//                    // set the relation list to the mapping entity.
//                    Field entityField = entityClass.getDeclaredField(dtoField.getName());
//                    entityField.setAccessible(true);
//                    entityField.set(entity, entityList);
//
//                } else {
//                    // not relation field
//                    Object dtoValue = dtoField.get(dto);
//                    Field entityField = entityClass.getDeclaredField(dtoField.getName());
//                    entityField.setAccessible(true);
//                    entityField.set(entity, dtoValue);
//                }
//            }
//            return entity;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static Object getIdValue(Object entity) {
//        // check for @Id annotation.
//        try {
//            for (Field field : entity.getClass().getDeclaredFields()) {
//                if (field.isAnnotationPresent(Id.class)) {
//                    field.setAccessible(true);
//                    return field.get(entity);
//                }
//            }
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public static <D, E> E merge(D dto, E entity,
//                                 BiFunction<Collection<?>, Collection<?>, Collection<?>> mergeFunction) {
//
//        try {
//            for (Field entityField : entity.getClass().getDeclaredFields()) {
//                Field dtoField = dto.getClass().getDeclaredField(entityField.getName());
//                entityField.setAccessible(true);
//                dtoField.setAccessible(true);
//
//                // merging the collection.
//                if (Collection.class.isAssignableFrom(entityField.getType())) {
//                    // casting to collection as relation list is not iterable.
//                    Collection<?> entityCollection = (Collection<?>) entityField.get(entity);
//                    Collection<?> dtoCollection = (Collection<?>) dtoField.get(dto);
//                    if (dtoCollection != null) {
//                        Collection<?> mergedCollection = mergeFunction.apply(dtoCollection, entityCollection);
//                        entityField.set(entity, mergedCollection);
//                    }
//                } else {
//                    Object dtoFieldValue = dtoField.get(dto);
//                    if (dtoFieldValue != null) {
//                        entityField.set(entity, dtoFieldValue);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return entity;
//    }
//}
