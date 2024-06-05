package uz.sardorbroo.opennlp.tokenization.provider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uz.sardorbroo.opennlp.tokenization.provider.impl.SampleTokenizerProvider;
import uz.sardorbroo.opennlp.utils.TestUtils;

import java.util.Arrays;
import java.util.stream.Stream;

public class SampleTokenizerProviderTest {

    private static Stream<String> getInvalidStringParams() {
        return Arrays.stream(TestUtils.getInvalidStringParams());
    }

    @ParameterizedTest
    @MethodSource("getInvalidStringParams")
    public void testTokenizingSentenceWithInvalidParams(String sentence) {

        var provider = new SampleTokenizerProvider(false);
        Assertions.assertThrows(IllegalArgumentException.class, () -> provider.tokenize(sentence));

    }

    @Test
    public void testTokenizingSentenceWithValidParamWithNonWhitespace() {

        var provider = new SampleTokenizerProvider(false);
        String text = "Hi, I'm Sardorbroo, and call me just broo. I'm Java developer";
        String[] expected = new String[]{"Hi", ",", "I", "'m", "Sardorbroo", ",", "and", "call", "me", "just", "broo", ".", "I", "'m", "Java", "developer"};

        String[] words = provider.tokenize(text);
        System.out.println(Arrays.toString(words));
        Assertions.assertArrayEquals(expected, words);
    }

    @Test
    public void testTokenizingSentenceWithValidParamWithWhitespace() {

        var provider = new SampleTokenizerProvider(true);
        String text = "Hi, I'm Sardorbroo, and call me just broo. I'm Java developer";
        String[] expected = new String[]{"Hi,", "I'm", "Sardorbroo,", "and", "call", "me", "just", "broo.", "I'm", "Java", "developer"};

        String[] words = provider.tokenize(text);
        System.out.println(Arrays.toString(words));
        Assertions.assertArrayEquals(expected, words);
    }
}
