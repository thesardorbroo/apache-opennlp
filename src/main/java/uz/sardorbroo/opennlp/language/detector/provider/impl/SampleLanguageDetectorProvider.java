package uz.sardorbroo.opennlp.language.detector.provider.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import opennlp.tools.langdetect.Language;
import opennlp.tools.langdetect.LanguageDetector;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;
import org.apache.commons.lang3.StringUtils;
import uz.sardorbroo.opennlp.language.detector.provider.LanguageDetectorProvider;
import uz.sardorbroo.opennlp.language.detector.provider.config.DefaultLanguageConfig;
import uz.sardorbroo.opennlp.language.detector.provider.config.LanguageConfig;
import uz.sardorbroo.opennlp.utils.ModelUtils;

import java.io.InputStream;
import java.util.Arrays;

@Slf4j
public class SampleLanguageDetectorProvider implements LanguageDetectorProvider {
    private final LanguageDetector detector;
    private LanguageConfig languageConfig = new DefaultLanguageConfig();
    private String model = "/opennlp-models/langdetect-183.bin";

    @SneakyThrows
    public SampleLanguageDetectorProvider() {
        LanguageDetectorModel model = new LanguageDetectorModel(ModelUtils.getModelInputStream(this.model));
        this.detector = new LanguageDetectorME(model);
    }

    @SneakyThrows
    public SampleLanguageDetectorProvider(String model, LanguageConfig languageConfig) {
        this.model = model;
        LanguageDetectorModel detectorModel = new LanguageDetectorModel(ModelUtils.getModelInputStream(this.model));
        this.detector = new LanguageDetectorME(detectorModel);
        this.languageConfig = languageConfig;
    }

    @Override
    public Language findLanguage(String text) {
        log.info("Find the most confidence language from text. Text: {}", text);

        throwIfInvalidText(text);

        Language language = Arrays.stream(findAllLanguages(text))
                .filter(lang -> languageConfig.getAllowedLanguages().contains(lang.getLang()))
                .findFirst()
                .orElse(null);
        log.info("The best language is found in text. Language: {}", language);
        return language;
    }

    @Override
    public Language[] findAllLanguages(String text) {
        log.info("Find all languages from text. Text: {}", text);

        throwIfInvalidText(text);

        Language[] languages = detector.predictLanguages(text);
        log.info("Languages are found in text. Languages count: {}", languages.length);
        return languages;
    }

    @Override
    public String[] getAllSupportedLanguages() {
        log.info("Get all supported languages");

        String[] languages = detector.getSupportedLanguages();
        log.info("All supported languages are fetched. Languages count: {}", languages.length);
        return languages;
    }

    private void throwIfInvalidText(String text) {
        if (StringUtils.isBlank(text)) {
            log.warn("Invalid argument is passed! Text must not be blank!");
            throw new IllegalArgumentException("Invalid argument is passed! Text must not be blank!");
        }
    }
}
