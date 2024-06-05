package uz.sardorbroo.opennlp.tokenization.provider.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import opennlp.tools.tokenize.*;
import org.apache.commons.lang3.StringUtils;
import uz.sardorbroo.opennlp.tokenization.provider.TokenizerProvider;
import uz.sardorbroo.opennlp.utils.ModelUtils;

@Slf4j
public class SampleTokenizerProvider implements TokenizerProvider {

    private String model = "/opennlp-models/en-token.bin";
    private final Tokenizer tokenizer;

    @SneakyThrows
    public SampleTokenizerProvider(boolean withWhitespace) {
        TokenizerModel model = new TokenizerModel(ModelUtils.getModelInputStream(this.model));
        tokenizer = withWhitespace ? WhitespaceTokenizer.INSTANCE : new TokenizerME(model);
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
