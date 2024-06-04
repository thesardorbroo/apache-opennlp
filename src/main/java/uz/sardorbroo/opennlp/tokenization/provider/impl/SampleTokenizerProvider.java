package uz.sardorbroo.opennlp.tokenization.provider.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.apache.commons.lang3.StringUtils;
import uz.sardorbroo.opennlp.tokenization.provider.TokenizerProvider;
import uz.sardorbroo.opennlp.utils.ModelUtils;

@Slf4j
public class SampleTokenizerProvider implements TokenizerProvider {

    private String model = "/opennlp-models/opennlp-en-ud-ewt-tokens-1.0-1.9.3.bin";
    private final Tokenizer tokenizer;

    @SneakyThrows
    public SampleTokenizerProvider() {
        TokenizerModel model = new TokenizerModel(ModelUtils.getModelInputStream(this.model));
        tokenizer = new TokenizerME(model);
    }

    @SneakyThrows
    public SampleTokenizerProvider(String model) {
        this.model = model;
        TokenizerModel tokenizerModel = new TokenizerModel(ModelUtils.getModelInputStream(model));
        tokenizer = new TokenizerME(tokenizerModel);
    }

    @Override
    public String[] tokenize(String sentence) {
        log.info("Tokenize the sentence. Sentence: {}", sentence);

        if (StringUtils.isBlank(sentence)) {
            log.warn("Invalid argument is passed! Sentence must not be blank!");
            throw new IllegalArgumentException("Invalid argument is passed! Sentence must not be blank!");
        }

        String[] words = tokenizer.tokenize(sentence);
        log.info("Sentence is tokenized successfully. Words count: {}", words.length);
        return words;
    }
}
