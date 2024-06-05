package uz.sardorbroo.opennlp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

public class TestUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public static String[] getInvalidStringParams() {
        return new String[]{null, "", "   "};
    }

    @SneakyThrows
    public static <T> String toString(T o) {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }
}
