package org.example.mapper;

import org.example.controller.ViewportController;
import org.example.model.element.Line;
import org.example.model.element.LineEq;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {
    private static final Map<Class<?>, Mapper<?, ?>> mappers = new HashMap<>();

    static {
        mappers.put(Line.class, new LineMapper(ViewportController.getInstance()));
        mappers.put(LineEq.class, new LineEqMapper(ViewportController.getInstance()));
        // Add all the mappers
    }

    @SuppressWarnings("unchecked")
    public static <M, D> Mapper<M, D> getMapper(Class<M> clazz) {
        return (Mapper<M, D>) mappers.get(clazz);
    }
}
