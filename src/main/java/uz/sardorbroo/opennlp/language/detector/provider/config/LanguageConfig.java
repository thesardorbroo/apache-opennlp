package uz.sardorbroo.opennlp.language.detector.provider.config;

import java.util.List;

public class LanguageConfig {

    private final List<String> allowedLanguages;

    public LanguageConfig(List<String> allowedLanguages) {
        this.allowedLanguages = allowedLanguages;
    }

    public List<String> getAllowedLanguages() {
        return this.allowedLanguages;
    }
}
