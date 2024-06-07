package uz.sardorbroo.opennlp.name.finder.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import opennlp.tools.util.Span;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import uz.sardorbroo.opennlp.name.finder.provider.impl.ProgrammingTechnologyNameFinderProvider;

import java.util.Arrays;

public class ProgrammingTechnologyNameFinderProviderTest {

    private static final NameFinderProvider PROVIDER =
            new ProgrammingTechnologyNameFinderProvider();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void test() {

        String sentence = "Web developers often utilize HTML/CSS along with React framework for building modern web applications.";
        String backend = "My product is build with spring, Java and Angular. And all data stores in our database rust";

        var result = PROVIDER.findNamesInSingleSentence(backend);
        System.out.println(Arrays.toString(result));
    }

    @SneakyThrows
    private String convert(Span span) {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(span);
    }
}
