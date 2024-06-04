package uz.sardorbroo.opennlp.language.detector.provider;

import opennlp.tools.langdetect.Language;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uz.sardorbroo.opennlp.language.detector.provider.impl.SampleLanguageDetectorProvider;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

class SampleLanguageDetectorProviderTest {

    private static final LanguageDetectorProvider DETECTOR = new SampleLanguageDetectorProvider();
    private static final Map<String, String> TEXT_BY_LANGUAGE_CODE = Map.of(
            "eng", "The London is capital of Great Britain",
            "rus", "Москва столица России",
            "uzb", "O'zbekistonning poytaxti Toshkent"
    );

    private static Stream<String> getInvalidStrings() {
        return Stream.of(
                null, "", "          "
        );
    }

    private static Stream<Map.Entry<String, String>> getTextsWithLanguageCode() {
        return TEXT_BY_LANGUAGE_CODE.entrySet().stream();
    }

    @Test
    public void testGetAllSupportedLanguages() {

        var languages = DETECTOR.getAllSupportedLanguages();

        System.out.println(Arrays.toString(languages));
    }

    @ParameterizedTest
    @MethodSource("getInvalidStrings")
    public void testDetectingLanguageWithInvalidParams(String text) {

        Assertions.assertThrows(IllegalArgumentException.class, () -> DETECTOR.findLanguage(text));

    }

    @ParameterizedTest
    @MethodSource("getTextsWithLanguageCode")
    public void testDetectingLanguageWithValidParams(Map.Entry<String, String> entry) {

        String languageCode = entry.getKey();
        String text = entry.getValue();

        Language detectedLanguage = DETECTOR.findLanguage(text);

        Assertions.assertNotNull(detectedLanguage);
        Assertions.assertEquals(languageCode, detectedLanguage.getLang());
    }

    @ParameterizedTest
    @MethodSource("getInvalidStrings")
    public void testDetectingAllLanguageWithInvalidParams(String text) {

        Assertions.assertThrows(IllegalArgumentException.class, () -> DETECTOR.findAllLanguages(text));

    }
}
