package com.group.genshinProg.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Исключение выбрасывается когда не найдена сущность по id
 */
@Getter
public class NotFoundEntityByIdException extends RuntimeException {

    private final Class<?> clazz;
    private final Long id;

    public NotFoundEntityByIdException(Class<?> clazz, Long id) {
        this.clazz = clazz;
        this.id = id;
    }

    public String getDescription() {
        return "No class object found " + clazz.getSimpleName() + " with id=" + id;
    }

}
