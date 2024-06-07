package uz.sardorbroo.opennlp.shell;

import lombok.extern.slf4j.Slf4j;
import opennlp.tools.langdetect.Language;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uz.sardorbroo.opennlp.language.detector.provider.LanguageDetectorProvider;
import uz.sardorbroo.opennlp.language.detector.provider.impl.SampleLanguageDetectorProvider;
import uz.sardorbroo.opennlp.name.finder.provider.NameFinderProvider;
import uz.sardorbroo.opennlp.name.finder.provider.impl.ProgrammingTechnologyNameFinderProvider;
import uz.sardorbroo.opennlp.tokenization.provider.TokenizerProvider;
import uz.sardorbroo.opennlp.tokenization.provider.impl.SampleTokenizerProvider;

import java.util.Arrays;

@Slf4j
@ShellComponent
public class NlpComponent {

    private final NameFinderProvider nameFinderProvider = new ProgrammingTechnologyNameFinderProvider();
    private final LanguageDetectorProvider languageDetectorProvider = new SampleLanguageDetectorProvider();
    private final TokenizerProvider tokenizerProvider = new SampleTokenizerProvider(true);

    @ShellMethod(key = "pt-ner")
    public String ner(@ShellOption String text) {
        log.info("Start Programming-Tech named entity recognition. Text: {}", text);

        String[] result = nameFinderProvider.findNamesInSingleSentence(text);

        return "Programming technologies NER result:\n\t\t" + Arrays.toString(result);
    }

    @ShellMethod(key = "ld-detector")
    public Language languageDetection(@ShellOption String text) {
        log.info("Start Language detector. Text: {}", text);

        return languageDetectorProvider.findLanguage(text);
    }

    @ShellMethod(key = "tokenization")
    public String[] tokenization(@ShellOption String text) {
        log.info("Start tokenization. Text: {}", text);

        return tokenizerProvider.tokenize(text);
    }
}
