package uz.sardorbroo.opennlp.name.finder.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import opennlp.tools.util.Span;
import org.junit.jupiter.api.Test;
import uz.sardorbroo.opennlp.name.finder.provider.impl.SampleNameFinderProvider;

import java.util.Arrays;

public class SampleNameFinderProviderTest {

    private static final NameFinderProvider PROVIDER = new SampleNameFinderProvider();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void test() {
        // todo override
    }

    @SneakyThrows
    private String convert(Span span) {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(span);
    }
}
