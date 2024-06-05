package uz.sardorbroo.opennlp.name.finder.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import opennlp.tools.util.Span;
import org.junit.jupiter.api.Test;
import uz.sardorbroo.opennlp.name.finder.provider.impl.ProgrammingTechnologyNameFinderProvider;

public class ProgrammingTechnologyNameFinderProviderTest {

    private static final NameFinderProvider PROVIDER =
            new ProgrammingTechnologyNameFinderProvider();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void test() {

        String sentence = "Java";

        PROVIDER.findNamesInSingleSentence(sentence);
    }

    @SneakyThrows
    private String convert(Span span) {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(span);
    }
}
