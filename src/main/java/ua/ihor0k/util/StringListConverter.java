package ua.ihor0k.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return String.join(" → ", attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return new LinkedList<>(Arrays.asList(dbData.split(" → ")));
    }
}
