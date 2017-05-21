package ua.ihor0k.util;

import ua.ihor0k.model.Page;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class StringListConverter implements AttributeConverter<LinkedList<Page>, String> {
    @Override
    public String convertToDatabaseColumn(LinkedList<Page> pages) {
        return pages.stream().map(Page::getTitle).collect(Collectors.joining(" → "));
    }

    @Override
    public LinkedList<Page> convertToEntityAttribute(String string) {
        return Stream
                .of(string.split(" → "))
                .map(Page::new)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
