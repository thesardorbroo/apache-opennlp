package uz.sardorbroo.opennlp.language.detector.provider;

import opennlp.tools.langdetect.Language;

public interface LanguageDetectorProvider {

    /**
     * The method returns the most confidence language by text(sentence)
     *
     * @param text
     * @return {@link Language}
     */
    Language findLanguage(String text);


    /**
     * The method returns all languages by text(sentence)
     *
     * @param text
     * @return Array of {@link Language}
     */
    Language[] findAllLanguages(String text);


    /**
     * The method returns all supported languages
     *
     * @return Array of {@link String}
     */
    String[] getAllSupportedLanguages();
}
