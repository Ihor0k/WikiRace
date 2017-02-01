import junit.framework.Assert;
import org.junit.Test;
import ua.ihor0k.model.Page;
import ua.ihor0k.util.StringListConverter;

import java.util.Arrays;
import java.util.List;

public class StringListConverterTest {
    @Test
    public void stringToList() {
        StringListConverter slc = new StringListConverter();

        String s = "str1 → str2 → str3 → str4 → str5";
        List<Page> list = Arrays.asList(new Page("str1"), new Page("str2"), new Page("str3"), new Page("str4"), new Page("str5"));

        Assert.assertEquals(slc.convertToEntityAttribute(s), list);
    }

    @Test
    public void listToString() {
        StringListConverter slc = new StringListConverter();

        String s = "str1 → str2 → str3 → str4 → str5";
        List<Page> list = Arrays.asList(new Page("str1"), new Page("str2"), new Page("str3"), new Page("str4"), new Page("str5"));

        Assert.assertEquals(slc.convertToDatabaseColumn(list), s);
    }
}
