package com.example.todo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseMapper {

    protected static final ObjectMapper objectMapper = new ObjectMapper();

    protected BaseMapper() {
    }

    public static String mapToJSON(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
