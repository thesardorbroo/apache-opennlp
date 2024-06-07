package uz.sardorbroo.opennlp.name.finder.provider.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import uz.sardorbroo.opennlp.name.finder.provider.NameFinderProvider;
import uz.sardorbroo.opennlp.sentence.detector.provider.SentenceDetectorProvider;
import uz.sardorbroo.opennlp.sentence.detector.provider.impl.SampleSentenceDetectorProviderImpl;
import uz.sardorbroo.opennlp.tokenization.provider.TokenizerProvider;
import uz.sardorbroo.opennlp.tokenization.provider.impl.SampleTokenizerProvider;
import uz.sardorbroo.opennlp.utils.ModelUtils;

import java.util.Arrays;

@Slf4j
public class ProgrammingTechnologyNameFinderProvider implements NameFinderProvider {

    private final NameFinderME nameFinderME;
    private final SentenceDetectorProvider sentenceDetectorProvider;
    private final TokenizerProvider tokenizerProvider;
    private String modelName = "/opennlp-models/en-ner-programming-technology.bin";

    @SneakyThrows
    public ProgrammingTechnologyNameFinderProvider() {
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(ModelUtils.getModelInputStream(modelName));
        this.nameFinderME = new NameFinderME(tokenNameFinderModel);
        this.sentenceDetectorProvider = new SampleSentenceDetectorProviderImpl();
        this.tokenizerProvider = new SampleTokenizerProvider(false);
    }

    @SneakyThrows
    public ProgrammingTechnologyNameFinderProvider(String modelName) {
        this.modelName = modelName;
        this.nameFinderME = new NameFinderME(new TokenNameFinderModel(ModelUtils.getModelInputStream(this.modelName)));
        this.sentenceDetectorProvider = new SampleSentenceDetectorProviderImpl();
        this.tokenizerProvider = new SampleTokenizerProvider(false);
    }

    @Override
    // todo override
    public String[][] findNames(String text) {
        log.info("Find names from text. Text: {}", text);

        if (StringUtils.isBlank(text)) {
            log.warn("Invalid argument is passed! Text must not be blank!");
            throw new IllegalArgumentException("Invalid argument is passed! Text must not be blank!");
        }

        return new String[0][0];
    }

    @Override
    public String[] findNamesInSingleSentence(String sentence) {
        log.info("Find names from single sentence. Sentence: {}", sentence);

        if (StringUtils.isBlank(sentence)) {
            log.warn("Invalid argument is passed! Sentence must not be blank!");
            throw new IllegalArgumentException("Invalid argument is passed! Sentence must not be blank!");
        }


        String[] words = Arrays.stream(tokenizerProvider.tokenize(sentence))
                .filter(StringUtils::isNotBlank)
                .map(String::trim)
                .toArray(String[]::new);

        if (ArrayUtils.isEmpty(words)) {
            log.warn("Tokenization sentence failed. Sentence: {}", sentence);
            return new String[0];
        }

        Span[] spans = nameFinderME.find(words);
        nameFinderME.clearAdaptiveData();
        // System.out.println(Arrays.toString(spans));
        // System.out.println(Arrays.toString(words));

        return Arrays.stream(spans)
                .map(span -> words[span.getStart()])
                .toArray(String[]::new);
    }
}
