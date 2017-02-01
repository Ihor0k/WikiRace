package ua.ihor0k.util;

import ua.ihor0k.model.Page;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class StringListConverter implements AttributeConverter<List<Page>, String> {
    @Override
    public String convertToDatabaseColumn(List<Page> pages) {
        return pages.stream().map(Page::getTitle).collect(Collectors.joining(" → "));
    }

    @Override
    public List<Page> convertToEntityAttribute(String string) {
        return Stream.of(string.split(" → ")).map(Page::new).collect(Collectors.toList());
    }
}
