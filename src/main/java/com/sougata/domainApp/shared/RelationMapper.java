package com.sougata.domainApp.shared;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class RelationMapper {
    private record ChildParentPair<C, P>(C child, P parent) {
    }

    public static MasterEntity mapToEntity(MasterDto dto, Map<Class<? extends MasterDto>, Class<? extends MasterEntity>> dtoEntityMap) {
        try {
            // no adj required as the entity already has the node.
            Queue<ChildParentPair<MasterDto, MasterEntity>> queue = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();
            visited.add(dto.hashCode());

            queue.offer(new ChildParentPair<>(dto, null));
            MasterEntity convertedEntity = null;

            while (!queue.isEmpty()) {
                ChildParentPair<MasterDto, MasterEntity> u = queue.poll();
                Class<? extends MasterEntity> entityClass = dtoEntityMap.get(u.child.getClass());
                MasterEntity v = entityClass.getDeclaredConstructor().newInstance();

                for (Field df : u.child.getClass().getDeclaredFields()) {
                    df.setAccessible(true);
                    Field vfield = v.getClass().getDeclaredField(df.getName());
                    vfield.setAccessible(true);

                    if (Collection.class.isAssignableFrom(df.getType())) {
                        Object relation = df.get(u.child);
                        if (relation != null) {
                            Collection<?> list = (Collection<?>) relation;
                            for (Object ele : list) {
                                if (ele instanceof MasterDto) {
                                    if (!visited.contains(ele.hashCode())) {
                                        visited.add(ele.hashCode());
                                        queue.offer(new ChildParentPair<>((MasterDto) ele, v));
                                    }
                                }
                            }
                        }
                        if (Set.class.isAssignableFrom(df.getType())) {
                            vfield.set(v, new HashSet<>());
                        } else {
                            vfield.set(v, new ArrayList<MasterEntity>());
                        }
                    } else if (isComplexType(df)) {
                        // set the parent object in the child v.
                        if (u.parent != null && u.parent.getClass().isAssignableFrom(vfield.getType())) {
                            vfield.set(v, u.parent);
                        }
                    } else {
                        Object uValue = df.get(u.child);
                        vfield.set(v, uValue);
                    }
                }

                // set the child in the relation list of parent
                if (u.parent != null) {
                    for (Field parentField : u.parent.getClass().getDeclaredFields()) {
                        parentField.setAccessible(true);

                        if (Collection.class.isAssignableFrom(parentField.getType())) {
                            Type genericType = parentField.getGenericType();
                            if (genericType instanceof ParameterizedType) {
                                Type actualType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
                                if (actualType == v.getClass()) {
                                    Collection<MasterEntity> relation = (Collection<MasterEntity>) parentField.get(u.parent);
                                    relation.add(v);
                                }
                            }
                        }
                    }
                } else {
                    convertedEntity = v;
                }
            }
            return convertedEntity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static MasterDto mapToDto(MasterEntity entity, Map<Class<? extends MasterEntity>, Class<? extends MasterDto>> entityDtoMap) {
        try {

            // no adj required as the entity already have the node.
            Queue<ChildParentPair<MasterEntity, MasterDto>> queue = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();
            visited.add(entity.hashCode());

            queue.offer(new ChildParentPair<>(entity, null));
            MasterDto convertedDto = null;

            while (!queue.isEmpty()) {
                ChildParentPair<MasterEntity, MasterDto> u = queue.poll();
                //System.out.println(u.child);
                Class<? extends MasterDto> dtoClass = entityDtoMap.get(u.child.getClass());
                MasterDto v = dtoClass.getDeclaredConstructor().newInstance();

                for (Field df : u.child.getClass().getDeclaredFields()) {
                    df.setAccessible(true);
                    Field vfield = v.getClass().getDeclaredField(df.getName());
                    vfield.setAccessible(true);

                    if (Collection.class.isAssignableFrom(df.getType())) {
                        Object relation = df.get(u.child);
                        if (relation != null) {
                            Collection<?> list = (Collection<?>) relation;
                            for (Object ele : list) {
                                if (ele instanceof MasterEntity) {
                                    if (!visited.contains(ele.hashCode())) {
                                        visited.add(ele.hashCode());
                                        queue.offer(new ChildParentPair<>((MasterEntity) ele, v));
                                    }
                                }
                            }
                        }
                        if (Set.class.isAssignableFrom(df.getType())) {
                            vfield.set(v, new HashSet<>());
                        } else {
                            vfield.set(v, new ArrayList<MasterEntity>());
                        }
                    } else if (isComplexType(df)) {
                        // set the parent object in the child v.
                        if (u.parent != null && u.parent.getClass().isAssignableFrom(vfield.getType())) {
                            // removing this because assigning the parent will cause stack overflow error
                            //vfield.set(v, u.parent);
                        }
                    } else {
                        Object uValue = df.get(u.child);
                        vfield.set(v, uValue);
                    }
                }

                // set the child in the relation list of parent
                if (u.parent != null) {
                    for (Field parentField : u.parent.getClass().getDeclaredFields()) {
                        parentField.setAccessible(true);

                        if (Collection.class.isAssignableFrom(parentField.getType())) {
                            Type genericType = parentField.getGenericType();
                            if (genericType instanceof ParameterizedType) {
                                Type actualType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
                                if (actualType == v.getClass()) {
                                    Collection<MasterDto> relation = (Collection<MasterDto>) parentField.get(u.parent);
                                    relation.add(v);
                                }
                            }
                        }
                    }
                } else {
                    convertedDto = v;
                }
            }
            return convertedDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static MasterEntity merge(MasterEntity og, MasterEntity nu) {
        if (nu == null) {
            return og;
        }
        try {

            Queue<ChildParentPair<MasterEntity, MasterEntity>> queueOg = new LinkedList<>();
            Queue<ChildParentPair<MasterEntity, MasterEntity>> queueNu = new LinkedList<>();

            Set<Integer> visitedOg = new HashSet<>();
            Set<Integer> visitedNu = new HashSet<>();

            queueOg.offer(new ChildParentPair<>(og, null));
            queueNu.offer(new ChildParentPair<>(nu, null));

            visitedOg.add(og.hashCode());
            visitedNu.add(nu.hashCode());

            while (!queueOg.isEmpty() && !queueNu.isEmpty()) {
                ChildParentPair<MasterEntity, MasterEntity> uOg = queueOg.poll();
                ChildParentPair<MasterEntity, MasterEntity> uNu = queueNu.poll();
                // iterating for the children
                for (Field ogField : uOg.child.getClass().getDeclaredFields()) {
                    ogField.setAccessible(true);
                    Field nuField = uNu.child.getClass().getDeclaredField(ogField.getName());
                    nuField.setAccessible(true);

                    // if og field is list, same goes for nu field.
                    if (Collection.class.isAssignableFrom(ogField.getType())) {
                        Object valueOg = ogField.get(uOg.child);
                        Object valueNu = nuField.get(uNu.child);

                        Collection<MasterEntity> listOg = (Collection<MasterEntity>) valueOg;
                        Collection<MasterEntity> listNu = (Collection<MasterEntity>) valueNu;

                        Map<Long, MasterEntity> mapOg = listOg.stream().collect(Collectors.toMap(MasterEntity::getId, i -> i));
                        Map<Long, MasterEntity> mapNu = listNu.stream().collect(Collectors.toMap(MasterEntity::getId, i -> i));

                        Collection<MasterEntity> itemOgRemoveList = new ArrayList<>();

                        for (MasterEntity itemOg : listOg) {
                            // check for removal
                            if (!mapNu.containsKey(itemOg.getId())) {
                                for (Field itemOgField : itemOg.getClass().getDeclaredFields()) {
                                    itemOgField.setAccessible(true);
                                    if (isComplexType(itemOgField) && uOg.child.getClass().isAssignableFrom(itemOgField.getType())) {
                                        // remove the child parent mapping.
                                        itemOgField.set(itemOg, null);
                                    }
                                }
                                // add the child to remove list.
                                itemOgRemoveList.add(itemOg);
                            } else {
                                // traverse the bfs that are untouched.
                                if (!visitedOg.contains(itemOg.hashCode())) {
                                    visitedOg.add(itemOg.hashCode());
                                    queueOg.offer(new ChildParentPair<>(itemOg, uOg.child));
                                }
                            }
                        }
                        // finally remove the children
                        itemOgRemoveList.forEach(listOg::remove);

                        for (MasterEntity itemNu : listNu) {
                            // check for insertion.
                            if (itemNu.getId() == null || !mapOg.containsKey(itemNu.getId())) {
                                for (Field itemNuField : itemNu.getClass().getDeclaredFields()) {
                                    itemNuField.setAccessible(true);
                                    if (isComplexType(itemNuField) && uOg.child.getClass().isAssignableFrom(itemNuField.getType())) {
                                        // add the og parent in parent relation.
                                        itemNuField.set(itemNu, uOg.child);
                                    }
                                }
                                // set the child in the ogList
                                listOg.add(itemNu);
                            } else {
                                // traverse the bfs that are untouched.
                                if (!visitedNu.contains(itemNu.hashCode())) {
                                    visitedNu.add(itemNu.hashCode());
                                    queueNu.offer(new ChildParentPair<>(itemNu, uNu.child));
                                }
                            }
                        }
                    } else if (!isComplexType(ogField) && uOg.parent == null) {
                        // update the attribute of the first parent only.
                        Object valueNu = nuField.get(uNu.child);
                        if (valueNu != null) ogField.set(uOg.child, valueNu);
                    }
                }
            }
            return og;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isComplexType(Field f) {
        return !Integer.class.isAssignableFrom(f.getType()) && !Long.class.isAssignableFrom(f.getType()) &&
                !Double.class.isAssignableFrom(f.getType()) && !Boolean.class.isAssignableFrom(f.getType()) &&
                !Float.class.isAssignableFrom(f.getType()) && !String.class.isAssignableFrom(f.getType()) &&
                !LocalDateTime.class.isAssignableFrom(f.getType()) && !Timestamp.class.isAssignableFrom(f.getType()) &&
                !Date.class.isAssignableFrom(f.getType()) && !Enum.class.isAssignableFrom(f.getType());
    }
}
