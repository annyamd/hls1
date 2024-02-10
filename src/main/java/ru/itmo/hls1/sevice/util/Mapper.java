package ru.itmo.hls1.sevice.util;

public interface Mapper<T, E> {
    E entityToDto(T entity);
    T dtoToEntity(E dto);
}
