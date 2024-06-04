package uz.sardorbroo.opennlp.name.finder.provider.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import uz.sardorbroo.opennlp.name.finder.provider.NameFinderProvider;
import uz.sardorbroo.opennlp.tokenization.provider.TokenizerProvider;
import uz.sardorbroo.opennlp.tokenization.provider.impl.SampleTokenizerProvider;
import uz.sardorbroo.opennlp.utils.ModelUtils;

@Slf4j
public class SampleNameFinderProvider implements NameFinderProvider {

    private final NameFinderME nameFinderME;
    private final TokenizerProvider provider;
    private String modelName = "/opennlp-models/en-ner-person.bin";

    @SneakyThrows
    public SampleNameFinderProvider() {
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(ModelUtils.getModelInputStream(modelName));
        this.nameFinderME = new NameFinderME(tokenNameFinderModel);
        this.provider = new SampleTokenizerProvider();
    }

    @Override
    public Span[] findNames(String sentence) {
        log.info("Find names from sentences. Sentence: {}", sentence);

        if (StringUtils.isBlank(sentence)) {
            log.warn("Invalid argument is passed! Sentence must not be blank!");
            throw new IllegalArgumentException("Invalid argument is passed! Sentence must not be blank!");
        }

        String[] words = provider.tokenize(sentence);
        if (ArrayUtils.isEmpty(words)) {
            log.warn("Sentence is not tokenized correctly!");
            return new Span[0];
        }

        Span[] spans = nameFinderME.find(words);
        nameFinderME.clearAdaptiveData();

        return spans;
    }
}
