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

        String sentence = "Web developers often utilize HTML/CSS along with React framework for building modern web applications.";
        String backend = "My product is build with Spring, Java and Angular. And all data stores in our database MySql";

        PROVIDER.findNamesInSingleSentence(backend);
    }

    @SneakyThrows
    private String convert(Span span) {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(span);
    }
}
