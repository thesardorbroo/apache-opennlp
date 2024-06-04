package uz.sardorbroo.opennlp.language.detector.provider.config;

import java.util.List;

public class DefaultLanguageConfig extends LanguageConfig {

    public DefaultLanguageConfig() {
        super(List.of("eng", "rus", "uzb"));
    }

    public DefaultLanguageConfig(List<String> allowedLanguages) {
        super(allowedLanguages);
    }
}
